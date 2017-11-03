package com.dumbpug.ohm.signalling.receivers;

import com.dumbpug.ohm.signalling.Colour;
import java.util.EnumSet;

/**
 * Represents a receiver for a signal.
 */
public interface SignalReceiver {

    /**
     * Called when an accepted signal is received.
     * @param signal
     */
    void onSignalReceived(Colour signal);

    /**
     * Gets the set of colours of signals that the receiver accepts.
     * @return colours
     */
    EnumSet<Colour> getReceiverColours();
}