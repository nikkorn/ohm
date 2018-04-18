package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.projectiles.Bullet;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.resources.AudioResources;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a shotgun.
 */
public class Shotgun extends Weapon {
    /**
     * The RNG to use in randomising bullet spread.
     */
    private Random random;

    /**
     * Creates a new instance of the Shotgun class.
     */
    public Shotgun() {
        super();
        // Create the RNG to use in randomising bullet spread.
        this.random = new Random();
    }

    @Override
    protected void onFire() {
        // TODO Make sound/ add muzzle flash texture.
        System.out.print("Fired Shotgun!");
    }

    /**
     * Generate projectiles for a single fire of this weapon.
     */
    @Override
    protected ArrayList<Projectile> generateProjectiles() {
        // A shotgun can fire a number of bullets simultaneously.
        return new ArrayList<Projectile>() {{
            for (int bulletIndex = 0; bulletIndex <= Constants.PROJECTILE_SHOTGUN_SPRAY_COUNT; bulletIndex++) {
                // Create a bullet for this shot.
                Bullet bullet = new Bullet();
                // Find a random angle to offset the bullet by in order to simulate bullet spread.
                float spreadOffset = (float)(random.nextInt(Constants.PROJECTILE_SHOTGUN_SPRAY_ANGLE) - (Constants.PROJECTILE_SHOTGUN_SPRAY_ANGLE / 2));
                // Apply the random angle offset.
                bullet.setAngleOfFireOffset(spreadOffset);
                // Add this bullet to the list of generated projectiles.
                add(bullet);
            }
        }};
    }

    /**
     * Get the cool-down time needed between successful shots.
     * @return The cool-down time needed between successful shots.
     */
    @Override
    public long getCooldown() {
        return Constants.PROJECTILE_SHOTGUN_COOL_DOWN;
    }

    /**
     * Get the maximum amount of ammo for this weapon.
     * @return The maximum amount of ammo for this weapon.
     */
    @Override
    public int getMaxAmmoAmount() {
        return Constants.PROJECTILE_SHOTGUN_MAX_AMMO;
    }

    /**
     * Get the sound effect to be played when this weapon is successfully fired.
     * @return The sound effect to be played when this weapon is successfully fired.
     */
    @Override
    public AudioResources.SoundEffect getFireSoundEffect() {
        return AudioResources.SoundEffect.HEAVY_SHOT;
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
        return 15;
    }

    /**
     * Gets the type of the weapon.
     * @return The type of the weapon.
     */
    @Override
    public WeaponType getType() {
        return WeaponType.SHOTGUN;
    }
}
