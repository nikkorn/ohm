package com.dumbpug.ohm.particles.rocketsmoke;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.IEmitterActivity;
import com.dumbpug.ohm.particles.IEmitterDetails;
import com.dumbpug.ohm.particles.IParticleGenerator;
import com.dumbpug.ohm.particles.Particle;

/**
 * Generator for RocketSmokeParticle objects.
 */
public class RocketSmokeParticleGenerator implements IParticleGenerator, IEmitterActivity {
    /**
     * The last time that a particle was generated
     */
    private long lastParticleGenerationTime = System.currentTimeMillis();

    /**
     * Generate a RocketSmokeParticle.
     * @param emitterDetails The emitter details.
     * @return particle The generated particle.
     */
    public Particle generate(IEmitterDetails emitterDetails) {
        return new RocketSmokeParticle(emitterDetails.positionX(), emitterDetails.positionY());
    }

    /**
     * The emitter activity which actually sets up an explosion emitter.
     * @param emitter The emitter.
     */
    @Override
    public void act(Emitter emitter) {
        // We should only generate a particle when enough time has passed since the last time.
        if ((System.currentTimeMillis() - lastParticleGenerationTime) >= getParticleGenerationDelay()) {
            // Generate a particle.
            emitter.spawnParticle();
            // Reset the generation delay.
            lastParticleGenerationTime = System.currentTimeMillis();
        }
    }

    /**
     * Get the particle generation delay.
     * @return delay The delay in ms.
     */
    private long getParticleGenerationDelay() {
        return Constants.PARTICLES_DEFAULT_GEN_DELAY;
    }
}