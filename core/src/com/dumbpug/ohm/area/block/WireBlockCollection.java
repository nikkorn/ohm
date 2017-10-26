package com.dumbpug.ohm.area.block;

import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;

/**
 * A collection of wire blocks which manages block updates and the movement of passengers between blocks.
 */
public class WireBlockCollection {

    /** The blocks. */
    private ArrayList<WireBlock> blocks = new ArrayList<WireBlock>();

    /**
     * Create a new instance of the WireBlockCollection class.
     */
    private WireBlockCollection() {}

    /**
     * Create a new instance of the WireBlockCollection class.
     * @param blocks
     */
    private WireBlockCollection(ArrayList<WireBlock> blocks) {
        this.blocks = blocks;
    }

    /**
     * Create a wire block collection from JSON.
     * @param blocksJSON
     * @return the wire blocks collection.
     */
    public WireBlockCollection createFromJSON(JsonValue blocksJSON) {
        // We only care if we have any blocks defined in our JSON.
        if (blocksJSON != null && blocksJSON.isArray()) {
            // Create an intermediate list of wire blocks.
            ArrayList<WireBlock> pendingBlocks = new ArrayList<WireBlock>();
            // Go over each wire block defined in the JSON.
            for (JsonValue blockValue : blocksJSON.iterator()) {
                // TODO Create a new wire block based on blockValue.
            }
            // Return a potentially populated (if the array wasn't empty) WireBlockCollection.
            return new WireBlockCollection(pendingBlocks);
        }
        // Return an empty WireBlockCollection.
        return new WireBlockCollection();
    }

    /**
     * Update this wire blocks collection.
     */
    public void update() {
        // Update each block.
        for (WireBlock block : blocks) {
            block.update();
        }
    }
}
