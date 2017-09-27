package com.dumbpug.ohm.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.nbp.NBPWorld;
import com.dumbpug.ohm.resources.PlayerResources;

/**
 * The player.
 */
public class Player extends com.dumbpug.ohm.character.Character {

    /** The players physics box. */
    private PlayerPhysicsBox physicsBox;

    /** The players facing direction.*/
    private Direction facingDirection = Direction.RIGHT;

    /**
     * Initialise a new instance of the Player class.
     */
    public Player() {
        // Initialise our players physics box.
        this.physicsBox = new PlayerPhysicsBox(this, 0, 0);
    }

    /**
     * Set the players position.
     * @param x
     * @param y
     */
    public void setPosition(float x, float y) {
        this.physicsBox.setX(x);
        this.physicsBox.setY(y);
    }

    /**
     * Add the players physics box to the specified physics world.
     * @param world
     * @param x
     * @param y
     */
    public void addToPhysicsWorld(NBPWorld world, float x, float y) {
        // Set the position of the players physics box.
        this.setPosition(x, y);
        // Add the players physics box to the world.
        world.addBox(this.physicsBox);
    }

    /**
     * Update the specified camera to reflect the players position.
     * @param camera
     */
    public void grabCamera(OrthographicCamera camera) {
        // Clamp the camera horizontally to the screen.
        float x = Math.max((Gdx.graphics.getWidth() * Constants.AREA_ZOOM) / 2, this.physicsBox.getX());
        // TODO Clamp to the end of the area too.
        // Set the camera to looks at the player.
        camera.position.set(x, Constants.TILE_SIZE * Constants.AREA_TILE_HEIGHT / 2, 0);
    }

    /**
     * Move the character to the left.
     */
    public void moveLeft() {
        this.facingDirection = Direction.LEFT;
        this.physicsBox.moveLeft();
    }

    /**
     * Move the character to the right.
     */
    public void moveRight() {
        this.facingDirection = Direction.RIGHT;
        this.physicsBox.moveRight();
    }

    /**
     * Make the character jump if he can.
     * @return true if character was able to jump
     */
    public boolean jump() { return this.physicsBox.jump(); }

    @Override
    public void draw(SpriteBatch batch) {
        // Which way is the player facing?
        if (this.facingDirection == Direction.LEFT) {

            if(!physicsBox.isTouchingFloor()) {
                // Are we prepped for a wall jump?
                if (physicsBox.isPreppedForWallJump()) {
                    batch.draw(PlayerResources.ohm_jumping_right, this.physicsBox.getX(), this.physicsBox.getY());
                } else {
                    // If our player is airborne, then draw airborne body based on whether we are ascending or descending
                    if (physicsBox.getVely() > 0) {
                        batch.draw(PlayerResources.ohm_jumping_left, this.physicsBox.getX(), this.physicsBox.getY());
                    } else {
                        batch.draw(PlayerResources.ohm_falling_left, this.physicsBox.getX(), this.physicsBox.getY());
                    }
                }
            } else if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_left, this.physicsBox.getX(), this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_left.getCurrentFrame(true), this.physicsBox.getX(), this.physicsBox.getY());
            }

        } else {

            if(!physicsBox.isTouchingFloor()) {
                // Are we prepped for a wall jump?
                if (physicsBox.isPreppedForWallJump()) {
                    batch.draw(PlayerResources.ohm_jumping_left, this.physicsBox.getX(), this.physicsBox.getY());
                } else {
                    // If our player is airborne, then draw airborne body based on whether we are ascending or descending
                    if (physicsBox.getVely() > 0) {
                        batch.draw(PlayerResources.ohm_jumping_right, this.physicsBox.getX(), this.physicsBox.getY());
                    } else {
                        batch.draw(PlayerResources.ohm_falling_right, this.physicsBox.getX(), this.physicsBox.getY());
                    }
                }
            } else if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_right, this.physicsBox.getX(), this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_right.getCurrentFrame(true), this.physicsBox.getX(), this.physicsBox.getY());
            }

        }
    }
}
