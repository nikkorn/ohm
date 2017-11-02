package com.dumbpug.ohm.area.block;

import com.dumbpug.ohm.*;

/**
 * Represents a movement along a wire segment.
 */
public class WireSegmentMovement {

    /** The length of the path still to follow. */
    private float distanceRemaining = Constants.WIRE_SEGMENT_SIZE;

    /** The length of the path still to follow. */
    private com.dumbpug.ohm.Direction direction;

    /** The type of wire movement. Either leaving or entering a wire block. */
    private MovementType type;

    /** The passenger doing the movement. */
    public Passenger passenger;

    /**
     * Create a new instance of the WireSegmentMovement class.
     * @param passenger
     * @param direction
     * @param type
     */
    public WireSegmentMovement(Passenger passenger, com.dumbpug.ohm.Direction direction, MovementType type) {
        this.passenger = passenger;
        this.direction = direction;
        this.type      = type;
    }

    /**
     * Move the passenger along the wire.
     */
    public void updatePassengerPosition() { distanceRemaining -= Constants.WIRE_MOVEMENT_STEP; }

    /**
     * Returns whether this movement complete.
     * @return is complete.
     */
    public boolean isCompleted() { return distanceRemaining <= 0f; }
}
