package com.dumbpug.ohm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.ParticleResources;
import com.dumbpug.ohm.resources.PlayerResources;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.states.Title;

public class Ohm extends ApplicationAdapter {

	/** The sprite batch to use throughout the game. */
	private SpriteBatch batch;

	/** The state manager. */
	private StateManager stateManager;
	
	@Override
	public void create () {

		// Create the game sprite batch.
		batch = new SpriteBatch();

		// Load the game resources.
		AreaResources.load();
		PlayerResources.load();
		ParticleResources.load();

		// Create the game state manager.
		stateManager = new StateManager();

		// Set the initial game state.
		stateManager.setState(new Title());
	}

	@Override
	public void render () {

		// Tick the game.
		stateManager.tick();

		// Clear the screen.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		// Draw the game.
		batch.begin();
		stateManager.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
