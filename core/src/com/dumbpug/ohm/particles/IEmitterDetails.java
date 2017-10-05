package com.dumbpug.ohm.particles;

/**
 * Provides details of an Emitter passed when generating a new particle
 */
public interface IEmitterDetails {
	
	/**
	 * Get the X position of an emitter.
	 * @return x position
	 */
	float positionX();
	
	/**
	 * Get the Y position of an emitter.
	 * @return y position
	 */
	float positionY();
}
