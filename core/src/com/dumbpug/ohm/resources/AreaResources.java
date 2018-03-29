package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Area resources.
 */
public class AreaResources {

    /** The background texture for all areas. */
    public static Texture background;

    /** The platform texture. */
    public static Texture platform;

    /** The target texture. */
    public static Texture target;

    /** The shadow textures. */
    public static Texture shadow_pickup;
    public static Texture shadow_player;

    /**
     * Load resources.
     */
    public static void load() {
        background    = new Texture(Gdx.files.internal("graphics/areas/background.png"));
        platform      = new Texture(Gdx.files.internal("graphics/areas/platform.png"));
        target        = new Texture(Gdx.files.internal("graphics/areas/target.png"));
        shadow_pickup = new Texture(Gdx.files.internal("graphics/areas/shadow_pickup.png"));
        shadow_player = new Texture(Gdx.files.internal("graphics/areas/shadow_player.png"));
    }
}
