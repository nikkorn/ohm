package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.projectiles.Bullet;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.resources.AudioResources;

import java.util.ArrayList;

/**
 * Represents a basic pistol.
 */
public class Pistol extends Weapon {

    @Override
    protected void onFire() {
        // TODO Make sound/ add muzzle flash texture.
        System.out.print("Fired Pistol!");
    }

    /**
     * Generate projectiles for a single fire of this weapon.
     */
    @Override
    protected ArrayList<Projectile> generateProjectiles() {
        // A pistol can only shoot a single bullet.
        return new ArrayList<Projectile>() {{
            add(new Bullet());
        }};
    }

    /**
     * Get the cool-down time needed between successful shots.
     * @return The cool-down time needed between successful shots.
     */
    @Override
    public long getCooldown() {
        return Constants.PROJECTILE_PISTOL_COOL_DOWN;
    }

    /**
     * Get the maximum amount of ammo for this weapon.
     * @return The maximum amount of ammo for this weapon.
     */
    @Override
    public int getMaxAmmoAmount() {
        return Constants.PROJECTILE_PISTOL_MAX_AMMO * 10000;
    }

    /**
     * Get the sound effect to be played when this weapon is successfully fired.
     * @return The sound effect to be played when this weapon is successfully fired.
     */
    @Override
    public AudioResources.SoundEffect getFireSoundEffect() {
        return AudioResources.SoundEffect.BASIC_SHOT;
    }

    /**
     * Gets whether this weapon is an automatic weapon.
     * @return Whether this weapon is an automatic weapon.
     */
    @Override
    public boolean isAutomatic() {
        return false;
    }

    /**
     * Gets the length of the weapon.
     * @return The length of the weapon.
     */
    @Override
    public int getLength() {
        return 13;
    }

    /**
     * Gets the type of the weapon.
     * @return The type of the weapon.
     */
    @Override
    public WeaponType getType() {
        return WeaponType.PISTOL;
    }
}
