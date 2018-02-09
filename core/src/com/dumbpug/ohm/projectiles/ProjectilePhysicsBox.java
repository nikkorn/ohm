package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;

/**
 * A physics box for a projectile.
 */
public class ProjectilePhysicsBox extends Box {

    /**
     * Create a new instance of the ProjectilePhysicsBox class.
     */
    public ProjectilePhysicsBox(float size) {
        super(0, 0, size, size, BoxType.KINETIC);
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
