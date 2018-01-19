package com.dumbpug.ohm.character.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.electro.ElectroParticleGenerator;
import com.dumbpug.ohm.resources.PlayerResources;

/**
 * The player.
 */
public class Player extends com.dumbpug.ohm.character.Character {
    /** The player physics box. */
    private PlayerPhysicsBox physicsBox;
    /** The player electro particle emitter. */
    private Emitter electroEmitter;
    /** The player electro charge level. */
    private ElectroChargeLevel electroChargeLevel;

    /**
     * Initialise a new instance of the Player class.
     * @param playerPhysicsBox The player physics box.
     */
    public Player(PlayerPhysicsBox playerPhysicsBox) {
        super(playerPhysicsBox);
        this.physicsBox = playerPhysicsBox;
        // Create the players electro emitter.
        createElectroEmitter();
        // Create the players electro charge level.
        this.electroChargeLevel = new ElectroChargeLevel();
    }

    /**
     * Tick the player.
     */
    public void tick() {
        // Update the players electro particle emitter.
        this.electroEmitter.update();
        // Update the players electro charge.
        this.electroChargeLevel.update();
        // Update the electro particle generator to let it know whether we are using electro charge.
        boolean usingElectroCharge = electroChargeLevel.isEnabled() && electroChargeLevel.hasCharge();
        ((ElectroParticleGenerator) this.electroEmitter.getParticleGenerator()).setElectroChargeModeEnabled(usingElectroCharge);
        // If we are using our electro charge then our physics box will move faster.
        this.physicsBox.setSpeedy(usingElectroCharge);
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
     * Get the player's electro charge level.
     * @return The player's electro charge level.
     */
    public ElectroChargeLevel getElectroChargeLevel() { return this.electroChargeLevel; }

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
