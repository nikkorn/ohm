package com.dumbpug.ohm.particles;

/**
 * Functional interface for creating a particle.
 */
public interface IParticleGenerator {
	
	/**
	 * Generate a particle.
	 * @param emitterDetails
	 * @return particle
	 */
	Particle generate(IEmitterDetails emitterDetails);
}
