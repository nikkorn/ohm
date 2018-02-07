package com.dumbpug.ohm.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.nbp.point.Point;

/**
 * A provider for keyboard input.
 */
public class KeyboardInputProvider implements IInputProvider {

    @Override
    public boolean isControlPressed(Control control) {
        // Determine which control we are checking the state of.
        switch (control) {
            case ACCEPT:
                return Gdx.input.isKeyPressed(Input.Keys.SPACE);
            case SECONDARY:
                return Gdx.input.isKeyPressed(Input.Keys.E);
            case UP:
                return Gdx.input.isKeyPressed(Input.Keys.W);
            case DOWN:
                return Gdx.input.isKeyPressed(Input.Keys.S);
            case LEFT:
                return Gdx.input.isKeyPressed(Input.Keys.A);
            case RIGHT:
                return Gdx.input.isKeyPressed(Input.Keys.D);
        }
        return false;
    }

    @Override
    public boolean isControlJustPressed(Control control) {
        // Determine which control we are checking the state of.
        switch (control) {
            case ACCEPT:
                return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
            case SECONDARY:
                return Gdx.input.isKeyJustPressed(Input.Keys.E);
            case UP:
                return Gdx.input.isKeyJustPressed(Input.Keys.W);
            case DOWN:
                return Gdx.input.isKeyJustPressed(Input.Keys.S);
            case LEFT:
                return Gdx.input.isKeyJustPressed(Input.Keys.A);
            case RIGHT:
                return Gdx.input.isKeyJustPressed(Input.Keys.D);
        }
        return false;
    }

    @Override
    public float getAngleOfAim() {
        float xDelta = Gdx.input.getDeltaX();
        float yDelta = Gdx.input.getDeltaY();
        // We don't want to count tiny movements on the stick.
        if (xDelta > 2 || xDelta < -2 || yDelta > 2 || yDelta < -2) {
            return NBPMath.getAngleBetweenPoints(new Point(0, 0), new Point(xDelta, yDelta));
        }
        // We cannot get our angle of aim.
        return -1;
    }

    @Override
    public void reset() {}
}
