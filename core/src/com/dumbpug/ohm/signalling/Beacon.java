package com.dumbpug.ohm.signalling;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.NBPBloom;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;

/**
 * A beacon which emits a signal.
 */
public class Beacon extends NBPBox {

    /** The colour of the beacon. */
    private Colour colour;

    /** The colour of the beacon. */
    private boolean isActive;

    /**
     * Create a new instance of the Beacon class.
     * @param x
     * @param y
     * @param colour
     * @param isActive
     */
    public Beacon(float x, float y, Colour colour, boolean isActive) {
        super(x, y, Constants.SIGNALLING_BEACON_WIDTH, Constants.SIGNALLING_BEACON_HEIGHT, NBPBoxType.GHOST);
        this.colour   = colour;
        this.isActive = isActive;
    }

    /**
     * Gets whether the beacon is active.
     * @return is active
     */
    public boolean isActive() { return isActive; }

    /**
     * Sets whether the beacon is active.
     * @param active
     */
    public void setActive(boolean active) { isActive = active; }

    /**
     * Gets the colour of the beacon.
     * @return colour
     */
    public Colour getColour() { return colour; }

    @Override
    protected void onCollisonWithKineticBox(NBPBox collidingBox, NBPIntersectionPoint kinematicBoxOriginAtCollision) {}

    @Override
    protected void onCollisonWithStaticBox(NBPBox collidingBox, NBPIntersectionPoint originAtCollision) {}

    @Override
    protected void onSensorEntry(NBPSensor sensor, NBPBox enteredBox) {}

    @Override
    protected void onSensorExit(NBPSensor sensor, NBPBox exitedBox) {}

    @Override
    protected boolean onBloomPush(NBPBloom bloom, float angleOfForce, float force, float distance) {
        // TODO Handle a bloom which represents a shock from ohm.
        // This will update the active state of this beacon.
        return false;
    }

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
