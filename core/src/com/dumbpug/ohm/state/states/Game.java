package com.dumbpug.ohm.state.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.area.AreaCamera;
import com.dumbpug.ohm.area.AreaRenderer;
import com.dumbpug.ohm.hud.AreaHUDRenderer;
import com.dumbpug.ohm.player.IngamePlayer;
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
            for (IngamePlayer ingamePlayer : players) {
                ingamePlayer.processInput(this.area);
            }
        }
        // Tick the area.
        this.area.tick(this.areaCamera);
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
