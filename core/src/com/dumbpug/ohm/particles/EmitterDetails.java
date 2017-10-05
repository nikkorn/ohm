package com.dumbpug.ohm.particles;

/**
 * Concrete instance of IEmitterDetails.
 */
public class EmitterDetails implements IEmitterDetails {

	/** The X position of the emitter. */
	public float positionX = 0;

	/** The Y position of the emitter. */
	public float positionY = 0;
	
	@Override
	public float positionX() { return positionX; }
	
	@Override
	public float positionY() { return positionY; }
}
