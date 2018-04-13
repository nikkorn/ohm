package com.dumbpug.ohm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.hud.DevHUDRenderer;
import com.dumbpug.ohm.input.IInputProvider;
import com.dumbpug.ohm.input.KeyboardInputProvider;
import com.dumbpug.ohm.input.OuyaInputProvider;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.HUDResources;
import com.dumbpug.ohm.resources.ParticleResources;
import com.dumbpug.ohm.resources.PickupResources;
import com.dumbpug.ohm.resources.PlayerResources;
import com.dumbpug.ohm.resources.WeaponResources;
import com.dumbpug.ohm.state.StateManager;
import com.dumbpug.ohm.state.states.Title;

public class Ohm extends ApplicationAdapter {
    /**
     * The sprite batch to use throughout the game.
     */
    private SpriteBatch batch;
    /**
     * The state manager.
     */
    private StateManager stateManager;
    /**
     * The developer HUD renderer.
     */
    private DevHUDRenderer devHUDRenderer;
    /**
     * The input provider.
     */
    private static IInputProvider inputProvider;

    @Override
    public void create() {

        // Create the game sprite batch.
        batch = new SpriteBatch();

        // Create a developer HUD renderer.
        this.devHUDRenderer = new DevHUDRenderer();

        // Load the game resources.
        AreaResources.load();
        PlayerResources.load();
        ParticleResources.load();
        PickupResources.load();
        WeaponResources.load();
        HUDResources.load();

        // Create the input provider specific to the system.
        if (Ouya.isRunningOnOuya()) {
            OuyaInputProvider provider = new OuyaInputProvider();
            this.inputProvider = provider;
            Controllers.addListener(provider);
        } else {
            this.inputProvider = new KeyboardInputProvider();
        }

        // Create the game state manager.
        stateManager = new StateManager();

        // Set the initial game state.
        stateManager.setState(new Title());
    }

    @Override
    public void render() {

        // Do we want to exit? (only desktop version)
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // Toggle whether the system cursor is captured.
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());
        }

        // Tick the game.
        stateManager.tick();

        // Reset the input provider.
        this.inputProvider.reset();

        // Clear the screen.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Draw the game.
        batch.begin();
        // Draw the current state.
        stateManager.draw(batch);
        // Are we drawing out dev HUD?
        if (Constants.SHOW_DEV_HUD) {
            devHUDRenderer.render(batch);
        }
        batch.end();
    }

    /**
     * Get the application input provider.
     * @return input provider
     */
    public static IInputProvider getInputProvider() {
        return Ohm.inputProvider;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
