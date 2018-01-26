package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Ohm;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.area.Transition;
import com.dumbpug.ohm.character.player.PlayerPhysicsBox;
import com.dumbpug.ohm.input.Control;
import com.dumbpug.ohm.input.IInputProvider;
import com.dumbpug.ohm.character.player.Player;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;

/**
 * Represents the in-game state.
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
        this.player = new Player(new PlayerPhysicsBox(0, 0));
        // Add the player to the area.
        this.area.addPlayer(player);
    }

    @Override
    public void tick(StateManager manager) {
        // Find out if the current area needs to be reset.
        if (this.area.isFailed()) {
            // Reset the current area. Set the area to be a newly created version of the same area.
            this.area = new Area(this.area.getDetails().getAreaName());
            // Add the player to the newly created area.
            this.area.addPlayer(player);
        } else if (this.area.isComplete()){
            // Set the area to be a newly created version of the next area.
            this.area = new Area(this.area.getDetails().getNextAreaName());
            // Add the player to the newly created area.
            this.area.addPlayer(player);
        } else {
            // Update the area physics world.
            this.area.updatePhysicsWorld();
            // Tick the player.
            player.tick();
            // Process input.
            processInput();
            // Tick the area.
            this.area.tick();
        }
    }

    /**
     * Process user input.
     */
    private void processInput() {
        // Get the application input provider.
        IInputProvider inputProvider = Ohm.getInputProvider();

        // Are we moving left/right?
        if (inputProvider.isControlPressed(Control.LEFT)) {
            // We are running left.
            player.moveLeft();
        } else if (inputProvider.isControlPressed(Control.RIGHT)) {
            // We are running right.
            player.moveRight();
        } else {
            player.characterPhysicsBox.setVelX(0f);
            // TODO Reduce X axis walking speed.
        }

        // Are we moving up/down?
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.moveUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.moveDown();
        } else {
            player.characterPhysicsBox.setVelY(0f);
            // TODO Reduce Y axis walking speed.
        }

        // Are we jumping?
        if (inputProvider.isControlJustPressed(Control.JUMP)) {
            player.jump();
        }
        // Are we using electro charge?
        if (inputProvider.isControlPressed(Control.ELECTRO_CHARGE)) {
            player.getElectroChargeLevel().setEnabled(true);
        } else {
            player.getElectroChargeLevel().setEnabled(false);
        }
        // Do we want to exit? (only desktop version)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
