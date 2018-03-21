package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.ohm.player.PlayerColour;

/**
 * StatusBar Resources for a particular player colour.
 */
public class StatusBarResources {

    /**
     * The health bar stage sprites.
     */
    private static Texture health_1;
    private static Texture health_2;
    private static Texture health_3;
    private static Texture health_4;
    private static Texture health_5;
    private static Texture health_6;
    private static Texture health_7;
    private static Texture health_8;

    /**
     * The colour-specific status bar elements.
     */
    private Texture health_bar;
    private Texture power_bar;
    private Texture weapon_bar;

    /**
     * Creates a new instance of the StatusBarResources class.
     * @param colour The player colour.
     */
    public StatusBarResources(PlayerColour colour) {
        // Load the resources from disk.
        this.load(colour);
    }

    /**
     * Load the colour-specific resources.
     * @param colour The player colour to load resources for.
     */
    private void load(PlayerColour colour) {
        String panelResourcesDir = "graphics/hud/status_bar/panels/" + colour.toString().toLowerCase() + "/";
        health_bar = new Texture(Gdx.files.internal(panelResourcesDir + "health_panel.png"));
        power_bar  = new Texture(Gdx.files.internal(panelResourcesDir + "power_panel.png"));
        weapon_bar = new Texture(Gdx.files.internal(panelResourcesDir + "weapon_panel.png"));
    }

    /**
     * Load the common resources.
     */
    public static void loadCommonAssets() {
        String healthResourcesDir = "graphics/hud/status_bar/health/";
        health_1 = new Texture(Gdx.files.internal(healthResourcesDir + "1.png"));
        health_2 = new Texture(Gdx.files.internal(healthResourcesDir + "2.png"));
        health_3 = new Texture(Gdx.files.internal(healthResourcesDir + "3.png"));
        health_4 = new Texture(Gdx.files.internal(healthResourcesDir + "4.png"));
        health_5 = new Texture(Gdx.files.internal(healthResourcesDir + "5.png"));
        health_6 = new Texture(Gdx.files.internal(healthResourcesDir + "6.png"));
        health_7 = new Texture(Gdx.files.internal(healthResourcesDir + "7.png"));
        health_8 = new Texture(Gdx.files.internal(healthResourcesDir + "8.png"));
    }

    /**
     * Gets the health bar texture.
     * @return The health bar texture.
     */
    public Texture getHealthBar() {
        return this.health_bar;
    }

    /**
     * Gets the power bar texture.
     * @return The power bar texture.
     */
    public Texture getPowerBar() {
        return this.power_bar;
    }

    /**
     * Gets the weapon bar texture.
     * @return The weapon bar texture.
     */
    public Texture getWeaponBar() {
        return this.weapon_bar;
    }

    /**
     * Gets a health bar stage texture.
     * @param health The level of health.
     * @return A health bar stage texture.
     */
    public Texture getHealthBarStage(int health) {
        switch(health) {
            case 1:
                return health_1;
            case 2:
                return health_2;
            case 3:
                return health_3;
            case 4:
                return health_4;
            case 5:
                return health_5;
            case 6:
                return health_6;
            case 7:
                return health_7;
            case 8:
                return health_8;
            default:
                // This should not happen! But...
                return health_1;
        }
    }
}
