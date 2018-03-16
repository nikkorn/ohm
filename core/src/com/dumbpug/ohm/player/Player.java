package com.dumbpug.ohm.player;

import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;

/**
 * The player.
 */
public class Player implements IPhysicsEntity {
    /**
     * The player physics box.
     */
    private PlayerPhysicsBox physicsBox;
    /**
     * The player angle of aim.
     */
    private float angleOfAim = 0f;

    /**
     * Initialise a new instance of the Player class.
     * @param playerPhysicsBox The player physics box.
     */
    public Player(PlayerPhysicsBox playerPhysicsBox) {
        // Create the physics box for this player.
        this.physicsBox = playerPhysicsBox;
        // Make the player float.
        this.physicsBox.setAffectedByGravity(false);
    }

    /**
     * Get the angle of aim.
     * @return
     */
    public float getAngleOfAim() {
        return angleOfAim;
    }

    /**
     * Set the angle of aim.
     * @param angleOfAim
     */
    public void setAngleOfAim(float angleOfAim) {
        this.angleOfAim = angleOfAim;
    }

    /**
     * Get the character X position.
     * @return X position
     */
    public float getX() {
        return this.physicsBox.getX();
    }

    /**
     * Get the character Y position.
     * @return Y position
     */
    public float getY() {
        return this.physicsBox.getY();
    }

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    @Override
    public Box getPhysicsBox() { return this.physicsBox; }

    /**
     * Whether the entity is airborne.
     * @return Whether the entity is airborne.
     */
    @Override
    public boolean isAirborne() { return false; }

    /**
     * Set the character position.
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.physicsBox.setX(x);
        this.physicsBox.setY(y);
    }

    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        this.physicsBox.moveLeft();
    }

    /**
     * Move the character to the right.
     */
    public void moveRight() {
        this.physicsBox.moveRight();
    }

    /**
     * Move the character up.
     */
    public void moveUp() {
        this.physicsBox.moveUp();
    }

    /**
     * Move the character down.
     */
    public void moveDown() {
        this.physicsBox.moveDown();
    }
}