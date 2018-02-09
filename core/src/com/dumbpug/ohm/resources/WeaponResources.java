package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Weapon resources.
 */
public class WeaponResources {

    /** The bullet texture. */
    public static Texture bullet;

    /**
     * Load resources.
     */
    public static void load() {
        bullet = new Texture(Gdx.files.internal("graphics/weapons/bullet.png"));
    }
}
