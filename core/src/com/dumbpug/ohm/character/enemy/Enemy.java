package com.dumbpug.ohm.character.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An enemy.
 */
public abstract class Enemy extends com.dumbpug.ohm.character.Character {

    /**
     * Initialise a new instance of the Enemy class.
     */
    public Enemy() {}

    /**
     * Move the character to the left.
     */
    public void moveLeft() {}

    /**
     * Move the character to the right.
     */
    public void moveRight() {}

    /**
     * Make the character jump if he can.
     * @return true if character was able to jump
     */
    public boolean jump() { return true; }

    /**
     * Tick the player.
     */
    public void tick() {}

    @Override
    public void draw(SpriteBatch batch) {}
}


