package com.dumbpug.ohm.particles.rocketsmoke;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.Particle;
import com.dumbpug.ohm.resources.ParticleResources;

/**
 * Represents a particle of rocket smoke.
 */
public class RocketSmokeParticle extends Particle {
    /**
     * The position of the particle.
     */
    private float posX, posY;

    /**
     * Create a new instance of the RocketSmokeParticle class.
     * @param posX The X position.
     * @param posY The Y position.
     */
    public RocketSmokeParticle(float posX, float posY) {
        // Set the position of the particle.
        this.posX = posX;
        this.posY = posY;
        // Set the life of this particle.
        this.setLife((long)(Constants.PARTICLES_DEFAULT_LIFE * 1.5f));
    }

    @Override
    public void update() { }

    public float getPositionX() { return posX; }

    public float getPositionY() { return posY; }

    /**
     * Draw the particle.
     * @param batch
     */
    protected void draw(SpriteBatch batch) {
        // Set the sprite size.
        ParticleResources.rocket_smoke_particle.setSize(Constants.PARTICLES_ELECTRO_SIZE_LARGE, Constants.PARTICLES_ELECTRO_SIZE_LARGE);
        // Set the sprite position.
        ParticleResources.rocket_smoke_particle.setX(getPositionX());
        ParticleResources.rocket_smoke_particle.setY(getPositionY());
        // Set opacity based on life.
        ParticleResources.rocket_smoke_particle.setAlpha(this.getRemainingLife() / (float) this.getLife());
        // Draw the particle.
        ParticleResources.rocket_smoke_particle.draw(batch);
    }

    protected void onCreation() {}

    protected void onEnd() {}
}
