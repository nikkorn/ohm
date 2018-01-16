package com.dumbpug.ohm.character.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.CharacterPhysicsBox;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * The physics box for the player.
 * Handles basic character physics actions such as walking and jumping, but includes ability to wall jump.
 */
public class PlayerPhysicsBox extends CharacterPhysicsBox {

    /** The player. */
    private Player player;

    /** Can the character wall jump to the left? */
    private boolean canWallJumpLeft = false;

    /** Can the character wall jump to the right? */
    private boolean canWallJumpRight = false;

    /**
     * Creates a new instance of the PlayerPhysicsBox class.
     * @param player
     * @param x
     * @param y
     */
    public PlayerPhysicsBox(Player player, float x, float y) {
        super(player, x, y, Constants.PLAYER_PHYSICS_SIZE_WIDTH, Constants.PLAYER_PHYSICS_SIZE_HEIGHT);
        this.player = player;
        // Create the wall sensors for detecting contact with walls.
        createRightWallSensor();
        createLeftWallSensor();
        // Give the player physics box a name.
        this.setName("PLAYER");
    }

    /**
     * Create the right wall sensor.
     */
    private void createRightWallSensor() {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = Constants.PLAYER_PHYSICS_SIZE_WIDTH / 2f;
        float sensorWidth  = 1;
        float sensorPosX   = this.getX() + Constants.PLAYER_PHYSICS_SIZE_WIDTH;
        float sensorPosY   = this.getY() + (Constants.PLAYER_PHYSICS_SIZE_WIDTH / 4f);
        // Create the sensor.
        NBPSensor rightWallSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        rightWallSensor.setName("wall_sensor_right");
        // Attach the sensor to the character box.
        attachSensor(rightWallSensor);
    }

    /**
     * Create the left wall sensor.
     */
    private void createLeftWallSensor() {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = Constants.PLAYER_PHYSICS_SIZE_WIDTH / 2f;
        float sensorWidth  = 1;
        float sensorPosX   = this.getX() - sensorWidth;
        float sensorPosY   = this.getY() + (Constants.PLAYER_PHYSICS_SIZE_WIDTH / 4f);
        // Create the sensor.
        NBPSensor leftWallSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        leftWallSensor.setName("wall_sensor_left");
        // Attach the sensor to the character box.
        attachSensor(leftWallSensor);
    }

    /**
     * Get whether the player is prepped for a wall jump.
     * @return is prepped for wall jump.
     */
    public boolean isPreppedForWallJump() { return (!this.isTouchingFloor()) && (this.canWallJumpLeft || this.canWallJumpRight); }

    /**
     * Make the player jump if he can.
     * If the player fails to jump normally, they will attempt a wall jump.
     * @return true if character was able to jump.
     */
    public boolean jump() {
        // Attempt to make the character jump.
        boolean hasJumped = super.jump();
        // If the character was not able to jump normally, maybe they are in a position to wall jump.
        if (!hasJumped){
            // Determine whether we can wall jump instead.
            if (this.canWallJumpLeft) {
                this.canWallJumpLeft = false;
                // Change the facing direction of this physics box.
                this.facingDirection = Direction.LEFT;
                // Do a wall jump!
                setVelx(0);
                setVely(0);
                applyImpulse(-Constants.CHARACTER_WALL_JUMP_X_OFFSET, Constants.CHARACTER_JUMPING_IMPULSE);
                // Character was able to jump.
                return true;
            } else if (this.canWallJumpRight) {
                this.canWallJumpRight = false;
                // Change the facing direction of this physics box.
                this.facingDirection = Direction.RIGHT;
                // Do a wall jump!
                setVelx(0);
                setVely(0);
                applyImpulse(Constants.CHARACTER_WALL_JUMP_X_OFFSET, Constants.CHARACTER_JUMPING_IMPULSE);
                // Character was able to jump.
                return true;
            } else {
                // The player could not jump at all.
                return false;
            }
        } else {
            // The player jumped normally.
            return true;
        }
    }

    /**
     * Get the movement modifier for the player.
     * @return movement modifier
     */
    private float getMovementModifier() {
        com.dumbpug.ohm.character.player.ElectroChargeLevel electroChargeLevel = this.player.getElectroChargeLevel();
        // We can run super fast if we are using electro charge! (As long as it isn't depleted)
        if (electroChargeLevel.isEnabled() && electroChargeLevel.hasCharge()) {
            return Constants.PLAYER_ELECTRO_WALKING_MODIFIER;
        } else {
            return 1f;
        }
    }

    @Override
    public void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
        super.onSensorEntry(sensor, enteredBox);
        // Check whether this is one of our wall sensors, contact with a static block means we can wall jump.
        if(sensor.getName().equals("wall_sensor_right")) {
            // If we are against any static block then we can wall jump off of it.
            if(enteredBox.getType() == NBPBoxType.STATIC) {
                this.canWallJumpLeft = true;
            }
        }
        if(sensor.getName().equals("wall_sensor_left")) {
            // If we are against any static block then we can wall jump off of it.
            if(enteredBox.getType() == NBPBoxType.STATIC) {
                this.canWallJumpRight = true;
            }
        }
    }

    @Override
    public void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {
        super.onSensorExit(sensor, exitedBox);
        // We have stopped being against a static block. If a wall sensor is no longer in contact
        // with ANY static blocks then we cannot wall jump any more.
        if(sensor.getName().equals("wall_sensor_right")) {
            // Check that the wall sensor left a static box.
            if(exitedBox.getType() == NBPBoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isStillAgainstWall = false;
                for(NBPBox box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == NBPBoxType.STATIC) {
                        // We are still against a static block!
                        isStillAgainstWall = true;
                        break;
                    }
                }
                // Set the flag to show whether we can still wall jump.
                this.canWallJumpLeft = isStillAgainstWall;
            }
        }
        if(sensor.getName().equals("wall_sensor_left")) {
            // Check that the wall sensor left a static box.
            if(exitedBox.getType() == NBPBoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isStillAgainstWall = false;
                for(NBPBox box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == NBPBoxType.STATIC) {
                        // We are still against a static block!
                        isStillAgainstWall = true;
                        break;
                    }
                }
                // Set the flag to show whether we can still wall jump.
                this.canWallJumpRight = isStillAgainstWall;
            }
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
