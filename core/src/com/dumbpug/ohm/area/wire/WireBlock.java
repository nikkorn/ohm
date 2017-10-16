package com.dumbpug.ohm.area.wire;

import java.util.HashMap;

/**
 * Represents a block in the game which contains wires the player can pass through.
 */
public class WireBlock {

    /** The map of passengers moving through wire segments in this block. */
    private HashMap<Passenger, WireSegmentMovement> movements = new HashMap<Passenger, WireSegmentMovement>();

    /** Flags defining the entry points for the block. */
    private boolean hasEntryLeft;
    private boolean hasEntryTop;
    private boolean hasEntryRight;
    private boolean hasEntryBottom;

    /**
     * Create a new instance of the WireBlock class.
     * @param hasEntryLeft
     * @param hasEntryTop
     * @param hasEntryRight
     * @param hasEntryBottom
     */
    public WireBlock(boolean hasEntryLeft, boolean hasEntryTop, boolean hasEntryRight, boolean hasEntryBottom) {
        this.hasEntryLeft   = hasEntryLeft;
        this.hasEntryTop    = hasEntryTop;
        this.hasEntryRight  = hasEntryRight;
        this.hasEntryBottom = hasEntryBottom;
    }

    /**
     * Add a passenger moving through this block.
     */
    public void addPassenger(Passenger passenger, Direction directionOfEntry) {
        movements.put(passenger, new WireSegmentMovement(passenger, directionOfEntry, MovementType.ENTRY));
    }

    /**
     * Update this wire block and the passengers moving through it.
     */
    public void update() {
        // TODO Update movement of each passenger.
        // TODO If an entry movement is complete, add an exit movement, taking directional input when dealing with a fork.
        // TODO If an exit movement is complete. Add passenger to list of exiting passengers and remove from movement list.
    }
}
