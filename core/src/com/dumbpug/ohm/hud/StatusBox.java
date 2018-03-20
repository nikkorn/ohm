package com.dumbpug.ohm.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.player.IngamePlayer;

/**
 * The portion of the HUD for displaying player status.
 */
public class StatusBox {

    /**
     * Render a HUD status box at the specified position for a player.
     * @param batch The sprite batch.
     * @param playerCount The number of players in the game.
     * @param position The position (1-4).
     * @param player The player
     */
    public static void render(SpriteBatch batch, int playerCount, int position, IngamePlayer player) {
        // Do not render a status bar for dead players.
        if (player.getStatus().isDead()) {
            return;
        }

    }
}
