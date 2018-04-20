package com.dumbpug.ohm.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.resources.WeaponResources;

/**
 * A simple rocket that explodes on impact.
 */
public class Rocket extends Projectile {

    /**
     * Whether the entity is airborne.
     * @return Whether the entity is airborne.
     */
    @Override
    public boolean isAirborne() {
        return true;
    }

    /**
     * Get the sprite for this projectile.
     * @return The sprite for this projectile.
     */
    public Sprite getSprite() {
        return WeaponResources.rocket;
    }

    /**
     * Get the size of the projectile.
     * @return The projectile size.
     */
    @Override
    public float getSize() {
        return Constants.PROJECTILE_BULLET_SIZE;
    }

    /**
     * Get the velocity of this projectile at the point of it firing.
     * @return The velocity of this projectile at the point of it firing.
     */
    @Override
    public float getFireVelocity() {
        return Constants.PROJECTILE_ROCKET_FIRE_VELOCITY;
    }

    /**
     * Get the amount of damage dealt on a hit.
     * @return The amount of damage dealt on a hit.
     */
    @Override
    public int getDamage() {
        return Constants.PROJECTILE_BASIC_BULLET_DAMAGE;
    }
}
