package com.dumbpug.ohm.character.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.character.Direction;
import com.dumbpug.ohm.resources.AreaResources;
import com.dumbpug.ohm.resources.PlayerResources;

/**
 * The player.
 */
public class Player extends com.dumbpug.ohm.character.Character {
    /** The player physics box. */
    private PlayerPhysicsBox physicsBox;
    /** The player angle of aim. */
    private float angleOfAim = 0f;

    /**
     * Initialise a new instance of the Player class.
     * @param playerPhysicsBox The player physics box.
     */
    public Player(PlayerPhysicsBox playerPhysicsBox) {
        super(playerPhysicsBox);
        this.physicsBox = playerPhysicsBox;
        // Make the player float.
        this.physicsBox.setAffectedByGravity(false);
    }

    public float getAngleOfAim() {
        return angleOfAim;
    }

    public void setAngleOfAim(float angleOfAim) {
        this.angleOfAim = angleOfAim;
    }

    /**
     * Tick the player.
     */
    public void tick() {}

    @Override
    public void draw(SpriteBatch batch) {
        // Calculate the x position of where to draw the sprite.
        float x = this.physicsBox.getX() - ((Constants.PLAYER_SIZE - Constants.PLAYER_PHYSICS_SIZE_WIDTH) / 2f);
        // Which way is the player facing?
        if (this.physicsBox.getFacingDirection() == Direction.LEFT) {
            if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_left, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_left.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        } else {
            if(physicsBox.isIdle()) {
                // If our player is idle then draw idle body.
                batch.draw(PlayerResources.ohm_standing_right, x, this.physicsBox.getY());
            } else {
                // If our player is not airborne and not idle, they must be walking. Draw walk animation.
                batch.draw(PlayerResources.ohm_walking_right.getCurrentFrame(true), x, this.physicsBox.getY());
            }
        }
        // Draw target based on the angle of aim.
        float targetPointX = this.physicsBox.getX() + Constants.PLAYER_AIM_TARGET_DISTANCE * (float)Math.cos(this.getAngleOfAim());
        float targetPointY = this.physicsBox.getY() - Constants.PLAYER_AIM_TARGET_DISTANCE * (float)Math.sin(this.getAngleOfAim());
        batch.draw(AreaResources.target, targetPointX, targetPointY);
    }
}
