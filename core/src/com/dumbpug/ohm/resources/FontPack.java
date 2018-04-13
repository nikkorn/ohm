package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * The game font resources.
 */
public class FontPack {
    /**
     * Singleton instance of FontPack.
     */
    private static FontPack instance;
    /**
     * The games main font generator.
     */
    private static FreeTypeFontGenerator mainFontGenerator;

    /**
     * The games font types.
     */
    public enum FontType { MAIN_FONT }

    /**
     * Create a new instance of the FontPack class.
     */
    private FontPack() {
        mainFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/hachicro.ttf"));
    }

    /**
     * Get our FontPack singleton instance.
     * @return The FontPack singleton instance.
     */
    public static FontPack getFontPack() {
        if (instance == null) {
            instance = new FontPack();
        }
        return instance;
    }

    /**
     * Get a game font.
     * @param type
     * @param fontParameter
     * @return font The font.
     */
    public BitmapFont getFont(FontType type, FreeTypeFontGenerator.FreeTypeFontParameter fontParameter) {
        switch (type) {
            case MAIN_FONT:
                return mainFontGenerator.generateFont(fontParameter);
        }
        return null;
    }
}
