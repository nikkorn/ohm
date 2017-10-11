package com.dumbpug.ohm.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.math.Vector3;

/**
 * A provider for Ouya input.
 */
public class OuyaInputProvider implements IInputProvider, ControllerListener {

    @Override
    public boolean isControlPressed(Control control) {

        // Get the first available controller.
        Controller controller = Controllers.getControllers().first();

        switch (control) {
            case JUMP:
                return controller.getButton(Ouya.BUTTON_O);
            case ELECTRO_CHARGE:
                return controller.getButton(Ouya.BUTTON_R1);
        }
        return false;
    }

    @Override
    public boolean isControlJustPressed(Control control) {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void connected(Controller controller) {}

    @Override
    public void disconnected(Controller controller) {}

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) { return false; }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) { return false; }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) { return false; }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) { return false; }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) { return false; }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) { return false; }
}
