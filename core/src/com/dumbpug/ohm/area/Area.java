package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.area.block.BlockDetails;
import com.dumbpug.ohm.character.Character;
import com.dumbpug.ohm.nbp.NBPWorld;
import com.dumbpug.ohm.character.player.Player;
import com.dumbpug.ohm.resources.AreaResources;
import java.util.ArrayList;

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
    /** The characters in the area. */
    private ArrayList<Character> characters = new ArrayList<Character>();
    /** Flag defining whether this area is complete. */
    private boolean isComplete = false;
    /** Flag defining whether this area is failed (player died or dropped out). */
    private boolean isFailed = false;
    /** The area details. */
    private AreaDetails details;

    /**
     * Create a new instance of the Area class.
     * @param areaName The name of the area.
     */
    public Area(String areaName) {
        // Get the area details from disk.
        this.details = new AreaDetails(areaName, new JsonReader().parse(Gdx.files.internal("areas/" + areaName + "/details.json")));
        // Create the physics world.
        this.createPhysicsWorld(areaName);
        // Create the area camera.
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.AREA_ZOOM;
        camera.update();
        // Set the area overlay.
        this.overlay = new Texture(Gdx.files.internal("areas/" + areaName + "/overlay.png"));
    }

    /**
     * Add the player to the area in the starting position.
     * @param player The player.
     */
    public void addPlayer(Player player) {
        // Add player to this area at the player spawn.
        this.player = player;
        player.addToPhysicsWorld(this.physicsWorld, this.details.getSpawn().getX(), this.details.getSpawn().getY());
    }

    /**
     * Add the character to the area in the starting position.
     * @param character The character to add.
     * @param x
     * @param y
     */
    public void addCharacter(Character character, float x, float y) {
        // Add the character to the area physics world.
        character.addToPhysicsWorld(this.physicsWorld, x, y);
        // Add the character to the list of characters in the area.
        this.characters.add(character);
    }

    /**
     * Get the details of the area.
     * @return The area details.
     */
    public AreaDetails getDetails() { return this.details; }

    /**
     * Tick the area.
     */
    public void tick() {
        // Tick all of the characters in this area.
        for (Character character : this.characters) { character.tick(); }
        // Update the camera.
        updateCamera();
        // Check the status of the player.
        checkPlayerStatus();
    }

    /**
     * Update the physics world.
     */
    public void updatePhysicsWorld() {
        // Update the physics world.
        physicsWorld.update();
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
     * Update the specified camera to reflect the players position.
     */
    private void updateCamera() {
        // Calculate the min/max horizontal camera positions.
        float minCameraPosX = (Gdx.graphics.getWidth() * Constants.AREA_ZOOM) / 2f;
        float maxCameraPosX = (this.details.getWidth() * Constants.BLOCK_SIZE) - ((Gdx.graphics.getWidth() * Constants.AREA_ZOOM) / 2f);
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
        if (player.getX() > (Constants.BLOCK_SIZE * this.details.getWidth())) {
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
        // Go pixel by pixel ...
        for (int y = 0; y < pixmap.getHeight(); y++) {
            for (int x = 0; x < pixmap.getWidth(); x++)  {
                // If there is a block at this position we will need to create it.
                if (details.isBlockAt(x, y)) {
                    // Calculate the actual y position, as reading from the pixmap is flipped.
                    int flippedY = pixmap.getHeight() - (1 + y);
                    // Attempt to get block details for the current position.
                    BlockDetails blockDetails = this.details.getBlockDetails(x, y);
                    // The type of block we are creating depends on whether it is a wire block.
                    if (blockDetails.isWireBlock()) {
                        // Create a new static wire block and add it to the physics world.
                        this.physicsWorld.addBox(new com.dumbpug.ohm.area.block.WireBlock(x * Constants.BLOCK_SIZE, flippedY * Constants.BLOCK_SIZE, blockDetails));
                    } else {
                        // Create a new static block and add it to the physics world.
                        this.physicsWorld.addBox(new com.dumbpug.ohm.area.block.Block(x * Constants.BLOCK_SIZE, flippedY * Constants.BLOCK_SIZE, blockDetails));
                    }
                }
            }
        }

        // If any wire blocks were defined in the area details then ... TODO

        // Dispose of the pixmap.
        pixmap.dispose();
        // Create a block which stops the player going left off the screen.
        this.physicsWorld.addBox(new BoundaryBox(-10, 0, 10, Constants.BLOCK_SIZE * Constants.AREA_TILE_HEIGHT));
        // Create a block which stops the player going above the screen.
        this.physicsWorld.addBox(new BoundaryBox(0, Constants.BLOCK_SIZE * Constants.AREA_TILE_HEIGHT, Constants.BLOCK_SIZE * this.details.getWidth(), 10));
    }
}
