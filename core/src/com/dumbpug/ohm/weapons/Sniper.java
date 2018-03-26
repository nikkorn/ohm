package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.projectiles.SniperBullet;
import java.util.ArrayList;

/**
 * Represents a sniper rifle.
 */
public class Sniper extends Weapon {

    @Override
    protected void onFire() {
        // TODO Make sound/ add muzzle flash texture.
        System.out.print("Fired Sniper!");
    }

    /**
     * Generate projectiles for a single fire of this weapon.
     */
    @Override
    protected ArrayList<Projectile> generateProjectiles() {
        // A sniper can only shoot a single bullet.
        return new ArrayList<Projectile>() {{
            add(new SniperBullet());
        }};
    }

    /**
     * Get the cool-down time needed between successful shots.
     * @return The cool-down time needed between successful shots.
     */
    @Override
    public long getCooldown() {
        return Constants.PROJECTILE_SNIPER_COOL_DOWN;
    }

    /**
     * Get the maximum amount of ammo for this weapon.
     * @return The maximum amount of ammo for this weapon.
     */
    @Override
    public int getMaxAmmoAmount() {
        return Constants.PROJECTILE_SNIPER_MAX_AMMO;
    }

    /**
     * Gets the type of the weapon.
     * @return The type of the weapon.
     */
    @Override
    public WeaponType getType() {
        return WeaponType.SNIPER;
    }
}
