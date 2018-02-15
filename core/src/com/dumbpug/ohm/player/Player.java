package com.dumbpug.ohm.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.PlayerResources;

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
     * The play status of the player.
     */
    private Status status;

    /**
     * Initialise a new instance of the Player class.
     * @param playerPhysicsBox The player physics box.
     */
    public Player(PlayerPhysicsBox playerPhysicsBox) {
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
     * Get the player status.
     * @return The player status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the player status.
     * @param status The player status.
     */
    public void setStatus(Status status) {
        this.status = status;
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
        world.addBox(this.physicsBox);
    }

    /**
     * Tick the player.
     */
    public void tick() {
    }

    /**
     * Draw the player.
     * @param batch
     */
    public void draw(SpriteBatch batch) {
        // Calculate the x position of where to draw the sprite.
        float x = this.physicsBox.getX() - ((Constants.PLAYER_SIZE - Constants.PLAYER_PHYSICS_SIZE_WIDTH) / 2f);
        // Which way is the player facing?
        if (this.physicsBox.getFacingDirection() == FacingDirection.LEFT) {
            if (physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_left, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_left.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        } else {
            if (physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_right, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_right.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        }
        // Draw target based on the angle of aim.
        float targetPointX = this.physicsBox.getX() + Constants.PLAYER_AIM_TARGET_DISTANCE * (float) Math.cos(this.getAngleOfAim());
        float targetPointY = this.physicsBox.getY() + Constants.PLAYER_AIM_TARGET_DISTANCE * (float) Math.sin(this.getAngleOfAim());
        batch.draw(AreaResources.target, targetPointX, targetPointY);
    }
}