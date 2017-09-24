package com.dumbpug.ohm.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by nik on 24/09/17.
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
