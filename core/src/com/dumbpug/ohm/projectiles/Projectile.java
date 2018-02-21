package com.dumbpug.ohm.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.player.IngamePlayer;

/**
 * Represents a projectile.
 */
public abstract class Projectile implements IPhysicsEntity {
    /**
     * The physics box for the projectile.
     */
    private ProjectilePhysicsBox physicsBox;
    /**
     * The angle of offset for the firing angle of the projectile.
     */
    private float angleOfFireOffset = 0f;

    /**
     * Create a new instance of the Projectile class.
     */
    public Projectile() {
        // Create the physics box for this projectile.
        physicsBox = new ProjectilePhysicsBox(this.getSize());
        // Set the user data of the physics box to be the projectile.
        physicsBox.setUserData(this);
        // Give the physics box a name to identify it.
        physicsBox.setName("PROJECTILE");
    }

    /**
     * Fire the projectile from the specified position, aiming in the specified direction.
     * @param x     The initial x position.
     * @param y     The initial y position.
     * @param angle The angle of fire.
     */
    public void fireAt(float x, float y, float angle) {
        this.physicsBox.setX(x);
        this.physicsBox.setY(y);
        // Simulate shooting this projectile in the physics environment
        // We are also applying the projectiles own angle of fire offset.
        this.physicsBox.applyVelocityInDirection(angle, this.getFireVelocity());
    }

    /**
     * Called when this projectile hits an in-game player.
     * @param ingamePlayer The in-game player.
     */
    public void onPlayerHit(IngamePlayer ingamePlayer) {
        System.out.println("Hit Player for " + this.getDamage() + " damage!!!!");
        ingamePlayer.applyDamage(this.getDamage());
    }

    /**
     * Get the size of the projectile.
     * @return The projectile size.
     */
    public abstract float getSize();

    /**
     * Get the velocity of this projectile at the point of it firing.
     * @return The velocity of this projectile at the point of it firing.
     */
    public abstract float getFireVelocity();

    /**
     * Get the amount of damage dealt on a hit.
     * @return The amount of damage dealt on a hit.
     */
    public abstract int getDamage();

    /**
     * Get the sprite for this projectile.
     * @return The sprite for this projectile.
     */
    public abstract Sprite getSprite();

    /**
     * Set the angle of offset for the firing angle of the projectile.
     * @param angleOfFireOffset The angle of offset for the firing angle of the projectile.
     */
    public void setAngleOfFireOffset(float angleOfFireOffset) {
        this.angleOfFireOffset = angleOfFireOffset;
    }

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    @Override
    public Box getPhysicsBox() {
        return this.physicsBox;
    }
}
