package com.dumbpug.ohm.character.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.CharacterPhysicsBox;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;

/**
 * The physics box for the player.
 * Handles basic character physics actions such as walking and jumping, but includes ability to wall jump.
 */
public class PlayerPhysicsBox extends CharacterPhysicsBox {
    /** Can the character wall jump to the left? */
    private boolean canWallJumpLeft = false;
    /** Can the character wall jump to the right? */
    private boolean canWallJumpRight = false;
    /** Whether the player is being speedy. */
    private boolean isSpeedy = false;

    /**
     * Creates a new instance of the PlayerPhysicsBox class.
     * @param x
     * @param y
     */
    public PlayerPhysicsBox(float x, float y) {
        super(x, y, Constants.PLAYER_PHYSICS_SIZE_WIDTH, Constants.PLAYER_PHYSICS_SIZE_HEIGHT);
        // Create the wall sensors for detecting contact with walls.
        createRightWallSensor();
        createLeftWallSensor();
        // Give the player physics box a name.
        this.setName("PLAYER");
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
                setVelX(0);
                setVelY(0);
                applyImpulse(-Constants.CHARACTER_WALL_JUMP_X_OFFSET, Constants.CHARACTER_JUMPING_IMPULSE);
                // Character was able to jump.
                return true;
            } else if (this.canWallJumpRight) {
                this.canWallJumpRight = false;
                // Change the facing direction of this physics box.
                this.facingDirection = Direction.RIGHT;
                // Do a wall jump!
                setVelX(0);
                setVelY(0);
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
     * Set whether this player is currently being speedy.
     * @param isSpeedy Whether this player is currently being speedy.
     */
    public void setSpeedy(boolean isSpeedy) { this.isSpeedy = isSpeedy; }

    @Override
    public void onSensorEntry(Sensor sensor, Box enteredBox) {
        super.onSensorEntry(sensor, enteredBox);
        // Check whether this is one of our wall sensors, contact with a static block means we can wall jump.
        if(sensor.getName().equals("wall_sensor_right")) {
            // If we are against any static block then we can wall jump off of it.
            if(enteredBox.getType() == BoxType.STATIC) {
                this.canWallJumpLeft = true;
            }
        }
        if(sensor.getName().equals("wall_sensor_left")) {
            // If we are against any static block then we can wall jump off of it.
            if(enteredBox.getType() == BoxType.STATIC) {
                this.canWallJumpRight = true;
            }
        }
    }

    @Override
    public void onSensorExit(Sensor sensor, Box exitedBox) {
        super.onSensorExit(sensor, exitedBox);
        // We have stopped being against a static block. If a wall sensor is no longer in contact
        // with ANY static blocks then we cannot wall jump any more.
        if(sensor.getName().equals("wall_sensor_right")) {
            // Check that the wall sensor left a static box.
            if(exitedBox.getType() == BoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isStillAgainstWall = false;
                for(Box box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == BoxType.STATIC) {
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
            if(exitedBox.getType() == BoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isStillAgainstWall = false;
                for(Box box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == BoxType.STATIC) {
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
     * Create the right wall sensor.
     */
    private void createRightWallSensor() {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = Constants.PLAYER_PHYSICS_SIZE_WIDTH / 2f;
        float sensorWidth  = 1;
        float sensorPosX   = this.getX() + Constants.PLAYER_PHYSICS_SIZE_WIDTH;
        float sensorPosY   = this.getY() + (Constants.PLAYER_PHYSICS_SIZE_WIDTH / 4f);
        // Create the sensor.
        Sensor rightWallSensor = new Sensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
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
        Sensor leftWallSensor = new Sensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        leftWallSensor.setName("wall_sensor_left");
        // Attach the sensor to the character box.
        attachSensor(leftWallSensor);
    }

    /**
     * Get the movement modifier for the player.
     * @return movement modifier
     */
    private float getMovementModifier() { return this.isSpeedy ? Constants.PLAYER_ELECTRO_WALKING_MODIFIER : 1f; }

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
