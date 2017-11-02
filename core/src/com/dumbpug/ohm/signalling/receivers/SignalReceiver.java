package com.dumbpug.ohm.signalling.receivers;

import com.dumbpug.ohm.signalling.Colour;

/**
 * Represents a receiver for a signal.
 */
public interface SignalReceiver {

    /**
     * Called when a signal is received.
     */
    void onSignalReceived();

    /**
     * Get the colour of the signal that the receiver expects.
     * @return colour
     */
    Colour getReceiverColour();
}
