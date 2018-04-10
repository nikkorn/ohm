package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.projectiles.LaunchedGrenade;
import com.dumbpug.ohm.projectiles.Projectile;
import java.util.ArrayList;

/**
 * Represents a grenade.
 */
public class Grenade extends Weapon {

    @Override
    protected void onFire() {
        // TODO Make sound/ add muzzle flash texture.
        System.out.print("Threw grenade!");
    }

    @Override
    protected ArrayList<Projectile> generateProjectiles() {
        // We are launching a single grenade at a time.
        return new ArrayList<Projectile>() {{
            add(new LaunchedGrenade());
        }};
    }

    @Override
    public long getCooldown() {
        return Constants.PROJECTILE_GRENADE_COOL_DOWN;
    }

    @Override
    public int getMaxAmmoAmount() {
        return Constants.PROJECTILE_GRENADE_MAX_AMMO;
    }

    @Override
    public boolean isAutomatic() {
        return false;
    }

    /**
     * Gets whether this weapon should be un-equipped when it is depleted.
     * @return Whether this weapon should be un-equipped when it is depleted.
     */
    public boolean unEquipWhenEmpty() {
        // We wouldn't expect our player to be holding a grenade if we had no ammo for it.
        return true;
    }

    /**
     * Gets the length of the weapon.
     * @return The length of the weapon.
     */
    @Override
    public int getLength() {
        return 6;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.GRENADE;
    }
}
