package com.dumbpug.ohm.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.pickup.Pickup;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.Player;

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
     * The owner of this projectile.
     * This will most likely be the player that fired it.
     */
    private Player owner = null;

    /**
     * Create a new instance of the Projectile class.
     */
    public Projectile() {
        // Create the physics box for this projectile.
        physicsBox = this.createPhysicsBox();
        // Set the user data of the physics box to be the projectile.
        physicsBox.setUserData(this);
        // Give the physics box a name to identify it.
        physicsBox.setName("PROJECTILE");
    }

    /**
     * Get the owner of this projectile.
     * This will most likely be the player that fired it.
     * @return The owner of this projectile.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the owner of this projectile.
     * This will most likely be the player that fired it.
     * @param owner The owner of this projectile.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
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
     * Tick the projectile.
     */
    public void tick() {}

    /**
     * Set the angle of offset for the firing angle of the projectile.
     * @param angleOfFireOffset The angle of offset for the firing angle of the projectile.
     */
    public void setAngleOfFireOffset(float angleOfFireOffset) {
        this.angleOfFireOffset = angleOfFireOffset;
    }

    /**
     * Get the angle of offset for the firing angle of the projectile.
     * @return The angle of offset for the firing angle of the projectile.
     */
    public float getAngleOfFireOffset() {
        return this.angleOfFireOffset;
    }

    /**
     * Gets the rotation of a projectile based on its current velocity.
     * @return The rotation of a projectile based on its current velocity.
     */
    public float getRotation() {
        return (float) -(Math.atan2(this.physicsBox.getVelX(), this.physicsBox.getVelY())/(Math.PI/180));
    }

    /**
     * Gets whether this projectile is stale.
     * This means that it has hit or has gone well out of the bounds of the screen.
     * @return Whether this projectile is stale.
     */
    public boolean isStale() {
        return this.physicsBox.isStale();
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
     * @param hitPlayer The in-game player that this projectile hit.
     */
    public void onPlayerHit(IngamePlayer hitPlayer) {
        // Apply the projectile damage to the player we hit.
        hitPlayer.applyDamage(this.getDamage());
        // The impact of the projectile should push the hit player slightly.
        hitPlayer.getPlayer().getPhysicsBox()
                .applyImpulse(this.getPhysicsBox().getVelX(), this.getPhysicsBox().getVelY());
    }

    /**
     * Called when this projectile hits a pickup.
     * @param hitPickup The pickup that this projectile hit.
     */
    public void onPickupHit(Pickup hitPickup) {
        // The impact of the projectile should push the hit pickup slightly.
        hitPickup.getPhysicsBox().applyImpulse(this.getPhysicsBox().getVelX(), this.getPhysicsBox().getVelY());
    }

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    @Override
    public Box getPhysicsBox() {
        return this.physicsBox;
    }

    /**
     * Draw the projectile.
     * @param batch The sprite batch.
     */
    public void draw(SpriteBatch batch) {
        // Get the sprite for this projectile.
        Sprite projectileSprite = this.getSprite();
        // Set the correct position of the sprite.
        projectileSprite.setX(getPhysicsBox().getX());
        projectileSprite.setY(getPhysicsBox().getY());
        projectileSprite.setRotation(getRotation());
        // Draw the sprite.
        projectileSprite.draw(batch);
    }

    /**
     * Creates the projectile physics box for this projectile.
     * @return The projectile physics box for this projectile.
     */
    protected ProjectilePhysicsBox createPhysicsBox() {
        return new ProjectilePhysicsBox(this.getSize());
    }
}
