package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.pickup.Pickup;
import com.dumbpug.ohm.pickup.PickupPool;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.PlayerRenderer;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.PickupResources;
import java.util.ArrayList;

/**
 * The area renderer.
 */
public class AreaRenderer {

    /**
     * Render an area.
     * @param batch The sprite batch.
     * @param camera The area camera.
     * @param area The area to render.
     */
    public void render(SpriteBatch batch, AreaCamera camera, Area area) {
        // Update the area camera.
        camera.update();
        // Apply the area camera projection matrix to the application sprite batch.
        batch.setProjectionMatrix(camera.combined);
        // Draw the area background.
        batch.draw(AreaResources.background, Gdx.graphics.getWidth() * -0.5f,
                Gdx.graphics.getHeight() * -0.5f, Gdx.graphics.getWidth() * 2f, Gdx.graphics.getHeight() * 2f);
        // Draw the platforms.
        drawPlatforms(batch, area.getPlatforms());
        // Draw the shadows of the entities in the area.
        drawShadows(batch, area);
        // Draw the projectiles.
        drawProjectiles(batch, area.getProjectilePool().getProjectiles());
        // Draw the pickups.
        drawPickups(batch, area.getPickupPool());
        // Draw the players.
        for (IngamePlayer ingamePlayer : area.getIngamePlayers()) {
            PlayerRenderer.render(batch, ingamePlayer);
        }
    }

    /**
     * Draw the shadows of the entities in the area.
     * @param batch The sprite batch.
     * @param area The area.
     */
    private void drawShadows(SpriteBatch batch, Area area) {
        // Draw the player shadows.
        for (IngamePlayer ingamePlayer : area.getIngamePlayers()) {
            // Get the physics box for the current player.
            Box playerPhysicsBox = ingamePlayer.getPlayer().getPhysicsBox();
            // Draw the shadow for the player.
            batch.draw(AreaResources.shadow_player, playerPhysicsBox.getX(), playerPhysicsBox.getY() - Constants.RENDER_SHADOW_OFFSET);
        }
        // Draw the pickup shadows.
        for (Pickup pickup : area.getPickupPool().getPickups()) {
            // Get the physics box for the current pickup.
            Box pickupPhysicsBox = pickup.getPhysicsBox();
            // Draw the shadow for the pickup.
            batch.draw(AreaResources.shadow_pickup, pickupPhysicsBox.getX(), pickupPhysicsBox.getY());
        }
    }

    /**
     * Draw the platforms.
     * @param batch The sprite batch.
     * @param platforms The platforms.
     */
    private void drawPlatforms(SpriteBatch batch, ArrayList<Platform> platforms) {
        for (int y = Constants.AREA_PLATFORMS_SIZE - 1; y >= 0; y--) {
            for (int x = Constants.AREA_PLATFORMS_SIZE - 1; x >= 0; x--) {
                // Get the platform.
                Platform platform = getPlatformAt(platforms, x, y);
                // Only draw the platform if it is raised.
                if (platform.isRaised()) {
                    batch.draw(AreaResources.platform, x * Constants.PLATFORM_SIZE, y * Constants.PLATFORM_SIZE);
                }
            }
        }
    }

    /**
     * Draw the projectiles.
     * @param batch The sprite batch.
     * @param projectiles The projectiles.
     */
    private void drawProjectiles(SpriteBatch batch, ArrayList<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            projectile.draw(batch);
        }
    }

    /**
     * Draw the pickups.
     * @param batch The sprite batch.
     * @param pickupPool The pool of pickups to draw.
     */
    private void drawPickups(SpriteBatch batch, PickupPool pickupPool) {
        for (Pickup pickup : pickupPool.getPickups()) {
            // Get the physics box of the pickup.
            Box pickupPhysicsBox = pickup.getPhysicsBox();
            // Get the current frame of the pickup animation.
            batch.draw(PickupResources.getAnimationFor(
                    pickup.getType()).getCurrentFrame(true), pickupPhysicsBox.getX(), pickupPhysicsBox.getY());
        }
    }

    /**
     * Get the platform at the x/y position.
     * @param platforms The platforms.
     * @param x The x position.
     * @param y The y position.
     * @return The platform at the x/y position.
     */
    private Platform getPlatformAt(ArrayList<Platform> platforms, int x, int y) {
        for (Platform platform : platforms) {
            if (platform.getPlatformPositionX() == x && platform.getPlatformPositionY() == y) {
                return platform;
            }
        }
        return null;
    }
}
