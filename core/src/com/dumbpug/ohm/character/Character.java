package com.dumbpug.ohm.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A character.
 */
public abstract class Character {

    /**
     * Handle the character landing on the floor.
     */
    public void onLanding() {}

    /**
     * Draw the character
     * @param batch
     */
    public abstract void draw(SpriteBatch batch);
}
