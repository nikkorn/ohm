package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.Constants;

/**
 * A basic bullet projectile.
 */
public class Bullet extends Projectile {

    /**
     * Whether the entity is airborne.
     * @return Whether the entity is airborne.
     */
    @Override
    public boolean isAirborne() { return true; }

    /**
     * Get the size of the projectile.
     * @return The projectile size.
     */
    @Override
    public float getSize() {
        return Constants.PROJECTILE_BULLET_SIZE;
    }
}
