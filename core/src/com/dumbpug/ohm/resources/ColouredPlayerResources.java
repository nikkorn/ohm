package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.ohm.player.PlayerColour;

/**
 * Colour-specific player resources.
 */
public class ColouredPlayerResources {
    /**
     * The player marker.
     */
    private static Texture player_marker;

    /**
     * Creates a new instance of the ColouredPlayerResources class.
     * @param colour The player colour.
     */
    public ColouredPlayerResources(PlayerColour colour) {
        // Load the resources from disk.
        this.load(colour);
    }

    /**
     * Load the colour-specific resources.
     * @param colour The player colour to load resources for.
     */
    private void load(PlayerColour colour) {
        String resourcesDir = "graphics/player/" + colour.toString().toLowerCase() + "/";
        player_marker = new Texture(Gdx.files.internal(resourcesDir + "player_marker.png"));
    }

    /**
     * Gets the player marker texture.
     * @return The player marker texture.
     */
    public Texture getPlayerMarker() {
        return this.player_marker;
    }
}
