package com.dumbpug.ohm.particles;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;

/**
 * A particle emitter.
 */
public class Emitter {

	/** The particle generator. */
	private IParticleGenerator particleGenerator;

	/** The EmitterDetails object to be passed to our particle generator. */
	private IEmitterDetails emitterDetails = new EmitterDetails();

	/** The Emitter Activity. If this is set it will be called at the start of each emitter update. */
	private IEmitterActivity emitterActivity = null;

	/** Whether the emitter activity is completed. */
	private boolean isEmitterActivityActive = false;

	/** The list of particles. */
	private ArrayList<Particle> particles = new ArrayList<Particle>();

	/** Flag defining whether this emitter is alive. */
	private boolean isAlive = true;

	/** Flag defining whether this emitter is alive only as long as we have alive particles. */
	private boolean isAliveOnlyIfHasParticles = false;
	
	/**
	 * Create a new instance of the Emitter class.
	 * @param particleGenerator
	 */
	public Emitter(IParticleGenerator particleGenerator) { this.particleGenerator = particleGenerator; }
	
	/**
	 * Sets whether this emitter is alive.
	 * @param isAlive
	 */
	public void setAlive(boolean isAlive) { this.isAlive = isAlive; }
	
	/**
	 * Gets whether this emitter is alive.
	 * @returns isAlive
	 */
	public boolean isAlive() { return this.isAlive; }
	
	/**
	 * Sets whether this emitter is alive only as long as we have alive particles.
	 * @param isAliveOnlyIfHasParticles
	 */
	public void setAliveOnlyIfHasParticles(boolean isAliveOnlyIfHasParticles) { this.isAliveOnlyIfHasParticles = isAliveOnlyIfHasParticles; }
	
	/**
	 * Get the emitter activity to be executed at the start of ever emitter update.
	 * @return IEmitterActivity
	 */
	public IEmitterActivity getEmitterActivity() { return emitterActivity; }

	/**
	 * Set the emitter activity to be executed at the start of ever emitter update.
	 * @param emitterActivity
	 */
	public void setEmitterActivity(IEmitterActivity emitterActivity) { 
		this.emitterActivity         = emitterActivity; 
		this.isEmitterActivityActive = true;
	}
	
	/**
	 * Reset the emitter along with the activity state.
	 */
	public void reset() {
		if(this.getEmitterActivity() != null) {
			this.isEmitterActivityActive = true;
			this.isAlive                 = true;
		}
	}
	
	/**
	 * Get the emitter details.
	 * @return emitter details.
	 */
	public IEmitterDetails getEmitterDetails() { return this.emitterDetails; }
	
	/**
	 * Set the emitter details.
	 * @param emitterDetails details.
	 */
	public void setEmitterDetails(IEmitterDetails emitterDetails) { this.emitterDetails = emitterDetails; }
	
	/**
	 * Marks the emitter activity as finished.
	 */
	public void finishActivity() { this.isEmitterActivityActive = false; }
	
	/**
	 * Spawn a new particle.
	 */
	public void spawnParticle() {
		// We can only spawn a particle if we have not hit out limit.
		if(particles.size() < Constants.PARTICLES_EMITTER_PARTICLE_LIMIT) {
			// Generate a new particle.
			Particle newParticle = particleGenerator.generate(emitterDetails);
			// Add it to our collection of particles.
			particles.add(newParticle);
			newParticle.onCreation();
		}
	}
	
	/**
	 * Update this emitter.
	 */
	public void update() {
		// Don't do anything if this emitter is not alive.
		if(!isAlive) {
			return;
		}
		// Do our activity if we have one.
		if(this.isEmitterActivityActive) {
			emitterActivity.act(this);
		}
		// Remove dead particles.
		Iterator<Particle> particleIterator = particles.iterator();
		while (particleIterator.hasNext()) {
			Particle currentParticle = particleIterator.next();
		    if (!currentParticle.isAlive()) {
		    	currentParticle.onEnd();
		    	particleIterator.remove();
		    }
		}
		// Update all of our particles.
		for(Particle particle : particles) {
			particle.update();
		}
		// If this emitter dies when we have no particles we need to handle it.
		if(isAliveOnlyIfHasParticles) {
			isAlive = this.hasActiveParticles();
		}
	}
	
	/**
	 * Returns whether there are still any active particles.
	 * @return has active particles.
	 */
	public boolean hasActiveParticles() {
		for(Particle particle : particles) {
			if(particle.isAlive()) {
				// At least one particle is alive.
				return true;
			}
		}
		return false;
	}

	/**
	 * Draw this emitters particles.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		// Don't do anything if this emitter is not alive.
		if(!isAlive) {
			return;
		}
		// Draw all of the particles.
		for(Particle particle : particles) {
			particle.draw(batch);
		}
	}
}
