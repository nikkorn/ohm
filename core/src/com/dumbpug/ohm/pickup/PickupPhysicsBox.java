package com.dumbpug.ohm.pickup;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;

/**
 * A physics box for a pickup.
 */
public class PickupPhysicsBox extends Box {

    /**
     * Create a new instance of the PickupPhysicsBox class.
     * @param x
     * @param y
     */
    public PickupPhysicsBox(float x, float y) {
        super(x, y, Constants.PICKUP_SIZE, Constants.PICKUP_SIZE, BoxType.KINETIC);
        this.setName("PICKUP");
    }

    /**
     * Get whether the pickup is currently idle (not moving at all)
     * @return is idle.
     */
    public boolean isIdle() {
        return (getVelX() < 0.2f && getVelX() > -0.2f) && (getVelY() < 0.2f && getVelY() > -0.2f);
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, IntersectionPoint originAtCollision) {}

    @Override
    protected void onSensorEntry(Sensor sensor, Box enteredBox) {}

    @Override
    protected void onSensorExit(Sensor sensor, Box exitedBox) {}

    @Override
    protected boolean onBloomPush(Bloom bloom, float angleOfForce, float force, float distance) { return false; }

    @Override
    protected void onBeforeUpdate() {
        // Reduce the pickup movement velocity over time so that it doesn't slide everywhere.
        this.setVelY(this.isIdle() ? 0 : this.getVelY() * 0.9f);
        this.setVelX(this.isIdle() ? 0 : this.getVelX() * 0.9f);
    }

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
