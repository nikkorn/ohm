package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.area.AreaCamera;
import com.dumbpug.ohm.area.AreaRenderer;
import com.dumbpug.ohm.hud.AreaHUDRenderer;
import com.dumbpug.ohm.input.Control;
import com.dumbpug.ohm.input.IInputProvider;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.Player;
import com.dumbpug.ohm.player.Status;
import com.dumbpug.ohm.state.State;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.StateType;
import java.util.ArrayList;

/**
 * Represents the in-game state.
 */
public class Game implements State {
    /**
     * The current game area.
     */
    private Area area;
    /**
     * The renderer for the game area.
     */
    private AreaRenderer areaRenderer;
    /**
     * The area camera.
     */
    private AreaCamera areaCamera;
    /**
     * The renderer for the game HUD.
     */
    private AreaHUDRenderer areaHudRenderer;
    /**
     * The players.
     */
    private ArrayList<IngamePlayer> players;

    /**
     * Create a new instance of the Game class.
     * @param players The players that will be taking part in the match.
     */
    public Game(ArrayList<IngamePlayer> players) {
        // Set the players that will be taking part in the match.
        this.players = players;
        // Create the area to have the match in.
        this.area = new Area(players);
        // Create the area renderer.
        this.areaRenderer = new AreaRenderer();
        // Create the area camera.
        this.areaCamera = new AreaCamera();
        // Create the HUD renderer.
        this.areaHudRenderer = new AreaHUDRenderer();
    }

    /**
     * Tick the Game state.
     * @param manager The state manager.
     */
    @Override
    public void tick(StateManager manager) {
        // Process player input if the match is still going.
        if (!area.hasMatchFinished()) {
            processPlayerInput();
        }
        // Tick the area.
        this.area.tick(this.areaCamera);
    }

    /**
     * Process player input.
     */
    private void processPlayerInput() {
        // Process input for each player.
        for (IngamePlayer ingamePlayer : players) {
            // Do not process player input for dead players.
            if (ingamePlayer.getStatus().isDead()) {
                return;
            }
            // Get the actual Player instance.
            Player player = ingamePlayer.getPlayer();
            // Get the input provider for this player.
            IInputProvider inputProvider = ingamePlayer.getInputProvider();
            // Do nothing if this player has no input provider.
            if (inputProvider == null) {
                return;
            }
            // Are we moving left/right?
            if (inputProvider.isControlPressed(Control.LEFT)) {
                player.moveLeft();
            } else if (inputProvider.isControlPressed(Control.RIGHT)) {
                player.moveRight();
            }
            // Are we moving up/down?
            if (inputProvider.isControlPressed(Control.UP)) {
                player.moveUp();
            } else if (inputProvider.isControlPressed(Control.DOWN)) {
                player.moveDown();
            }
            // Get our angle of aim for the player.
            float angleOfAim = inputProvider.getAngleOfAim();
            if (angleOfAim != -1) {
                player.setAngleOfAim(angleOfAim);
            }
            // Are we trying to fire a weapon we may or may not have?
            if (inputProvider.isControlJustPressed(Control.ACCEPT)) {
                // Get the player status.
                Status playerStatus = ingamePlayer.getStatus();
                // Does the player have a status yet?
                if (playerStatus != null) {
                    // Attempt to use our weapon if we have one.
                    if (playerStatus.getEquippedWeapon() != null) {
                        // Use our weapon!
                        playerStatus.getEquippedWeapon().use();
                    }
                }
            }
            // Has the player tried to drop/swap their current weapon?
            if (inputProvider.isControlJustPressed(Control.SECONDARY)) {
                // What we do here depends on whether the player is already holding a weapon.
                if (ingamePlayer.getStatus().getEquippedWeapon() != null) {
                    // The player wants to swap their weapon for a weapon pickup
                    // if they are standing over one. Or drop their weapon if
                    // they are not standing over a weapon pickup.
                    area.swapOrDropPlayerWeapon(ingamePlayer);
                } else {
                    // The player has no weapon but is trying to pick one up.
                    area.pickUpPlayerWeapon(ingamePlayer);
                }
            }
        }
    }

    /**
     * Draw the area.
     * @param batch The sprite batch.
     */
    public void draw(SpriteBatch batch) {
        // Draw the area.
        this.areaRenderer.render(batch, this.areaCamera, this.area);
        // Draw the HUD.
        this.areaHudRenderer.render(batch, area.getIngamePlayers());
    }

    @Override
    public StateType getStateType() {
        return StateType.GAME;
    }
}
