package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.Constants;

/**
 * A specialised bullet for sniper rifles.
 */
public class SniperBullet extends Bullet {

    /**
     * Get the velocity of this projectile at the point of it firing.
     * @return The velocity of this projectile at the point of it firing.
     */
    @Override
    public float getFireVelocity() {
        return Constants.PROJECTILE_BULLET_FIRE_VELOCITY * 1.5f;
    }

    /**
     * Get the amount of damage dealt on a hit.
     * @return The amount of damage dealt on a hit.
     */
    @Override
    public int getDamage() {
        return Constants.PROJECTILE_SNIPER_BULLET_DAMAGE;
    }
}
