package com.dumbpug.ohm.resources;

import com.dumbpug.ohm.player.PlayerColour;
import java.util.HashMap;

/**
 * HUD resources.
 */
public class HUDResources {
    /**
     * Mapping of player colours to status bar resources matching those colours.
     */
    private static HashMap<PlayerColour, StatusBarResources> statusBarResourceMap;

    /**
     * Load resources.
     */
    public static void load() {
        // Populate the status bar resource map.
        statusBarResourceMap = new HashMap<PlayerColour, StatusBarResources>() {{
            put(PlayerColour.RED, new StatusBarResources(PlayerColour.RED));
            put(PlayerColour.BLUE, new StatusBarResources(PlayerColour.BLUE));
            put(PlayerColour.GREEN, new StatusBarResources(PlayerColour.GREEN));
            put(PlayerColour.YELLOW, new StatusBarResources(PlayerColour.YELLOW));
        }};
    }
}
