package com.dumbpug.ohm.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.nbp.Environment;

/**
 * A character.
 */
public abstract class Character {
    /** The physics box for the character. */
    public CharacterPhysicsBox characterPhysicsBox;

    /**
     * Create a new instance of the Character class.
     * @param characterPhysicsBox The character physics box.
     */
    public Character(CharacterPhysicsBox characterPhysicsBox) {
        this.characterPhysicsBox = characterPhysicsBox;
    }

    /**
     * Get the character X position.
     * @return X position
     */
    public float getX() { return this.characterPhysicsBox.getX(); }

    /**
     * Get the character Y position.
     * @return Y position
     */
    public float getY() { return this.characterPhysicsBox.getY(); }

    /**
     * Set the character position.
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.characterPhysicsBox.setX(x);
        this.characterPhysicsBox.setY(y);
    }

    /**
     * Move the character to the left.
     */
    public void moveLeft() { this.characterPhysicsBox.moveLeft(); }

    /**
     * Move the character to the right.
     */
    public void moveRight() { this.characterPhysicsBox.moveRight(); }

    /**
     * Make the character jump if he can.
     * @return Whether the character was able to jump.
     */
    public boolean jump() { return this.characterPhysicsBox.jump(); }

    /**
     * Add the character physics box to the specified physics world.
     * @param world
     * @param x
     * @param y
     */
    public void addToPhysicsWorld(Environment world, float x, float y) {
        // Set the position of the characters physics box.
        this.setPosition(x, y);
        // Add the characters physics box to the world.
        world.addBox(this.characterPhysicsBox);
    }

    /**
     * Tick the character
     */
    public abstract void tick();

    /**
     * Draw the character
     * @param batch
     */
    public abstract void draw(SpriteBatch batch);
}
