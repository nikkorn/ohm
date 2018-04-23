package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.resources.AudioResources;
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
     * @param isTriggerJustPressed Whether the trigger was just pressed.
     */
    public void use(boolean isTriggerJustPressed) {
        // If this weapon is not an automatic then it should only be used once per trigger press.
        if ((!this.isAutomatic()) && (!isTriggerJustPressed)) {
            return;
        }
        // Get the current time.
        long currentTime = System.currentTimeMillis();
        // Have we waited long enough since we last fired this weapon?
        if (System.currentTimeMillis() >= (lastFired + this.getCooldown())) {
            // Do we have enough ammo to fire?
            if (ammo > 0) {
                // Use a unit of ammo.
                this.ammo--;
                // Reset the last fired time.
                this.lastFired = currentTime;
                // Generate projectiles.
                this.newProjectiles = this.generateProjectiles();
                // We have successfully fired our weapon!
                this.onFire();
                // If this weapon makes a sound when fired then play it now.
                if (this.getFireSoundEffect() != null) {
                    AudioResources.getSoundEffect(this.getFireSoundEffect()).play();
                }
            } else {
                // We have no ammo!
                AudioResources.getSoundEffect(AudioResources.SoundEffect.DRY_SHOT).play();
                // Reset the last fired time.
                this.lastFired = currentTime;
            }
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
     * Gets whether this weapon is empty.
     * @return Whether this weapon is empty.
     */
    public boolean isEmpty() {
        return this.ammo == 0;
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
     * Gets the cool-down time needed between successful shots.
     * @return The cool-down time needed between successful shots.
     */
    public abstract long getCooldown();

    /**
     * Gets the length of the weapon.
     * @return The length of the weapon.
     */
    public abstract int getLength();

    /**
     * Gets the maximum amount of ammo for this weapon.
     * @return The maximum amount of ammo for this weapon.
     */
    public abstract int getMaxAmmoAmount();

    /**
     * Get the sound effect to be played when this weapon is successfully fired.
     * @return The sound effect to be played when this weapon is successfully fired.
     */
    public abstract AudioResources.SoundEffect getFireSoundEffect();

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
