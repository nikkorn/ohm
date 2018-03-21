package com.dumbpug.ohm.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.player.IngamePlayer;
import java.util.ArrayList;

/**
 * The HUD renderer.
 */
public class HUDRenderer {
    /**
     * The HUD camera.
     */
    OrthographicCamera camera;

    /**
     * Creates a new instance of the HUDRenderer class.
     */
    public HUDRenderer() {
        // Create the HUD camera.
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.AREA_ZOOM;
        camera.position.set(0, 0, 0);
    }

    /**
     * Render the HUD.
     * @param batch The sprite batch.
     * @param ingamePlayers The in-game players.
     */
    public void render(SpriteBatch batch, ArrayList<IngamePlayer> ingamePlayers) {
        // Update the HUD camera.
        camera.update();
        // Apply the HUD camera projection matrix to the application sprite batch.
        batch.setProjectionMatrix(camera.combined);
        // Render a status box for each player.
        for (IngamePlayer player : ingamePlayers) {
            StatusBox.render(batch, ingamePlayers.size(), ingamePlayers.indexOf(player), player);
        }
    }
}
