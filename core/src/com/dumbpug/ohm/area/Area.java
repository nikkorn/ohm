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

    /** The area overlay. */
    private Texture overlay;

    /** The player. */
    private Player player;

    /**
     * Create a new instance of the Area class.
     * @param areaName
     */
    public Area(String areaName) {
        // Create the physics world.
        this.createPhysicsWorld(areaName);
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
        // Add player to this area. TODO Set actual starting position.
        this.player = player;
        player.addToPhysicsWorld(this.physicsWorld, 32, 48);
    }

    /**
     * Tick the area.
     */
    public void tick() {
        // Update the physics world.
        physicsWorld.update();
        // Process input.
        processInput();
        // Allow the player to grab the camera.
        player.grabCamera(camera);
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
        // TODO Draw game entities.
        // Draw player.
        player.draw(batch);
        // Draw overlay.
        batch.draw(this.overlay, 0, 0);
    }

    /**
     * Process user input.
     */
    private void processInput() {
        // The way we handle input depends n whether we are running on ouya or not.
        if (Ouya.runningOnOuya) {

            // Handle Ouya input.
            for (Controller controller : Controllers.getControllers()) {

                if (controller.getButton(Ouya.BUTTON_O)) {
                    player.jump();
                }

                float leftXAxis = controller.getAxis(Ouya.AXIS_LEFT_X);

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
     * Create the physics world.
     */
    private void createPhysicsWorld(String areaName) {
        // Create the physics world.
        physicsWorld = new NBPWorld(Constants.PHYSICS_GRAVITY);
        // Populate the physics world with static blocks based on the area block map image.
        Pixmap pixmap = new Pixmap(Gdx.files.internal("areas/" + areaName + "/block_map.png"));
        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++)  {
                // Get the pixel colour.
                int pixelColour = pixmap.getPixel(x, y);
                // If we have a black pixel then we have a static block.
                if (pixelColour == 255) {
                    // Create a new static block and add it to the physics world.
                    this.physicsWorld.addBox(new Block(x * Constants.TILE_SIZE, (pixmap.getHeight() - (1 + y)) * Constants.TILE_SIZE));
                }
            }
        }
        pixmap.dispose();
    }
}
