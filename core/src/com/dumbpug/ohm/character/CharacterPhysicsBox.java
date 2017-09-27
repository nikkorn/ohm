package com.dumbpug.ohm.character;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.NBPBloom;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * Physics box for a character.
 */
public class CharacterPhysicsBox extends NBPBox {

    /** Reference to our Character object. */
    protected Character character;

    /** Can the character jump? */
    private boolean canJump = false;

    /** Can the character wall jump to the left? */
    private boolean canWallJumpLeft = false;

    /** Can the character wall jump to the right? */
    private boolean canWallJumpRight = false;

    /**
     * Creates a new instance of the CharacterPhysicsBox class.
     * @param character
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CharacterPhysicsBox(Character character, float x, float y, float width, float height) {
        super(x, y, width, height, NBPBoxType.KINETIC);
        setFriction(Constants.CHARACTER_PHYSICS_FRICTION);
        setRestitution(Constants.CHARACTER_PHYSICS_RESTITUTION);
        // Set max velocity for this character.
        setMaxVelocityX(Constants.CHARACTER_PHYSICS_MAX_VELOCITY);
        setMaxVelocityY(Constants.CHARACTER_PHYSICS_MAX_VELOCITY * 1.5f);
        // Create a sensor and place it at the base of our character. This sensor will
        // be used to detect when we are standing on something static, thus allowing
        // the character to jump.
        createBaseSensor(width, x, y);
        // Create the wall sensors for detecting contact with walls.
        createRightWallSensor(width, height, x, y);
        createLeftWallSensor(width, height, x, y);
        // Grab reference to our character.
        this.character = character;
    }

    /**
     * Create the right wall sensor.
     * @param width
     * @param height
     * @param x
     * @param y
     */
    private void createRightWallSensor(float width, float height, float x, float y) {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = height / 2f;
        float sensorWidth  = 1;
        float sensorPosX   = x + width;
        float sensorPosY   = y + (height / 4f);
        // Create the sensor.
        NBPSensor rightWallSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        rightWallSensor.setName("wall_sensor_right");
        // Attach the sensor to the character box.
        attachSensor(rightWallSensor);
    }

    /**
     * Create the left wall sensor.
     * @param width
     * @param height
     * @param x
     * @param y
     */
    private void createLeftWallSensor(float width, float height, float x, float y) {
        // Create a sensor and place it to the right of our character to detect wall contact.
        float sensorHeight = height / 2f;
        float sensorWidth  = 1;
        float sensorPosX   = x - sensorWidth;
        float sensorPosY   = y + (height / 4f);
        // Create the sensor.
        NBPSensor leftWallSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        leftWallSensor.setName("wall_sensor_left");
        // Attach the sensor to the character box.
        attachSensor(leftWallSensor);
    }

    /**
     * Create a sensor at the base of the character.
     * @param width
     * @param x
     * @param y
     */
    private void createBaseSensor(float width, float x, float y) {
        // Create a sensor and place it at the base of our character. This sensor will
        // be used to detect when we are standing on something static, thus allowing
        // the character to jump.
        float sensorHeight = 1;
        float sensorWidth  = width;
        float sensorPosX   = x;
        float sensorPosY   = y - sensorHeight;
        // Create the sensor.
        NBPSensor baseSensor = new NBPSensor(sensorPosX, sensorPosY, sensorWidth, sensorHeight);
        // Give the sensor a name, this will be checked when notified by the sensor.
        baseSensor.setName("base_sensor");
        // Attach the sensor to the character box.
        attachSensor(baseSensor);
    }

    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        // Calculate how to apply an impulse to this character so that its moving speed is defined
        // by a value lower that its max velocity. In this case, a walking speed.
        if(this.getVelx() > -Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY) {
            if((-Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx()) > Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE) {
                applyImpulse(-Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx(), 0f);
            } else {
                applyImpulse(-Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE, 0f);
            }
        }
    }

    /**
     * Move the character to the right.
     */
    public void moveRight() {
        // Calculate how to apply an impulse to this character so that its moving speed is defined
        // by a value lower that its max velocity. In this case, a walking speed.
        if(this.getVelx() < Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY) {
            if((Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx()) < Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE) {
                applyImpulse(Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY - this.getVelx(), 0f);
            } else {
                applyImpulse(Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE, 0f);
            }
        }
    }

    /**
     * Make the character jump if he can.
     * @return true if character was able to jump
     */
    public boolean jump() {
        // Can we jump? (Are we on a static block?)
        // We should also not allow a jump to happen if our Y velocity
        // is above 0, this is due to the fact that you cannot jump
        // if you are already ascending. This fixes the issue where
        // sometimes the characters base sensor will intersect with a
        // static box while ascending upwards against it.
        if(canJump && this.getVely() <= 0) {
            // Apply a vertical impulse.
            applyImpulse(0f, Constants.CHARACTER_JUMPING_IMPULSE);
            // Character was able to jump.
            return true;
        } else {
            // Determine whether we can wall jump instead.
            if (this.canWallJumpLeft) {
                // Do a wall jump!
                applyImpulse(-1f, Constants.CHARACTER_JUMPING_IMPULSE * 1.5f);
                // Character was able to jump.
                return true;
            } else if (this.canWallJumpRight) {
                // Do a wall jump!
                applyImpulse(1f, Constants.CHARACTER_JUMPING_IMPULSE * 1.5f);
                // Character was able to jump.
                return true;
            }
        }
        // Player was not able to jump.
        return false;
    }
    /**
     * Get whether the character is currently idle (not moving at all)
     * @return is idle.
     */
    public boolean isIdle() {
        return (getVelx() < 0.2f && getVelx() > -0.2f) && (getVely() < 0.2f && getVely() > -0.2f);
    }

    /**
     * Get whether the character is touching the floor (if false then the character is airborne).
     * @return is touching floor.
     */
    public boolean isTouchingFloor() {
        // If the player can jump then he is touching the floor.
        return canJump;
    }

    /**
     * Return the character which this physics box represents.
     * @return character
     */
    public Character getCharacter() { return this.character; }

    @Override
    public void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {
        // Check that this sensor is the one we have placed at the bottom of the box.
        if(sensor.getName().equals("base_sensor")) {
            // If we are on any static block then we can jump off of it.
            if(enteredBox.getType() == NBPBoxType.STATIC) {
                // If the player was previously airborne, then this is a landing.
                if(!isTouchingFloor()) {
                    // Let our player instance know that we have landed.
                    character.onLanding();
                }
                // Set a flag to show that the player can now jump.
                this.canJump = true;
            }
        }
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
        // We have lifted off of a box, if this is a static box then check whether the sensor is
        // now not intersecting with any static boxes. if not then the player can no longer jump.
        // Firstly, make sure that this is the sensor that we placed at the base of the player.
        if(sensor.getName().equals("base_sensor")) {
            // Check that the sensor left a static box.
            if(exitedBox.getType() == NBPBoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isRestingOnStaticBox = false;
                for(NBPBox box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == NBPBoxType.STATIC) {
                        // We are still standing on a static box so we can still jump.
                        isRestingOnStaticBox = true;
                        break;
                    }
                }
                // Set the flag to show whether we can still jump.
                this.canJump = isRestingOnStaticBox;
            }
        }
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

    @Override
    protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
        // Return true as we want the force to affect this character box.
        return true;
    }

    @Override
    protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {}

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}