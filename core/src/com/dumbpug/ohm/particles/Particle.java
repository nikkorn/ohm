package com.dumbpug.ohm.particles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;

/**
 * Represents a Particle.
 */
public abstract class Particle {

	/** The life of this particle in millis. */
	private long life = Constants.PARTICLES_DEFAULT_LIFE;

	/** The time this particle was created. */
	private long timeOfCreation = System.currentTimeMillis();
    
    /**
     * Update the particle.
     */
	public abstract void update();

	/**
	 * Get the X position of this particle.
	 * @return x position.
     */
	public abstract float getPositionX();

	/**
	 * Get the Y position of this particle.
	 * @return y position.
	 */
	public abstract float getPositionY();

    /**
     * Draw the particle.
     * @param batch
     */
    protected abstract void draw(SpriteBatch batch);
    
    /**
     * Called when this particle is first created.
     */
    protected  abstract void onCreation();
    
    /**
     * Called when this particle dies.
     */
    protected abstract void onEnd();

    /**
     * Get whether this particle is alive.
     * @return isAlive
     */
	public boolean isAlive() { return getRemainingLife() > 0; }
	
	/**
     * Get the remaining life of this particle in millis.
     * @return remaining life
     */
	public long getRemainingLife() { 
		long remainingLife = life - (System.currentTimeMillis() - timeOfCreation);
		return remainingLife > 0 ? remainingLife : 0; 
	}
	
    /**
     * Get the life of this particle in millis.
     * @return life
     */
	public long getLife() { return life; }

	/**
	 * Set the life of this particle in millis.
	 * @param life
	 */
	public void setLife(long life) { this.life = life; }
}
