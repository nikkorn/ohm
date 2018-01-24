package com.dumbpug.ohm.area;

import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * A boundary box to place around the area.
 */
public class BoundaryBox extends Box {

    /**
     * Creates an instance of the BoundaryBox class.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public BoundaryBox(float x, float y, float width, float height) {
        super(x, y, width, height, BoxType.STATIC);
        this.setName("AreaBoundary");
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, NBPIntersectionPoint originAtCollision) {}

    @Override
    protected void onSensorEntry(NBPSensor sensor, Box enteredBox) {}

    @Override
    protected void onSensorExit(NBPSensor sensor, Box exitedBox) {}

    @Override
    protected boolean onBloomPush(Bloom bloom, float angleOfForce, float force, float distance) { return false; }

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
