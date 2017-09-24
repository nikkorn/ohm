package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.player.Player;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;

/**
 * Represents the title screen state.
 */
public class Game implements State {

    /** The current game area. */
    private Area area;

    /** The player. */
    private Player player;

    /**
     * Create a new instance of the Game class.
     * @param isNewGame
     */
    public Game(boolean isNewGame) {
        // If this is a new game then ...
        if (isNewGame) {
            // ... Go to the initial area, otherwise ...
            this.area = new Area("initial");
        } else {
            // TODO ... Get the last area visited from disk.
        }
        // Create the player.
        this.player = new Player();
        // Add the player to the area.
        this.area.addPlayer(player);
        // TODO Create HUD.
    }

    @Override
    public void tick(StateManager manager) {
        // Tick the area.
        this.area.tick();
    }

    public void draw(SpriteBatch batch) {
        // Draw the area.
        this.area.draw(batch);
    }

    @Override
    public StateType getStateType() { return StateType.GAME; }
}
