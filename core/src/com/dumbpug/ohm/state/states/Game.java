package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.area.Transition;
import com.dumbpug.ohm.player.Player;
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

    /** The area transition. */
    private Transition transition;

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
        // Find out if the current area needs to be reset.
        if (this.area.isFailed()) {
            // Reset the current area. Set the area to be a newly created version of the same area.
            this.area = new Area(this.area.getAreaName());
            // Add the player to the newly created area.
            this.area.addPlayer(player);
        } else if (this.area.isComplete()){
            // Set the area to be a newly created version of the next area.
            this.area = new Area(this.area.getNextAreaName());
            // Add the player to the newly created area.
            this.area.addPlayer(player);
        } else {
            // Tick the area.
            this.area.tick();
        }
    }

    /**
     * Draw the area.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Draw the area.
        this.area.draw(batch);
    }

    @Override
    public StateType getStateType() { return StateType.GAME; }
}
