package com.dumbpug.ohm.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.nbp.NBPWorld;
import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.EmitterDetails;
import com.dumbpug.ohm.particles.IEmitterDetails;
import com.dumbpug.ohm.particles.electro.ElectroParticle;
import com.dumbpug.ohm.particles.electro.ElectroParticleGenerator;
import com.dumbpug.ohm.resources.PlayerResources;

/**
 * The player.
 */
public class Player extends com.dumbpug.ohm.character.Character {

    /** The players physics box. */
    private PlayerPhysicsBox physicsBox;

    /** The player electro particle emitter. */
    private Emitter electroEmitter;

    /**
     * Initialise a new instance of the Player class.
     */
    public Player() {
        // Initialise our players physics box.
        this.physicsBox = new PlayerPhysicsBox(this, 0, 0);
        // Create the players electro emitter.
        createElectroEmitter();
    }

    /**
     * Set the players position.
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.physicsBox.setX(x);
        this.physicsBox.setY(y);
    }

    /**
     * Get the players X position.
     * @return X position
     */
    public float getX() { return this.physicsBox.getX(); }

    /**
     * Get the players Y position.
     * @return Y position
     */
    public float getY() { return this.physicsBox.getY(); }

    /**
     * Add the players physics box to the specified physics world.
     * @param world
     * @param x
     * @param y
     */
    public void addToPhysicsWorld(NBPWorld world, float x, float y) {
        // Set the position of the players physics box.
        this.setPosition(x, y);
        // Add the players physics box to the world.
        world.addBox(this.physicsBox);
    }

    /**
     * Move the character to the left.
     */
    public void moveLeft() { this.physicsBox.moveLeft(); }

    /**
     * Move the character to the right.
     */
    public void moveRight() { this.physicsBox.moveRight(); }

    /**
     * Make the character jump if he can.
     * @return true if character was able to jump
     */
    public boolean jump() { return this.physicsBox.jump(); }

    /**
     * Tick the player.
     */
    public void tick() {
        // Update the players electro particle emitter.
        this.electroEmitter.update();
    }

    @Override
    public void draw(SpriteBatch batch) {
        // Draw the electro emitter particles.
        this.electroEmitter.draw(batch);
        // Calculate the x position of where to draw the sprite.
        float x = this.physicsBox.getX() - ((Constants.PLAYER_SIZE - Constants.PLAYER_PHYSICS_SIZE_WIDTH) / 2f);
        // Which way is the player facing?
        if (this.physicsBox.getFacingDirection() == Direction.LEFT) {
            if(!physicsBox.isTouchingFloor()) {
                // Are we prepped for a wall jump?
                if (physicsBox.isPreppedForWallJump()) {
                    batch.draw(PlayerResources.ohm_wall_jumping_right, x, this.physicsBox.getY());
                } else {
                    // If our player is airborne, then draw airborne body based on whether we are ascending or descending
                    if (physicsBox.getVely() > 0) {
                        batch.draw(PlayerResources.ohm_jumping_left, x, this.physicsBox.getY());
                    } else {
                        batch.draw(PlayerResources.ohm_falling_left, x, this.physicsBox.getY());
                    }
                }
            } else if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_left, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_left.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        } else {
            if(!physicsBox.isTouchingFloor()) {
                // Are we prepped for a wall jump?
                if (physicsBox.isPreppedForWallJump()) {
                    batch.draw(PlayerResources.ohm_wall_jumping_left, x, this.physicsBox.getY());
                } else {
                    // If our player is airborne, then draw airborne body based on whether we are ascending or descending
                    if (physicsBox.getVely() > 0) {
                        batch.draw(PlayerResources.ohm_jumping_right, x, this.physicsBox.getY());
                    } else {
                        batch.draw(PlayerResources.ohm_falling_right, x, this.physicsBox.getY());
                    }
                }
            } else if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_right, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_right.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        }
    }

    /**
     * Create the electro particle emitter for the player.
     */
    private void createElectroEmitter() {
        // Create an explosion emitter/particle generator.
        ElectroParticleGenerator electroParticleGenerator = new ElectroParticleGenerator();
        this.electroEmitter = new Emitter(electroParticleGenerator);
        // Set the emitter details. This will map the emitter position to the players position.
        this.electroEmitter.setEmitterDetails(new ElectroParticleEmitterDetails(this));
        // Set the emitter activity.
        this.electroEmitter.setEmitterActivity(electroParticleGenerator);
    }
}
