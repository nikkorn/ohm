package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Player resources.
 */
public class PlayerResources {

    public static Texture ohm_standing_left;

    /**
     * Load resources.
     */
    public static void load() {
        ohm_standing_left = new Texture(Gdx.files.internal("graphics/player/ohm_standing_left.png"));
    }
}
