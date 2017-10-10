package com.dumbpug.ohm.particles.electro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.Particle;
import com.dumbpug.ohm.resources.ParticleResources;
import java.util.Random;

/**
 * Represents a charged electro particle.
 */
public class ChargedElectroParticle extends Particle {

    /** The position of the particle. */
    private float posX, posY;

    /** The positional offset to apply to the particle every update. */
    private float updateOffsetX = new Random().nextFloat() - 0.8f;
    private float updateOffsetY = new Random().nextFloat() - 0.8f;

    /**
     * Create a new instance of the ChargeElectroParticle class.
     * @param posX
     * @param posY
     */
    public ChargedElectroParticle(float posX, float posY) {
        // Set the position of the particle.
        this.posX = posX;
        this.posY = posY;
        // Set the life of this particle.
        this.setLife((long)(Constants.PARTICLES_DEFAULT_LIFE * 1.5f));
    }

    @Override
    public void update() {
        // Update the particle position..
        this.posX += updateOffsetX;
        this.posY += updateOffsetY;
    }

    public float getPositionX() { return posX; }

    public float getPositionY() { return posY; }

    /**
     * Draw the particle.
     * @param batch
     */
    protected void draw(SpriteBatch batch) {
        // Set the sprite size.
        ParticleResources.electro_particle.setSize(Constants.PARTICLES_ELECTRO_SIZE_LARGE, Constants.PARTICLES_ELECTRO_SIZE_LARGE);
        // Set the sprite position.
        ParticleResources.electro_particle.setX(getPositionX());
        ParticleResources.electro_particle.setY(getPositionY());
        // Set opacity based on life.
        ParticleResources.electro_particle.setAlpha(this.getRemainingLife() / (float) this.getLife());
        // Draw the particle.
        ParticleResources.electro_particle.draw(batch);
    }

    protected void onCreation() {}

    protected void onEnd() {}
}
