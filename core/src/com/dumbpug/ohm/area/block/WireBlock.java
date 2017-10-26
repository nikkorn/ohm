package com.dumbpug.ohm.area.block;

import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

/**
 * Represents a block in the game which contains wires the player can pass through.
 */
public class WireBlock extends Block {

    /** The map of passengers moving through wire segments in this block. */
    private HashMap<Passenger, com.dumbpug.ohm.area.block.WireSegmentMovement> movements = new HashMap<Passenger, com.dumbpug.ohm.area.block.WireSegmentMovement>();

    /** Flags defining the entry points for the block. */
    private boolean hasEntryLeft;
    private boolean hasEntryTop;
    private boolean hasEntryRight;
    private boolean hasEntryBottom;

    /**
     * Create a new instance of the WireBlock class.
     * @param x
     * @param y
     */
    public WireBlock(float x, float y) {
        super(x, y);
        this.setName("WireBlock");
    }

    /**
     * Apply wire details to this wire block.
     * @param wireDetails
     * @param blockLeft
     * @param blockAbove
     * @param blockRight
     * @param blockBelow
     */
    public void applyWireDetails(JsonValue wireDetails, boolean blockLeft, boolean blockAbove, boolean blockRight, boolean blockBelow) {

    }

    /**
     * Add a passenger moving through this block.
     */
    public void addPassenger(Passenger passenger, Direction directionOfEntry) {
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
