package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Player resources.
 */
public class PlayerResources {

    public static Texture ohm_standing_left;
    public static Texture ohm_standing_right;

    public static Texture ohm_jumping_left;
    public static Texture ohm_jumping_right;

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

        ohm_falling_left  = new Texture(Gdx.files.internal("graphics/player/ohm_falling_left.png"));
        ohm_falling_right = new Texture(Gdx.files.internal("graphics/player/ohm_falling_right.png"));

        ohm_walking_left  = new Animation(new Texture(Gdx.files.internal("graphics/player/ohm_walking_left.png")), 4, 1, 0.1f);
        ohm_walking_right = new Animation(new Texture(Gdx.files.internal("graphics/player/ohm_walking_right.png")), 4, 1, 0.1f);
    }
}
