package com.dumbpug.ohm.resources;

import com.dumbpug.ohm.player.PlayerColour;

/**
 * StatusBar Resources for a particular player colour.
 */
public class StatusBarResources {

    /**
     * Creates a new instance of the StatusBarResources class.
     * @param colour The player colour.
     */
    public StatusBarResources(PlayerColour colour) {
        // Load the resources from disk;
        this.load(colour);
    }

    /**
     * Load the resources.
     * @param colour The player colour to load resources for.
     */
    private void load(PlayerColour colour) {
    }
}
