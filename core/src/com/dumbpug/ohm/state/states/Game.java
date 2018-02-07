package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Ohm;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.area.AreaRenderer;
import com.dumbpug.ohm.input.Control;
import com.dumbpug.ohm.input.IInputProvider;
import com.dumbpug.ohm.player.Player;
import com.dumbpug.ohm.player.PlayerPhysicsBox;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;

/**
 * Represents the in-game state.
 */
public class Game implements State {
    /**
     * The current game area.
     */
    private Area area;
    /**
     * The renderer for areas.
     */
    private AreaRenderer areaRenderer;
    /**
     * The player.
     */
    private Player player;

    /**
     * Create a new instance of the Game class.
     */
    public Game() {
        // Create the player.
        this.player = new Player(new PlayerPhysicsBox(0, 0));
        // Create the area.
        this.area = new Area(player);
        // Create the area renderer.
        this.areaRenderer = new AreaRenderer();
    }

    /**
     * Tick the Game state.
     * @param manager The state manager.
     */
    @Override
    public void tick(StateManager manager) {
        // Process input.
        processInput();
        // Tick the player.
        this.player.tick();
        // Tick the area.
        this.area.tick();
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
            player.getPlayerPhysicsBox().setVelX(0f);
            // TODO Reduce X axis walking speed.
        }

        // Are we moving up/down?
        if (inputProvider.isControlPressed(Control.UP)) {
            player.moveUp();
        } else if (inputProvider.isControlPressed(Control.DOWN)) {
            player.moveDown();
        } else {
            player.getPlayerPhysicsBox().setVelY(0f);
            // TODO Reduce Y axis walking speed.
        }

        // Get our angle of aim for the player.
        float angleOfAim = inputProvider.getAngleOfAim();
        if (angleOfAim != -1) {
            player.setAngleOfAim(angleOfAim);
        }

        // Are we accepting something?
        if (inputProvider.isControlJustPressed(Control.ACCEPT)) {
            // TODO Are we able to accept something?
        }

        // Do we want to exit? (only desktop version)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    /**
     * Draw the area.
     * @param batch The sprite batch.
     */
    public void draw(SpriteBatch batch) {
        // Draw background.
        batch.draw(AreaResources.background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Draw the area.
        this.areaRenderer.render(batch, area);
    }

    @Override
    public StateType getStateType() {
        return StateType.GAME;
    }
}
