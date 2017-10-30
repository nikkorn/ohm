package com.dumbpug.ohm.area.block;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.NBPBloom;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * Represents a plain world block.
 */
public class Block extends NBPBox {

    /** The block details. */
    protected BlockDetails details;

    /**
     * Creates an instance of the Block class.
     * @param x
     * @param y
     * @param details
     */
    public Block(float x, float y, BlockDetails details) {
        super(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, NBPBoxType.STATIC);
        this.setName("Block");
        this.details = details;
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