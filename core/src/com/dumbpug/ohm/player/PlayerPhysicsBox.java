package com.dumbpug.ohm.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;

/**
 * Physics box for a player.
 */
public class PlayerPhysicsBox extends Box {
    /** The characters facing direction.*/
    protected FacingDirection facingDirection = FacingDirection.RIGHT;

    /**
     * Creates a new instance of the PlayerPhysicsBox class.
     * @param x
     * @param y
     */
    public PlayerPhysicsBox(float x, float y) {
        super(x, y, Constants.PLAYER_PHYSICS_SIZE_WIDTH, Constants.PLAYER_PHYSICS_SIZE_WIDTH, BoxType.KINETIC);
        setFriction(Constants.CHARACTER_PHYSICS_FRICTION);
        setRestitution(Constants.CHARACTER_PHYSICS_RESTITUTION);
        // Set max velocity for this character.
        setMaxVelocityX(Constants.CHARACTER_PHYSICS_MAX_VELOCITY);
        setMaxVelocityY(Constants.CHARACTER_PHYSICS_MAX_VELOCITY * 1.5f);
        // Give the player physics box a name.
        this.setName("PLAYER");
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
        this.facingDirection = FacingDirection.LEFT;
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
        this.facingDirection = FacingDirection.RIGHT;
    }

    /**
     * Move the character down.
     */
    public void moveDown() {
        // Calculate how to apply an impulse to this character so that its moving speed is defined
        // by a value lower that its max velocity. In this case, a walking speed.
        if(this.getVelY() > -getMaxWalkingVelocity()) {
            if((-getMaxWalkingVelocity() - this.getVelY()) > getWalkingImpulse()) {
                applyImpulse(0f, -getMaxWalkingVelocity() + this.getVelY());
            } else {
                applyImpulse(0f, -getWalkingImpulse());
            }
        }
    }

    /**
     * Move the character up.
     */
    public void moveUp() {
        // Calculate how to apply an impulse to this character so that its moving speed is defined
        // by a value lower that its max velocity. In this case, a walking speed.
        if(this.getVelY() < getMaxWalkingVelocity()) {
            if((getMaxWalkingVelocity() - this.getVelY()) < getWalkingImpulse()) {
                applyImpulse(0f, getMaxWalkingVelocity() - this.getVelY());
            } else {
                applyImpulse(0f, getWalkingImpulse());
            }
        }
    }

    /**
     * Get whether the character is currently idle (not moving at all)
     * @return is idle.
     */
    public boolean isIdle() {
        return (getVelX() < 0.2f && getVelX() > -0.2f) && (getVelY() < 0.2f && getVelY() > -0.2f);
    }

    /**
     * Get the facing direction of the character physics box.
     * @return facing direction.
     */
    public FacingDirection getFacingDirection() { return this.facingDirection; }

    @Override
    public void onSensorEntry(Sensor sensor, Box enteredBox) { }

    @Override
    public void onSensorExit(Sensor sensor, Box exitedBox) { }

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
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, IntersectionPoint originAtCollision) {}

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}