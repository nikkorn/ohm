package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.ohm.weapons.WeaponType;

/**
 * Weapon resources.
 */
public class WeaponResources {

    /** The bullet texture. */
    public static Sprite bullet;

    /** The pistol texture. */
    private static Sprite pistol;

    /**
     * Load resources.
     */
    public static void load() {
        bullet = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/bullet.png")));
        pistol = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/pistol.png")));
    }

    /**
     * Gets the sprite resource for a weapon type.
     * @param type The weapon type.
     * @return The sprite.
     */
    public static Sprite getWeaponSprite(WeaponType type) {
        switch (type) {
            case PISTOL:
                return pistol;
            default:
                return null;
        }
    }
}
