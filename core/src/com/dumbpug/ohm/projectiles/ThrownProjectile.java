package com.dumbpug.ohm.projectiles;

/**
 * Represents a thrown projectile.
 * A thrown projectile will not move at a constant rate like a bullet.
 */
public abstract class ThrownProjectile extends Projectile {

    /**
     * Get the amount of damage dealt on a hit.
     * @return The amount of damage dealt on a hit.
     */
    @Override
    public int getDamage() {
        // A thrown projectile will not apply damage directly.
        return 0;
    }

    /**
     * Creates the projectile physics box for this projectile.
     * @return The projectile physics box for this projectile.
     */
    protected ProjectilePhysicsBox createPhysicsBox() {
        return new ThrownProjectilePhysicsBox(this.getSize());
    }
}
