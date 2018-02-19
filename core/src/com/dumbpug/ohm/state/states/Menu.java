package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;

/**
 * Represents the title screen state.
 */
public class Menu implements State {

    @Override
    public void tick(StateManager manager) {
        // Go straight to player selection.
        manager.setState(new PlayerSelection());
    }

    public void draw(SpriteBatch batch) {
        batch.draw(AreaResources.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public StateType getStateType() { return StateType.MENU; }
}
