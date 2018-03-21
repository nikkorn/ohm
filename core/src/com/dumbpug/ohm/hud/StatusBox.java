package com.dumbpug.ohm.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.resources.HUDResources;
import com.dumbpug.ohm.resources.StatusBarResources;
import com.dumbpug.ohm.weapons.Secondary;
import com.dumbpug.ohm.weapons.Weapon;

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
        // Get the status bar resources for the player colour.
        StatusBarResources statusBarResources = HUDResources.getStatusBarResources(player.getPlayerColour());
        // Draw the player health bar.
        batch.draw(statusBarResources.getHealthBar(), Constants.HUD_STATUS_BOX_WIDTH * position, 50);
        // Draw the player health bar stage.
        batch.draw(statusBarResources.getHealthBarStage(player.getStatus().getLife()), Constants.HUD_STATUS_BOX_WIDTH * position, 50);
        // Draw the weapon panel if we have a weapon equipped.
        if (player.getStatus().getEquippedWeapon() != null) {
            drawWeaponPanel(batch, position, player.getStatus().getEquippedWeapon());
        }
        // Draw the power panel if we have a power equipped.
        if (player.getStatus().getEquippedSecondary() != null) {
            drawPowerPanel(batch, position, player.getStatus().getEquippedSecondary());
        }
    }

    /**
     * Draw the weapon panel.
     * @param batch The sprite batch.
     * @param weapon The equipped weapon.
     */
    private static void drawWeaponPanel(SpriteBatch batch, int position, Weapon weapon) {
    }

    /**
     * Draw the power panel.
     * @param batch The sprite batch.
     * @param secondary The equipped power.
     */
    private static void drawPowerPanel(SpriteBatch batch, int position, Secondary secondary) {
    }
}
