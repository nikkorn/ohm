package com.dumbpug.ohm.signalling.transmitters;

import com.dumbpug.ohm.signalling.Colour;
import java.util.EnumSet;

/**
 * Represents a transmitter for a signal.
 */
public interface SignalTransmitter {

    /**
     * Gets a set of signal colours the transmitter is currently transmitting.
     * @return transmitted signal colours.
     */
    EnumSet<Colour> getSignals();
}