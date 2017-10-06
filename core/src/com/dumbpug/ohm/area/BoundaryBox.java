package com.dumbpug.ohm.area;

import com.dumbpug.ohm.nbp.NBPBloom;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * A boundary box to place around the area.
 */
public class BoundaryBox extends NBPBox {

    /**
     * Creates an instance of the BoundaryBox class.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public BoundaryBox(float x, float y, float width, float height) {
        super(x, y, width, height, NBPBoxType.STATIC);
        this.setName("AreaBoundary");
    }

    @Override
    protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {}

    @Override
    protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {}

    @Override
    protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {}

    @Override
    protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) { return false; }

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
