package com.dumbpug.ohm.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.resources.FontPack;

/**
 * The HUD showing development info.
 */
public class DevHUDRenderer {
    /**
     * The dev HUD camera.
     */
    OrthographicCamera camera;
    /**
     * The font with which to draw our HUD.
     */
    private BitmapFont font;

    /**
     * Creates a new instance of the DevHUDRenderer class.
     */
    public DevHUDRenderer() {
        // Create the HUD camera.
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.HUD_CAMERA_ZOOM;
        camera.position.set(0, 0, 0);
        camera.update();
        // Set the HUD font.
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size                  = Constants.FONT_DEV_HUD_SIZE;
        font                            = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
        font.setColor(Color.WHITE);
    }

    /**
     * Render the HUD.
     * @param batch The sprite batch.
     */
    public void render(SpriteBatch batch) {
        // Apply the HUD camera projection matrix to the application sprite batch.
        batch.setProjectionMatrix(camera.combined);
        // Create the details string to be drawn to the screen.
        String details = "";
        // Add the game FPS.
        details += "FPS                         : " + Gdx.graphics.getFramesPerSecond() + "\n";
        // Add the game screen dimensions.
        details += "Display (width/height)      : " + Gdx.graphics.getWidth() + "/" + Gdx.graphics.getHeight() + "\n";
        // Draw the actual details to the screen.
        font.draw(batch, details, 5, Gdx.graphics.getHeight() - 5, Gdx.graphics.getWidth(), Align.left, true);
    }
}
