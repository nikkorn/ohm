package com.dumbpug.ohm.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.point.Point;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.ColouredPlayerResources;
import com.dumbpug.ohm.resources.PlayerResources;
import com.dumbpug.ohm.resources.WeaponResources;
import com.dumbpug.ohm.weapons.Weapon;

/**
 * The player renderer.
 */
public class PlayerRenderer {

    /**
     * Render a player.
     * @param batch The sprite batch.
     * @param player The in-game player to render.
     */
    public static void render(SpriteBatch batch, IngamePlayer player) {
        // Draw the player marker that sits underneath the player.
        drawMarker(batch, player);
        // Draw the character.
        drawCharacter(batch, player.getPlayer());
        // Draw the player's equipped weapon, if they have one.
        if (player.getStatus().getEquippedWeapon() != null) {
            drawEquippedWeapon(batch, player.getPlayer().getPhysicsBox().getCurrentOriginPoint(),
                    player.getPlayer().getAngleOfAim(), player.getStatus().getEquippedWeapon());
        }
    }

    /**
     * Draws the marker that sits under the player.
     * @param batch The sprite batch.
     * @param player The player.
     */
    private static void drawMarker(SpriteBatch batch, IngamePlayer player) {
        // Get the current player physics box.
        PlayerPhysicsBox playerPhysicsBox = (PlayerPhysicsBox)player.getPlayer().getPhysicsBox();
        // Get the player resources for the player colour.
        ColouredPlayerResources resources = PlayerResources.getResourcesForColour(player.getPlayerColour());
        // Draw the marker.
        batch.draw(resources.getPlayerMarker(), playerPhysicsBox.getX() - 4, playerPhysicsBox.getY() - 3);
    }

    /**
     * Draw the equipped weapon of the player.
     * @param batch The sprite batch.
     * @param playerOrigin The player origin point.
     * @param angleOfAim The player angle of aim.
     * @param equippedWeapon The equipped weapon.
     */
    private static void drawEquippedWeapon(SpriteBatch batch, Point playerOrigin, float angleOfAim, Weapon equippedWeapon) {
        // Get the sprite for the equipped weapon type.
        Sprite weaponSprite = WeaponResources.getWeaponSprite(equippedWeapon.getType());
        // Prepare the sprite to be drawn.
        weaponSprite.setRotation(0);
        weaponSprite.setOrigin(-Constants.PLAYER_PHYSICS_SIZE_WIDTH, weaponSprite.getHeight() / 2); // TODO Tweak this!
        weaponSprite.setRotation(angleOfAim);
        weaponSprite.setPosition(playerOrigin.getX(), playerOrigin.getY());
        // Draw the sprite.
        weaponSprite.draw(batch);
    }

    /**
     * Draw the actual character.
     * @param batch The sprite batch.
     * @param player The player.
     */
    private static void drawCharacter(SpriteBatch batch, Player player) {
        // Get the current player physics box.
        PlayerPhysicsBox playerPhysicsBox = (PlayerPhysicsBox)player.getPhysicsBox();
        // Calculate the x position of where to draw the sprite.
        float x = playerPhysicsBox.getX() - ((Constants.PLAYER_SIZE - Constants.PLAYER_PHYSICS_SIZE_WIDTH) / 2f);
        // Which way is the player facing?
        if (playerPhysicsBox.getFacingDirection() == FacingDirection.LEFT) {
            if (playerPhysicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_left, x, playerPhysicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_left.getCurrentFrame(true), x, playerPhysicsBox.getY());
            }
        } else {
            if (playerPhysicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_right, x, playerPhysicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_right.getCurrentFrame(true), x, playerPhysicsBox.getY());
            }
        }
        // Draw target based on the angle of aim.
        float angleOfAim   = player.getAngleOfAim();
        float targetPointX = playerPhysicsBox.getX() + Constants.PLAYER_AIM_TARGET_DISTANCE * (float) Math.cos(Math.toRadians(angleOfAim));
        float targetPointY = playerPhysicsBox.getY() + Constants.PLAYER_AIM_TARGET_DISTANCE * (float) Math.sin(Math.toRadians(angleOfAim));
        batch.draw(AreaResources.target, targetPointX, targetPointY);
    }
}
