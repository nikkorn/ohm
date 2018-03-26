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

    /** The weapon textures. */
    private static Sprite pistol;
    private static Sprite sniper;
    private static Sprite shotgun;

    /**
     * Load resources.
     */
    public static void load() {
        bullet  = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/bullet.png")));
        pistol  = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/pistol.png")));
        sniper  = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/sniper.png")));
        shotgun = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/shotgun.png")));
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
            case SNIPER:
                return sniper;
            case SHOTGUN:
                return shotgun;
            default:
                return null;
        }
    }
}
