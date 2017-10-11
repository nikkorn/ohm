package com.dumbpug.ohm.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * A provider for keyboard input.
 */
public class KeyboardInputProvider implements IInputProvider {

    @Override
    public boolean isControlPressed(Control control) {
        switch (control) {
            case JUMP:
                return Gdx.input.isButtonPressed(Input.Keys.SPACE);
            case ELECTRO_CHARGE:
                return Gdx.input.isButtonPressed(Input.Keys.SHIFT_LEFT);
            case LEFT:
                return Gdx.input.isButtonPressed(Input.Keys.A);
            case RIGHT:
                return Gdx.input.isButtonPressed(Input.Keys.D);
        }
        return false;
    }

    @Override
    public boolean isControlJustPressed(Control control) {
        switch (control) {
            case JUMP:
                return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
            case ELECTRO_CHARGE:
                return Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT);
            case LEFT:
                return Gdx.input.isKeyJustPressed(Input.Keys.A);
            case RIGHT:
                return Gdx.input.isKeyJustPressed(Input.Keys.D);
        }
        return false;
    }

    @Override
    public void reset() {}
}
