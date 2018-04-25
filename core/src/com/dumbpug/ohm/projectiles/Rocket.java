package com.dumbpug.ohm.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.IEmitterDetails;
import com.dumbpug.ohm.particles.rocketsmoke.RocketSmokeParticleGenerator;
import com.dumbpug.ohm.resources.WeaponResources;

/**
 * A simple rocket that explodes on impact.
 */
public class Rocket extends Projectile {
    /**
     * The emitter of smoke particles that the rocket leaves behind.
     */
    private Emitter smokeParticleEmitter;

    /**
     * Create a new instance of the Rocket class.
     */
    public Rocket() {
        super();
        // Create the rocket smoke particle emitter.
        createRocketSmokeParticleEmitter();
        // Rockets can only go so fast.
        this.getPhysicsBox().setMaxVelocityX(Constants.PROJECTILE_ROCKET_MAX_VELOCITY);
        this.getPhysicsBox().setMaxVelocityY(Constants.PROJECTILE_ROCKET_MAX_VELOCITY);
    }

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

    /**
     * Tick the projectile.
     */
    public void tick() {
        super.tick();
        // Update the rocket smoke particle emitter.
        this.smokeParticleEmitter.update();
    }

    /**
     * Draw the projectile.
     * @param batch The sprite batch.
     */
    @Override
    public void draw(SpriteBatch batch) {
        // Draw the projectile.
        super.draw(batch);
        // Draw the rocket smoke particles.
        this.smokeParticleEmitter.draw(batch);
    }

    /**
     * Create the rocket smoke particle emitter.
     */
    private void createRocketSmokeParticleEmitter() {
        // Create the rocket smoke particle generator.
        RocketSmokeParticleGenerator generator = new RocketSmokeParticleGenerator();
        // Create the rocket smoke particle emitter.
        this.smokeParticleEmitter = new Emitter(generator);
        // Set the emitter details.
        this.smokeParticleEmitter.setEmitterDetails(new IEmitterDetails() {
            @Override
            public float positionX() {
                return getPhysicsBox().getCurrentOriginPoint().getX();
            }
            @Override
            public float positionY() {
                return getPhysicsBox().getCurrentOriginPoint().getY();
            }
        });
        // Set the emitter activity.
        this.smokeParticleEmitter.setEmitterActivity(generator);
    }
}
