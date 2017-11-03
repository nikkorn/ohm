package com.dumbpug.ohm.signalling.transmitters;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.NBPBloom;
import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPBoxType;
import com.dumbpug.ohm.nbp.NBPIntersectionPoint;
import com.dumbpug.ohm.nbp.NBPSensor;
import com.dumbpug.ohm.signalling.Colour;
import java.util.EnumSet;

/**
 * A beacon which emits a signal.
 */
public class Beacon extends NBPBox implements SignalTransmitter {

    /** The colour of the beacon. */
    private Colour colour;

    /** Flag defining whether the beacon is currently transmitting a signal. */
    private boolean isTransmitting;

    /**
     * Create a new instance of the Beacon class.
     * @param x
     * @param y
     * @param colour
     * @param isTransmitting
     */
    public Beacon(float x, float y, Colour colour, boolean isTransmitting) {
        super(x, y, Constants.SIGNALLING_BEACON_WIDTH, Constants.SIGNALLING_BEACON_HEIGHT, NBPBoxType.GHOST);
        this.colour         = colour;
        this.isTransmitting = isTransmitting;
    }

    /**
     * Get the signal colours currently being transmitted by this beacon.
     * @return transmitted signal colours
     */
    @Override
    public EnumSet<Colour> getSignals() {
        // Return an empty set if we are not transmitting.
        if (this.isTransmitting) {
            return EnumSet.of(colour);
        } else {
            return EnumSet.noneOf(Colour.class);
        }
    }

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
