package com.dumbpug.ohm.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.player.IngamePlayer;
import java.util.ArrayList;

/**
 * The HUD renderer.
 */
public class HUDRenderer {

    /**
     * Render the HUD.
     * @param batch The sprite batch.
     * @param ingamePlayers The in-game players.
     */
    public void render(SpriteBatch batch, ArrayList<IngamePlayer> ingamePlayers) {
        // Render a status box for each player.
        for (IngamePlayer player : ingamePlayers) {
            StatusBox.render(batch, ingamePlayers.size(), ingamePlayers.indexOf(player), player);
        }
    }
}
