package com.dumbpug.ohm.area.block;

import java.util.HashMap;

/**
 * Represents a block in the game which contains wires the player can pass through.
 */
public class WireBlock extends Block {

    /** The map of passengers moving through wire segments in this block. */
    private HashMap<Passenger, com.dumbpug.ohm.area.block.WireSegmentMovement> movements = new HashMap<Passenger, com.dumbpug.ohm.area.block.WireSegmentMovement>();

    /**
     * Create a new instance of the WireBlock class.
     * @param x
     * @param y
     * @param details
     */
    public WireBlock(float x, float y, BlockDetails details) {
        super(x, y, details);
        this.setName("WireBlock");
    }

    /**
     * Add a passenger moving through this block.
     */
    public void addPassenger(Passenger passenger, com.dumbpug.ohm.Direction directionOfEntry) {
        movements.put(passenger, new com.dumbpug.ohm.area.block.WireSegmentMovement(passenger, directionOfEntry, MovementType.ENTRY));
    }

    /**
     * Update this wire block and the passengers moving through it.
     */
    public void update() {
        // TODO Update movement of each passenger.
        // TODO If an entry movement is complete, add an exit movement, taking directional input when dealing with a fork.
        // TODO If an exit movement is complete. Add passenger to list of exiting passengers and remove from movement list.
    }

    /**
     * Create sensors on the sides of this block which a wire is linked to and that the player can access (not against another block).
     */
    private void createEntrySensors() {

    }
}
