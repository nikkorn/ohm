package com.dumbpug.ohm.area;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.PlayerResources;

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
        // Draw player.
        area.getPlayer().draw(batch);
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
     * Get the platform at the x/y position.
     * @param platforms
     * @param x
     * @param y
     * @return
     */
    private Platform getPlatformAt(ArrayList<Platform> platforms, int x, int y) {
        for (Platform platform : platforms) {
            if (platform.getX() == x && platform.getY() == y) {
                return platform;
            }
        }
        return null;
    }
}
