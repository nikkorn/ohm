package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.projectiles.Projectile;
import java.util.ArrayList;

/**
 * Represents an equip-able weapon
 */
public abstract class Weapon {
    /**
     * The last time the weapon was fired.
     */
    private long lastFired = 0l;
    /**
     * The ammo level of the weapon.
     */
    private int ammo;
    /**
     * The list containing any newly generated projectiles.
     */
    private ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();

    /**
     * Create a new instance of the Weapon class.
     */
    public Weapon() {
        // Every new weapon will have max ammo.
        this.ammo = this.getMaxAmmoAmount();
    }

    /**
     * Attempt to use the weapon.
     */
    public void use() {
        // Do we have enough ammo to fire?
        if (ammo > 0) {
            // Get the current time.
            long currentTime = System.currentTimeMillis();
            // Have we waited long enough since we last fired this weapon?
            if (System.currentTimeMillis() >= (lastFired + this.getCooldown())) {
                // Use a unit of ammo.
                this.ammo--;
                // Reset the last fired time.
                this.lastFired = currentTime;
                // Generate projectiles.
                this.newProjectiles = this.generateProjectiles();
                // We have successfully fired our weapon!
                this.onFire();
            } else {
                // TODO Handle attempting to fire before cool-down!
                System.out.println("Weapon must cool down!");
            }
        } else {
            // TODO Handle empty weapon!
            System.out.println("Weapon empty!");
        }
    }

    /**
     * Get the ammo count
     * @return The ammo count.
     */
    public int getAmmo() {
        return ammo;
    }

    /**
     * Set the ammo count.
     * @param ammo The ammo count.
     */
    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    /**
     * Get any newly generated projectiles.
     * @return Any newly generated projectiles.
     */
    public ArrayList<Projectile> getNewProjectiles() {
        // Create a copy of the newly generated projectiles list.
        ArrayList<Projectile> copiedProjectilesList = new ArrayList<Projectile>(this.newProjectiles);
        // Clear the original list so that we don't keep generating projectiles in the area.
        this.newProjectiles.clear();
        // Return the projectiles which are actually new and should be added to the area.
        return copiedProjectilesList;
    }

    /**
     * Gets whether this weapon should be un-equipped when it is depleted.
     * @return Whether this weapon should be un-equipped when it is depleted.
     */
    public boolean unEquipWhenEmpty() {
        return false;
    }

    /**
     * Called when the weapon is successfully fired.
     */
    protected abstract void onFire();

    /**
     * Generate projectiles for a single fire of this weapon.
     */
    protected abstract ArrayList<Projectile> generateProjectiles();

    /**
     * Get the cool-down time needed between successful shots.
     * @return The cool-down time needed between successful shots.
     */
    public abstract long getCooldown();

    /**
     * Get the maximum amount of ammo for this weapon.
     * @return The maximum amount of ammo for this weapon.
     */
    public abstract int getMaxAmmoAmount();

    /**
     * Gets whether this weapon is an automatic weapon.
     * @return Whether this weapon is an automatic weapon.
     */
    public abstract boolean isAutomatic();

    /**
     * Gets the type of the weapon.
     * @return The type of the weapon.
     */
    public abstract WeaponType getType();
}
