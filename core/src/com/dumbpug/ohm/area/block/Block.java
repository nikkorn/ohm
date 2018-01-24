package com.dumbpug.ohm.area.block;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * Represents a plain world block.
 */
public class Block extends Box {

    /** The block details. */
    protected BlockDetails details;

    /**
     * Creates an instance of the Block class.
     * @param x
     * @param y
     * @param details
     */
    public Block(float x, float y, BlockDetails details) {
        super(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, BoxType.STATIC);
        this.setName("Block");
        this.details = details;
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