package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.character.player.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an area in game.
 */
public class Area {
    /** The area camera. */
    private OrthographicCamera camera;
    /** The physics world for this area. */
    private Environment physicsEnvironment;
    /** The platforms that make up this area. */
    private ArrayList<Platform> platforms;
    /** The player for the area. */
    private Player player;

    /**
     * Create a new instance of the Area class.
     * @param player
     */
    public Area(Player player) {
        // Create the physics world.
        this.physicsEnvironment = new Environment();
        // Create the platforms.
        createPlatforms();
        // Add player to this area at the player spawn.
        this.player = player;
        player.addToPhysicsWorld(this.physicsEnvironment, 50, 50);
        // Create the area camera.
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.AREA_ZOOM;
        camera.position.set(70, 70, 0);
    }

    /**
     * Create the area platforms.
     */
    private void createPlatforms() {
        this.platforms = new ArrayList<Platform>();
        for (int x = 0; x < Constants.AREA_PLATFORMS_SIZE; x++) {
            for (int y = 0; y < Constants.AREA_PLATFORMS_SIZE; y++) {
                Platform platform = new Platform(x, y);

                // Lower some platforms for funsies!
                platform.setRaised(new Random().nextBoolean());

                this.platforms.add(platform);
            }
        }
    }

    /**
     * Tick the area.
     */
    public void tick() {
        // Set the camera to look at the player.
        // camera.position.set(this.player.getX(), Constants.BLOCK_SIZE * Constants.AREA_TILE_HEIGHT / 2, 0);
    }

    /**
     * Update the physics world.
     */
    public void updatePhysicsWorld() {
        // Update the physics world.
        physicsEnvironment.update();
    }

    /**
     * Get the area camera.
     * @return The area camera.
     */
    public OrthographicCamera getCamera() { return this.camera; }

    /**
     * Get the area player.
     * @return The area player.
     */
    public Player getPlayer() { return this.player; }

    /**
     * Get the platforms in this area.
     * @return The platforms in this area.
     */
    public ArrayList<Platform> getPlatforms() { return this.platforms; }
}
