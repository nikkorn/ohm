package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;

/**
 * Represents the title screen state.
 */
public class Title implements State {

	/** The DumbPug logo. */
	private Texture dumbpugLogo;

	/** The time we first rendered this state. */
	private long firstRenderTime = -1;

	/** The length of time we should show the splash. */
	private long titleDuration = 2000;
	
	public Title() {
		// Load our logo.
		dumbpugLogo = new Texture(Gdx.files.internal("graphics/misc/dumbpug.png"));
	}

    @Override
    public void tick(StateManager manager) {
    	// Set the first time that this state was rendered
    	if(firstRenderTime == -1) {
    		firstRenderTime = System.currentTimeMillis();
    	}
    	// If we have been displaying this title too long 
    	// then we need to change state to the splash screen.
    	if((System.currentTimeMillis() - firstRenderTime) > titleDuration) {
			// Go to the menu.
    		manager.setState(new Menu());
    	}
    }

	public void draw(SpriteBatch batch) {
		int logoHeight = Gdx.graphics.getHeight() / 8;
		int logoWidth  = logoHeight * 2;
		int posX       = (Gdx.graphics.getWidth()/2) - (logoWidth/2);
		int posY       = (Gdx.graphics.getHeight()/2) - (logoHeight/2);

		// Draw our logo to the screen.
		batch.draw(dumbpugLogo, posX, posY, logoWidth, logoHeight);
	}

    @Override
    public StateType getStateType() { return StateType.TITLE; }
}
