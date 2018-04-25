package com.dumbpug.ohm.projectiles;

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
     * Create a new instance of the TrackingRocket class.
     * @param tracker The tracker to use in following enemy players.
     */
    public TrackingRocket(Tracker tracker) {
        super();
        this.tracker = tracker;
    }

    /**
     * Tick the tracking rocket.
     */
    public void tick() {
        super.tick();
        // Use the tracker to determine the angle between the rocket and the closest enemy.
        float angleToClosestEnemy = this.tracker.getAngleToClosestEnemy(getPhysicsBox().getCurrentOriginPoint());
        // Change the direction of this tracking rocket.
        getPhysicsBox().applyVelocityInDirection(angleToClosestEnemy, 0.6f);
    }
}
