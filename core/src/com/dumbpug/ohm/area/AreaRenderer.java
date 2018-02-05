package com.dumbpug.ohm.area;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.resources.AreaResources;

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
        // Draw background.
        batch.draw(AreaResources.background, 0, 0);
        // Draw player.
        area.getPlayer().draw(batch);
    }
}
