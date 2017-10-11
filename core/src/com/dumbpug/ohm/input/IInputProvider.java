package com.dumbpug.ohm.input;

/**
 * An input provider interface.
 */
public interface IInputProvider {

    /**
     * Is the specified control pressed.
     * @param control
     * @return is pressed
     */
    boolean isControlPressed(Control control);

    /**
     * Is the specified control pressed.
     * @param control
     * @return is pressed
     */
    boolean isControlJustPressed(Control control);

    /**
     * Reset the input.
     */
    void reset();
}
