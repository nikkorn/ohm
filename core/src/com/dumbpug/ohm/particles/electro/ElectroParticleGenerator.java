package com.dumbpug.ohm.particles.electro;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.Emitter;
import com.dumbpug.ohm.particles.IEmitterActivity;
import com.dumbpug.ohm.particles.IEmitterDetails;
import com.dumbpug.ohm.particles.IParticleGenerator;
import com.dumbpug.ohm.particles.Particle;

/**
 * Generator for ElectroParticle objects.
 */
public class ElectroParticleGenerator implements IParticleGenerator, IEmitterActivity {

	/** The last time that a particle was generated. */
	private long lastParticleGenerationTime = System.currentTimeMillis();

	/** Flag defining whether the player is using electro charge. */
	private boolean isElectroChargeMode = false;
	
	/**
	 * Generate an ElectroParticle.
	 * @param emitterDetails
	 * @return particle
	 */
	public Particle generate(IEmitterDetails emitterDetails) {
		// Create the particle depending on whether we are in electro charge mode.
		if (this.isElectroChargeMode) {
			return new ChargedElectroParticle(emitterDetails.positionX(), emitterDetails.positionY());
		} else {
			return new ElectroParticle(emitterDetails.positionX(), emitterDetails.positionY());
		}
	}

	/**
	 * Set whether electro charge mode is enabled.
	 * @param enabled
	 */
	public void setElectroChargeModeEnabled(boolean enabled) { this.isElectroChargeMode = enabled; }

	/**
	 * The emitter activity which actually sets up an explosion emitter.
	 */
	@Override
	public void act(Emitter emitter) {
		// We should only generate a particle when enough time has passed since the last time.
		if((System.currentTimeMillis() - lastParticleGenerationTime) >= getParticleGenerationDelay()) {
			// Generate a particle.
			emitter.spawnParticle();
			// Reset the generation delay.
			lastParticleGenerationTime = System.currentTimeMillis();
		}
	}

	/**
	 * Get the particle generation delay.
	 * @return delay
	 */
	private long getParticleGenerationDelay() {
		// The delay will depend on whether we are in electro charge mode.
		if (this.isElectroChargeMode) {
			return Constants.PARTICLES_DEFAULT_GEN_DELAY / 5;
		} else {
			return Constants.PARTICLES_DEFAULT_GEN_DELAY;
		}
	}
}