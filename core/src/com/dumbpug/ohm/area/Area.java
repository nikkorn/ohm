package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.NBPWorld;
import com.dumbpug.ohm.player.Player;
import com.dumbpug.ohm.resources.AreaResources;

/**
 * Represents an area in game.
 */
public class Area {

    /** The area camera. */
    private OrthographicCamera camera;

    /** The physics world for this area. */
    private NBPWorld physicsWorld;

    /** The width of the area in blocks. */
    private int blocksWide;

    /** The area overlay. */
    private Texture overlay;

    /** The player. */
    private Player player;

    /** The player spawn. */
    private Spawn playerSpawn;

    /** The name of the next area. */
    private String area;

    /** The name of the next area. */
    private String nextArea;

    /** Flag defining whether this area is complete. */
    private boolean isComplete = false;

    /** Flag defining whether this area is failed (player died or dropped out). */
    private boolean isFailed = false;

    /**
     * Create a new instance of the Area class.
     * @param areaName
     */
    public Area(String areaName) {
        // Set the area name.
        this.area = areaName;
        // Get the area details from disk.
        JsonValue details = new JsonReader().parse(Gdx.files.internal("areas/" + areaName + "/details.json"));
        // Create the physics world.
        this.createPhysicsWorld(areaName);
        // Create the player spawn.
        this.playerSpawn = new Spawn(details.get("spawn"));
        // Set the name of the next area.
        this.nextArea = details.getString("nextArea");
        // Create the area camera..
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.AREA_ZOOM;
        camera.update();
        // Set the area overlay.
        this.overlay = new Texture(Gdx.files.internal("areas/" + areaName + "/overlay.png"));
    }

    /**
     * Add the player to the area in the starting position.
     * @param player
     */
    public void addPlayer(Player player) {
        // Add player to this area at the player spawn.
        this.player = player;
        player.addToPhysicsWorld(this.physicsWorld, this.playerSpawn.getX(), this.playerSpawn.getY());
    }

    /**
     * Tick the area.
     */
    public void tick() {
        // Update the physics world.
        physicsWorld.update();
        // Tick the player if we have one yet.
        if (player != null) { player.tick(); }
        // Process input.
        processInput();
        // Update the camera.
        updateCamera();
        // Check the status of the player.
        checkPlayerStatus();
    }

    /**
     * Draw the area.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        // Draw background.
        batch.draw(AreaResources.background, 0, 0);
        // Draw player.
        player.draw(batch);
        // Draw overlay.
        batch.draw(this.overlay, 0, 0);
    }

    /**
     * Gets whether this area is complete.
     * @return is complete.
     */
    public boolean isComplete() { return this.isComplete; }

    /**
     * Gets whether this area is failed.
     * @return is failed.
     */
    public boolean isFailed() { return this.isFailed; }

    /**
     * Gets the name of the next area.
     * @return the name of the next area.
     */
    public String getNextAreaName() { return this.nextArea; }

    /**
     * Gets the name of the area.
     * @return the name of the area.
     */
    public String getAreaName() { return this.area; }

    /**
     * Process user input.
     */
    private void processInput() {
        // The way we handle input depends n whether we are running on ouya or not.
        if (Ouya.runningOnOuya) {
            // Handle Ouya input.
            for (Controller controller : Controllers.getControllers()) {
                // Are we jumping?
                if (controller.getButton(Ouya.BUTTON_O)) {
                    player.jump();
                }
                // Get the x axis of the left stick (movement).
                float leftXAxis = controller.getAxis(Ouya.AXIS_LEFT_X);
                // Maybe move left or right, based on the position of the stick.
                if (leftXAxis < -0.5) {
                    player.moveLeft();
                }
                if (leftXAxis > 0.5) {
                    player.moveRight();
                }
            }
        } else {
            // Are we running left?
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                // We are running left.
                player.moveLeft();
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                // We are running right.
                player.moveRight();
            }
            // Are we jumping?
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                player.jump();
            }
            // Do we want to exit?
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }
    }

    /**
     * Update the specified camera to reflect the players position.
     */
    private void updateCamera() {
        // Calculate the min/max horizontal camera positions.
        float minCameraPosX = (Gdx.graphics.getWidth() * Constants.AREA_ZOOM) / 2f;
        float maxCameraPosX = (this.blocksWide * Constants.BLOCK_SIZE) - ((Gdx.graphics.getWidth() * Constants.AREA_ZOOM) / 2f);
        // Set the cameras position to match the players.
        float x = this.player.getX();
        // Clamp the camera horizontally to the screen.
        if (this.player.getX() < minCameraPosX) {
            x = minCameraPosX;
        } else if (this.player.getX() > maxCameraPosX) {
            x = maxCameraPosX;
        }
        // Set the camera to look at the player.
        camera.position.set(x, Constants.BLOCK_SIZE * Constants.AREA_TILE_HEIGHT / 2, 0);
    }

    /**
     * Check the status of the player and respond accordingly.
     */
    private void checkPlayerStatus() {
        // Has the player dropped out the bottom of the area?
        if (player.getY() < -(Constants.PLAYER_PHYSICS_SIZE_HEIGHT)) {
            // The player is below the area and should die.
            this.isFailed = true;
        }
        // Has the player made it through to the right of the area (completed).
        if (player.getX() > (Constants.BLOCK_SIZE * this.blocksWide)) {
            // The player has made it to the right of the area! This area is complete.
            this.isComplete = true;
        }
    }

    /**
     * Create the physics world.
     * @param areaName The name of the area.
     */
    private void createPhysicsWorld(String areaName) {
        // Create the physics world.
        physicsWorld = new NBPWorld(Constants.PHYSICS_GRAVITY);
        // Populate the physics world with static blocks based on the area block map image.
        Pixmap pixmap = new Pixmap(Gdx.files.internal("areas/" + areaName + "/block_map.png"));
        // Determine the block width of the area.
        this.blocksWide = pixmap.getWidth();
        // Go pixel by pixel ...
        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++)  {
                // ... Get the pixel colour ...
                int pixelColour = pixmap.getPixel(x, y);
                // ... If we have a black pixel then we have a static block.
                if (pixelColour == 255) {
                    // Create a new static block and add it to the physics world.
                    this.physicsWorld.addBox(new Block(x * Constants.BLOCK_SIZE, (pixmap.getHeight() - (1 + y)) * Constants.BLOCK_SIZE));
                }
            }
        }
        pixmap.dispose();
    }
}
