package com.dumbpug.ohm.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.resources.WeaponResources;

/**
 * A basic grenade projectile.
 */
public class LaunchedGrenade extends ThrownProjectile {

    @Override
    public boolean isAirborne() {
        return true;
    }

    @Override
    public float getSize() {
        return Constants.PROJECTILE_GRENADE_SIZE;
    }

    @Override
    public float getFireVelocity() {
        // A grenade projectile fire velocity can be built up (cooked).
        // This is the default velocity if the grenade is launched without cooking it.
        return Constants.PROJECTILE_GRENADE_BASE_FIRE_VELOCITY;
    }

    @Override
    public Sprite getSprite() {
        return WeaponResources.launched_grenade;
    }
}
