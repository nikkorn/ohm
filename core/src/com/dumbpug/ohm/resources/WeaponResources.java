package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.ohm.weapons.WeaponType;

/**
 * Weapon resources.
 */
public class WeaponResources {

    /** The launched projectile textures. */
    public static Sprite bullet;
    public static Sprite launched_grenade;

    /** The weapon textures. */
    private static Sprite pistol;
    private static Sprite sniper;
    private static Sprite shotgun;
    private static Sprite grenade;

    /**
     * Load resources.
     */
    public static void load() {
        // The launched projectile textures.
        bullet           = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/bullet.png")));
        launched_grenade = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/launched_grenade.png")));
        // The weapon textures.
        pistol  = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/pistol.png")));
        sniper  = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/sniper.png")));
        shotgun = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/shotgun.png")));
        grenade = new Sprite(new Texture(Gdx.files.internal("graphics/weapons/grenade.png")));
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
            case GRENADE:
                return grenade;
            default:
                return null;
        }
    }
}
