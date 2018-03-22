package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.ohm.player.PlayerColour;
import java.util.HashMap;

/**
 * Player resources.
 */
public class PlayerResources {
    /**
     * Mapping of player colours to status bar resources matching those colours.
     */
    private static HashMap<PlayerColour, ColouredPlayerResources> colouredPlayerResourceMap;

    public static Texture ohm_standing_left;
    public static Texture ohm_standing_right;

    public static Texture ohm_jumping_left;
    public static Texture ohm_jumping_right;

    public static Texture ohm_wall_jumping_left;
    public static Texture ohm_wall_jumping_right;

    public static Texture ohm_falling_left;
    public static Texture ohm_falling_right;

    public static Animation ohm_walking_left;
    public static Animation ohm_walking_right;

    /**
     * Load resources.
     */
    public static void load() {
        ohm_standing_left  = new Texture(Gdx.files.internal("graphics/player/ohm_standing_left.png"));
        ohm_standing_right = new Texture(Gdx.files.internal("graphics/player/ohm_standing_right.png"));

        ohm_jumping_left  = new Texture(Gdx.files.internal("graphics/player/ohm_jumping_left.png"));
        ohm_jumping_right = new Texture(Gdx.files.internal("graphics/player/ohm_jumping_right.png"));

        ohm_wall_jumping_left  = new Texture(Gdx.files.internal("graphics/player/ohm_wall_jumping_left.png"));
        ohm_wall_jumping_right = new Texture(Gdx.files.internal("graphics/player/ohm_wall_jumping_right.png"));

        ohm_falling_left  = new Texture(Gdx.files.internal("graphics/player/ohm_falling_left.png"));
        ohm_falling_right = new Texture(Gdx.files.internal("graphics/player/ohm_falling_right.png"));

        ohm_walking_left  = new Animation(new Texture(Gdx.files.internal("graphics/player/ohm_walking_left.png")), 4, 1, 0.1f);
        ohm_walking_right = new Animation(new Texture(Gdx.files.internal("graphics/player/ohm_walking_right.png")), 4, 1, 0.1f);

        // Populate the coloured player resource map.
        colouredPlayerResourceMap = new HashMap<PlayerColour, ColouredPlayerResources>() {{
            put(PlayerColour.RED, new ColouredPlayerResources(PlayerColour.RED));
            put(PlayerColour.BLUE, new ColouredPlayerResources(PlayerColour.BLUE));
            put(PlayerColour.GREEN, new ColouredPlayerResources(PlayerColour.GREEN));
            put(PlayerColour.YELLOW, new ColouredPlayerResources(PlayerColour.YELLOW));
        }};
    }

    /**
     * Get the player resources for a player colour.
     * @param colour The player colour.
     * @return The player resources for a player colour.
     */
    public static ColouredPlayerResources getResourcesForColour(PlayerColour colour) {
        return colouredPlayerResourceMap.get(colour);
    }
}
