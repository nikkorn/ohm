package com.dumbpug.ohm.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.CharacterPhysicsBox;

/**
 * The physics box for the player.
 */
public class PlayerPhysicsBox extends CharacterPhysicsBox {

    /** The player. */
    private Player player;

    /**
     * Creates a new instance of the PlayerPhysicsBox class.
     * @param player
     * @param x
     * @param y
     */
    public PlayerPhysicsBox(Player player, float x, float y) {
        super(player, x, y, Constants.PLAYER_PHYSICS_SIZE_WIDTH, Constants.PLAYER_PHYSICS_SIZE_HEIGHT);
        this.player = player;
        this.setName("PLAYER");
    }

    /**
     * Get the movement modifier for the player.
     * @return movement modifier
     */
    private float getMovementModifier() {
        ElectroChargeLevel electroChargeLevel = this.player.getElectroChargeLevel();
        // We can run super fast if we are using electro charge! (As long as it isn't depleted)
        if (electroChargeLevel.isEnabled() && electroChargeLevel.hasCharge()) {
            return Constants.PLAYER_ELECTRO_WALKING_MODIFIER;
        } else {
            return 1f;
        }
    }

    /**
     * Get the max walking velocity of this character physics box.
     * @return max walking velocity.
     */
    protected float getMaxWalkingVelocity() { return Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY * getMovementModifier(); }

    /**
     * Get the walking impulse value of this character physics box.
     * @return walking impulse value.
     */
    protected float getWalkingImpulse() { return Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE * getMovementModifier(); }
}
