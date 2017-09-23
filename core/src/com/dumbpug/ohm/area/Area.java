package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
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

    /** The physics world for this area. */
    private NBPWorld physicsWorld;

    /** The area overlay. */
    private Texture overlay;

    /**
     * Create a new instance of the Area class.
     * @param areaName
     */
    public Area(String areaName) {
        // Create the physics world.
        this.createPhysicsWorld();
        // Set the area overlay.
        this.overlay = new Texture(Gdx.files.internal("areas/" + areaName + "/overlay.png"));
    }

    /**
     * Add the player to the area in the starting position.
     * @param player
     */
    public void addPlayer(Player player) {
        // TODO Add player physics box to physics world.
    }

    /**
     * Tick the area.
     */
    public void tick() {
        // Update the physics world.
        physicsWorld.update();
        // TODO Process input.
        // TODO Update camera.
    }

    /**
     * Draw the area.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Draw background.
        batch.draw(AreaResources.background, 0, 0);
        // TODO Draw game entities.
        // TODO Draw player.
        // Draw overlay.
        batch.draw(this.overlay, 0, 0);
    }

    /**
     * Create the physics world.
     */
    private void createPhysicsWorld() {
        // Create the physics world.
        physicsWorld = new NBPWorld(Constants.PHYSICS_GRAVITY);
        // TODO Populate the physics world with static blocks based on the area block map image.
    }
}
