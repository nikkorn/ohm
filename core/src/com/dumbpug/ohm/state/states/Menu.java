package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    public void tick(StateManager manager) {}

    public void draw(SpriteBatch batch) {
        // Draw our logo to the screen.
        batch.begin();
        batch.draw(AreaResources.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    public void onTransitionFromState(State state) {}

    @Override
    public StateType getStateType() { return StateType.MENU; }
}
