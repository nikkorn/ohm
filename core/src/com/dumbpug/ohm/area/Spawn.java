package com.dumbpug.ohm.area;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Defines a spawn point.
 */
public class Spawn {

    /** The x position of the spawn. */
    private float x;

    /** The y position of the spawn. */
    private float y;

    /**
     * Create a new instance of the Spawn class.
     * @param spawn
     */
    public Spawn(JsonValue spawn) {
        this.x = spawn.getFloat("x");
        this.y = spawn.getFloat("y");
    }

    /**
     * Get the spawn x position.
     * @return x
     */
    public float getX() { return x; }

    /**
     * Get the spawn y position.
     * @return y
     */
    public float getY() { return y; }
}
