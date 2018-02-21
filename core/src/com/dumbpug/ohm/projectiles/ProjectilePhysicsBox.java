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
     * Create a new instance of the ProjectilePhysicsBox class.
     * @param size The size of the physics box.
     */
    public ProjectilePhysicsBox(float size) {
        super(0, 0, size, size, BoxType.KINETIC);
    }

    /**
     * Handle this projectile colliding with a player physics box.
     * @param playerPhysicsBox The player physics box.
     */
    private void handlePlayerCollision(PlayerPhysicsBox playerPhysicsBox) {
        // Get the projectile that this physics box represents.
        Projectile projectile = (Projectile)this.getUserData();
        // Projectiles should handle player collisions.
        projectile.onPlayerHit((IngamePlayer)playerPhysicsBox.getUserData());
    }

    /**
     * Handle this projectile colliding with a pickup physics box.
     * @param pickupPhysicsBox The pickup physics box.
     */
    private void handlePickupCollision(PickupPhysicsBox pickupPhysicsBox) {
        System.out.println("Hit pickup!");
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {
        // How we handle this collision depends on the type of physics box we have hit.
        if (collidingBox.getName().equals("PLAYER")) {
            handlePlayerCollision((PlayerPhysicsBox)collidingBox);
        } else if (collidingBox.getName().equals("PICKUP")) {
            handlePickupCollision((PickupPhysicsBox)collidingBox);
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
