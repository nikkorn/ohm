package com.dumbpug.ohm.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.math.Vector3;
import java.util.ArrayList;

/**
 * A provider for Ouya input.
 */
public class OuyaInputProvider implements IInputProvider, ControllerListener {

    /** The list of just pressed controls.*/
    private ArrayList<Control> justPressedControls = new ArrayList<Control>();

    @Override
    public boolean isControlPressed(Control control) {
        // We can only continue if there is a controller attached.
        if (Controllers.getControllers().size > 0) {
            // Get the first available controller.
            Controller controller = Controllers.getControllers().first();
            // Get the x axis of the left stick (movement).
            float leftXAxis = controller.getAxis(Ouya.AXIS_LEFT_X);
            // Determine which control we are checking the state of.
            switch (control) {
                case JUMP:
                    return controller.getButton(Ouya.BUTTON_O);
                case ELECTRO_CHARGE:
                    return controller.getButton(Ouya.BUTTON_R1);
                case LEFT:
                    // Whether we are moving left depends on the position of the stick.
                    return leftXAxis < -0.5;
                case RIGHT:
                    // Whether we are moving right depends on the position of the stick.
                    return leftXAxis > 0.5;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public boolean isControlJustPressed(Control control) {
        // We only care about handing inputs where we care about acting upon single presses.
        switch (control) {
            case JUMP:
            case ELECTRO_CHARGE:
                return justPressedControls.contains(control);
            default:
                return this.isControlPressed(control);
        }
    }

    @Override
    public void reset() {
        // Forget about any buttons that were just pressed.
        justPressedControls.clear();
    }

    @Override
    public void connected(Controller controller) {}

    @Override
    public void disconnected(Controller controller) {}

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        // We only care about handing inputs where we care about acting upon single presses.
        switch (buttonCode) {
            case Ouya.BUTTON_O:
                this.justPressedControls.add(Control.JUMP);
                return true;
            case Ouya.BUTTON_R1:
                this.justPressedControls.add(Control.ELECTRO_CHARGE);
                return true;
        }
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
