package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Area resources.
 */
public class AreaResources {

    /** The background texture for all areas. */
    public static Texture background;

    /** The area transition overlay. */
    public static Texture transition_overlay;

    /** The platform texture. */
    public static Texture platform;

    /** The target texture. */
    public static Texture target;

    /**
     * Load resources.
     */
    public static void load() {
        background         = new Texture(Gdx.files.internal("graphics/areas/background.png"));
        platform           = new Texture(Gdx.files.internal("graphics/areas/platform.png"));
        target             = new Texture(Gdx.files.internal("graphics/areas/target.png"));
        transition_overlay = new Texture(Gdx.files.internal("graphics/areas/transition_overlay.png"));
    }
}
