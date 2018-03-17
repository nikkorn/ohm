package com.dumbpug.ohm.area.pickup;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;
import com.dumbpug.ohm.player.Player;

import java.util.ArrayList;

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
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
