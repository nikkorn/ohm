package com.dumbpug.ohm.signalling.receivers;

import com.dumbpug.ohm.signalling.Colour;
import java.util.EnumSet;

/**
 * Represents a door which is opened via a signal.
 */
public class Door implements SignalReceiver {

    /** The colour of the door. */
    private Colour colour;

    /**
     * Create a new instance of the Door class.
     * @param x
     * @param y
     * @param colour The colour of the door.
     */
    public Door(float x, float y, Colour colour) { this.colour = colour; }

    @Override
    public void onSignalReceived(Colour signal) {
        // TODO Open the door for a period of time.
    }

    @Override
    public EnumSet<Colour> getReceiverColours() { return EnumSet.of(colour); }
}