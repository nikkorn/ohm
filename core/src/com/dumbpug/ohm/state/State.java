package com.dumbpug.ohm.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Represents a game state.
 */
public interface State {

    /**
     * Tick the state.
     * @param manager
     */
    void tick(StateManager manager);

    /**
     * Draw the state.
     * @param batch
     */
    void draw(SpriteBatch batch);

    /**
     * Get the type of this state.
     * @return state type
     */
    StateType getStateType();
}