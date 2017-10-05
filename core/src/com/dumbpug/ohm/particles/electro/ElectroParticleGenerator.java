package com.dumbpug.ohm.particles.electro;

import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.IEmitterActivity;
import com.dumbpug.ohm.particles.IEmitterDetails;
import com.dumbpug.ohm.particles.IParticleGenerator;
import com.dumbpug.ohm.particles.Particle;

/**
 * Generator for ElectroParticle objects.
 */
public class ElectroParticleGenerator implements IParticleGenerator, IEmitterActivity {

	/** The time in milliseconds to wait between particle generations. */
	private long particleGenerationDelay = 80;

	/** The last time that a particle was generated. */
	private long lastParticleGenerationTime = System.currentTimeMillis();
	
	/**
	 * Generate an ElectroParticle.
	 * @param emitterDetails
	 * @return particle
	 */
	public Particle generate(IEmitterDetails emitterDetails) {
		// Create the particle.
		return new ElectroParticle(emitterDetails.positionX(), emitterDetails.positionY());
	}

	/**
	 * The emitter activity which actually sets up an explosion emitter.
	 */
	@Override
	public void act(Emitter emitter) {
		// We should only generate a particle when enough time has passed since the last time.
		if((System.currentTimeMillis() - lastParticleGenerationTime) >= particleGenerationDelay) {
			// Generate a particle.
			emitter.spawnParticle();
			// Reset the generation delay.
			lastParticleGenerationTime = System.currentTimeMillis();
		}
	}
}