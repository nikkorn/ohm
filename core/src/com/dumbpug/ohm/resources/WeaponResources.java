package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Weapon resources.
 */
public class WeaponResources {

    /** The bullet texture. */
    public static Sprite bullet;

    /**
     * Load resources.
     */
    public static void load() {
        bullet = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/bullet.png")));
    }
}
