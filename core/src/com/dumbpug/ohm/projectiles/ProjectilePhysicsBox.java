package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.area.pickup.PickupPhysicsBox;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.PlayerPhysicsBox;

/**
 * A physics box for a projectile.
 */
public class ProjectilePhysicsBox extends Box {
    /**
     * Whether the projectile can hit its owner.
     */
    private boolean canHitOwner = false;
    /**
     * Whether the projectile physics box is stale.
     */
    private boolean isStale = false;

    /**
     * Create a new instance of the ProjectilePhysicsBox class.
     * @param size The size of the physics box.
     */
    public ProjectilePhysicsBox(float size) {
        super(0, 0, size, size, BoxType.KINETIC);
    }

    /**
     * Gets whether this projectile is stale.
     * This means that it has hit or has gone well out of the bounds of the screen.
     * @return Whether this projectile is stale.
     */
    public boolean isStale() {
        return isStale;
    }

    /**
     * Gets whether this projectile can hit its owner.
     * A projectile wont be able to hit its owner until it
     * initially stops overlapping the player physics box.
     * @return Whether this projectile can hit its owner.
     */
    public boolean canHitOwner() {
        return canHitOwner;
    }

    /**
     * Sets whether this projectile can hit its owner.
     * A projectile wont be able to hit its owner until it
     * initially stops overlapping the player physics box.
     * @param canHitOwner Whether this projectile can hit its owner.
     */
    public void setCanHitOwner(boolean canHitOwner) {
        this.canHitOwner = canHitOwner;
    }

    /**
     * Handle this projectile colliding with a player physics box.
     * @param playerPhysicsBox The player physics box.
     */
    private void handlePlayerCollision(PlayerPhysicsBox playerPhysicsBox) {
        // Get the player that we have collided with.
        IngamePlayer player = (IngamePlayer)playerPhysicsBox.getUserData();
        // Get the projectile that this physics box represents.
        Projectile projectile = (Projectile)this.getUserData();
        // Check whether the colliding player is the owner of the projectile.
        // If so, we will have to do nothing unless we can hit them yet.
        if (!this.canHitOwner && projectile.getOwner() == player.getPlayer()) {
            return;
        }
        // Projectiles should handle player collisions.
        projectile.onPlayerHit(player);
        // This projectile has hit a player and is now stale.
        this.isStale = true;
    }

    /**
     * Handle this projectile colliding with a pickup physics box.
     * @param pickupPhysicsBox The pickup physics box.
     */
    private void handlePickupCollision(PickupPhysicsBox pickupPhysicsBox) {
        System.out.println("Hit pickup!");
        // This projectile has hit a pickup and is now stale.
        this.isStale = true;
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {
        // How we handle this collision depends on the type of physics box we have hit.
        if (collidingBox.getName().equals("PLAYER")) {
            handlePlayerCollision((PlayerPhysicsBox)collidingBox);
        } else if (collidingBox.getName().equals("PICKUP")) {
            handlePickupCollision((PickupPhysicsBox) collidingBox);
        }
    }

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, IntersectionPoint originAtCollision) {
        // TODO We hit something hard eh?
    }

    @Override
    protected void onSensorEntry(Sensor sensor, Box enteredBox) {}

    @Override
    protected void onSensorExit(Sensor sensor, Box exitedBox) {}

    @Override
    protected boolean onBloomPush(Bloom bloom, float angleOfForce, float force, float distance) { return false; }

    @Override
    protected void onBeforeUpdate() {}

    @Override
    protected void onAfterUpdate() {}

    @Override
    protected void onDeletion() {}
}
