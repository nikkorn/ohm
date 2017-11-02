package com.dumbpug.ohm.signalling;

import com.dumbpug.ohm.Direction;

/**
 * Represents a signal moving through a signalling system.
 */
public class Signal {

    /** The current direction of the signal. */
    private Direction direction = Direction.RIGHT;

    /** The current colour of the signal. */
    private Colour colour;
}
