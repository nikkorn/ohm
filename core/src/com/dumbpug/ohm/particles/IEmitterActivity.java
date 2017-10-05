package com.dumbpug.ohm.particles;

/**
 * An interface which represents an activity to be carried out by the emitter.
 */
public interface IEmitterActivity {

	/**
	 * Do the activity
	 * @param emitter
	 */
	void act(Emitter emitter);
}
