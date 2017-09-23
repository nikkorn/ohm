package com.dumbpug.ohm.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Manages the rendering of and navigation between states.
 */
public class StateManager {

    /** The current state. */
    private State state;

    /**
     * Set the current State.
     * @param state
     */
    public void setState(State state){
        this.state = state;
    }

    /**
     * Get the current.
     * @return state
     */
    public State getState() { return this.state; }

    /**
     * Ticks the current state.
     */
    public void tick() { this.state.tick(this); }

    /**
     * Draws the current state.
     * @param batch
     */
    public void draw(SpriteBatch batch) { this.state.draw(batch);}
}