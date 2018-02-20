package com.dumbpug.ohm.area;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.resources.AreaResources;
import java.util.ArrayList;

/**
 * The area renderer.
 */
public class AreaRenderer {

    /**
     * Render an area.
     * @param area The area to render.
     */
    public void render(SpriteBatch batch, Area area) {
        // Get the area camera.
        OrthographicCamera camera = area.getCamera();
        // Update the camera.
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        // Draw the platforms.
        drawPlatforms(batch, area.getPlatforms());

        // TODO Draw players, projectiles and pickups in Y order.
        // Draw the projectiles.
        drawProjectiles(batch, area.getProjectilePool().getProjectiles());
        // Draw players.
        for (IngamePlayer player : area.getIngamePlayers()) {
            player.getPlayer().draw(batch);
        }
    }

    /**
     * Draw the platforms.
     * @param batch
     * @param platforms
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
     * @param batch
     * @param projectiles
     */
    private void drawProjectiles(SpriteBatch batch, ArrayList<Projectile> projectiles) {
        for (Projectile projectile : projectiles) {
            // Get the sprite for this projectile.
            Sprite projectileSprite = projectile.getSprite();
            // Set the correct position of the sprite.
            projectileSprite.setX(projectile.getPhysicsBox().getX());
            projectileSprite.setY(projectile.getPhysicsBox().getY());
            projectileSprite.setRotation(0 /** TODO Set this based on directional velocity of projectile.*/);
            // Draw the sprite.
            projectileSprite.draw(batch);
        }
    }

    /**
     * Get the platform at the x/y position.
     * @param platforms
     * @param x
     * @param y
     * @return
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
