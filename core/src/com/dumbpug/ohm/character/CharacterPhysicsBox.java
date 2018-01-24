package com.dumbpug.ohm.character;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * Physics box for a character.
 * Handles basic character physics actions such as walking and jumping.
 */
public class CharacterPhysicsBox<TCharacter extends Character> extends Box {
    /** Can the character jump? */
    private boolean canJump = false;
    /** The characters facing direction.*/
    protected Direction facingDirection = Direction.RIGHT;

    /**
     * Creates a new instance of the CharacterPhysicsBox class.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CharacterPhysicsBox(float x, float y, float width, float height) {
        super(x, y, width, height, BoxType.KINETIC);
        setFriction(Constants.CHARACTER_PHYSICS_FRICTION);
        setRestitution(Constants.CHARACTER_PHYSICS_RESTITUTION);
        // Set max velocity for this character.
        setMaxVelocityX(Constants.CHARACTER_PHYSICS_MAX_VELOCITY);
        setMaxVelocityY(Constants.CHARACTER_PHYSICS_MAX_VELOCITY * 1.5f);
        // Create a sensor and place it at the base of our character. This sensor will
        // be used to detect when we are standing on something static, thus allowing the character to jump.
        createBaseSensor(width, x, y);
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
        if(this.getVelX() > -getMaxWalkingVelocity()) {
            if((-getMaxWalkingVelocity() - this.getVelX()) > getWalkingImpulse()) {
                applyImpulse(-getMaxWalkingVelocity() + this.getVelX(), 0f);
            } else {
                applyImpulse(-getWalkingImpulse(), 0f);
            }
        }
        // Change the facing direction of this physics box.
        this.facingDirection = Direction.LEFT;
    }

    /**
     * Move the character to the right.
     */
    public void moveRight() {
        // Calculate how to apply an impulse to this character so that its moving speed is defined
        // by a value lower that its max velocity. In this case, a walking speed.
        if(this.getVelX() < getMaxWalkingVelocity()) {
            if((getMaxWalkingVelocity() - this.getVelX()) < getWalkingImpulse()) {
                applyImpulse(getMaxWalkingVelocity() - this.getVelX(), 0f);
            } else {
                applyImpulse(getWalkingImpulse(), 0f);
            }
        }
        // Change the facing direction of this physics box.
        this.facingDirection = Direction.RIGHT;
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
        if(canJump && this.getVelY() <= 0) {
            // Apply a vertical impulse.
            applyImpulse(0f, Constants.CHARACTER_JUMPING_IMPULSE);
            // Character was able to jump.
            return true;
        }
        // Player was not able to jump.
        return false;
    }

    /**
     * Get whether the character is currently idle (not moving at all)
     * @return is idle.
     */
    public boolean isIdle() {
        return (getVelX() < 0.2f && getVelX() > -0.2f) && (getVelY() < 0.2f && getVelY() > -0.2f);
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
     * Get the facing direction of the character physics box.
     * @return facing direction.
     */
    public Direction getFacingDirection() { return this.facingDirection; }

    @Override
    public void onSensorEntry(NBPSensor sensor, Box enteredBox) {
        // Check that this sensor is the one we have placed at the bottom of the box.
        if(sensor.getName().equals("base_sensor")) {
            // If we are on any static block then we can jump off of it.
            if(enteredBox.getType() == BoxType.STATIC) {
                // Set a flag to show that the player can now jump.
                this.canJump = true;
            }
        }
    }

    @Override
    public void onSensorExit(NBPSensor sensor, Box exitedBox) {
        // We have lifted off of a box, if this is a static box then check whether the sensor is
        // now not intersecting with any static boxes. if not then the player can no longer jump.
        // Firstly, make sure that this is the sensor that we placed at the base of the player.
        if(sensor.getName().equals("base_sensor")) {
            // Check that the sensor left a static box.
            if(exitedBox.getType() == BoxType.STATIC) {
                // Get all other intersecting boxes for this sensor, if none are static
                // then we can no longer jump as we are not resting on anything.
                boolean isRestingOnStaticBox = false;
                for(Box box : sensor.getIntersectingBoxes()) {
                    // Is this intersecting box static?
                    if(box.getType() == BoxType.STATIC) {
                        // We are still standing on a static box so we can still jump.
                        isRestingOnStaticBox = true;
                        break;
                    }
                }
                // Set the flag to show whether we can still jump.
                this.canJump = isRestingOnStaticBox;
            }
        }
    }

    /**
     * Get the max walking velocity of this character physics box.
     * @return max walking velocity.
     */
    protected float getMaxWalkingVelocity() { return Constants.CHARACTER_PHYSICS_MAX_WALKING_VELOCITY; }

    /**
     * Get the walking impulse value of this character physics box.
     * @return walking impulse value.
     */
    protected float getWalkingImpulse() { return Constants.CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE; }

    @Override
    protected boolean onBloomPush(Bloom bloom, float angleOfForce, float force, float distance) { return true; }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, NBPIntersectionPoint originAtCollision) {}

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}