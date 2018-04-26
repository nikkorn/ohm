package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.player.Tracker;

/**
 * A rocket which can alter its velocity to follow the closest enemy player.
 */
public class TrackingRocket extends Rocket {
    /**
     * The tracker to use in following enemy players.
     */
    private Tracker tracker;
    /**
     * The projectile launch time.
     */
    private long launched;

    /**
     * Create a new instance of the TrackingRocket class.
     * @param tracker The tracker to use in following enemy players.
     */
    public TrackingRocket(Tracker tracker) {
        super();
        // Set the tracker for the rocket to use.
        this.tracker = tracker;
        // Set the launched time on the projectile.
        this.launched = System.currentTimeMillis();
    }

    /**
     * Get the velocity of this projectile at the point of it firing.
     * @return The velocity of this projectile at the point of it firing.
     */
    @Override
    public float getFireVelocity() {
        return Constants.PROJECTILE_TRACKING_ROCKET_FIRE_VELOCITY;
    }

    /**
     * Tick the tracking rocket.
     */
    public void tick() {
        super.tick();
        // We only want to start tracking after a launch delay.
        if (System.currentTimeMillis() < launched + Constants.PROJECTILE_TRACKING_ROCKET_DELAY) {
            return;
        }
        // Use the tracker to determine the position of the closest enemy.
        float angleToClosestEnemy = this.tracker.getAngleToClosestEnemy(getPhysicsBox().getCurrentOriginPoint());
        // Change the direction of this tracking rocket.
        getPhysicsBox().setVelX(getPhysicsBox().getVelX() * 0.6f);
        getPhysicsBox().setVelY(getPhysicsBox().getVelY() * 0.6f);
        getPhysicsBox().applyVelocityInDirection(angleToClosestEnemy, 0.6f);
    }
}
