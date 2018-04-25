package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.player.Tracker;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.projectiles.TrackingRocket;
import java.util.ArrayList;

/**
 * Represents a tracking rocket launcher.
 */
public class TrackingRocketLauncher extends RocketLauncher {
    /**
     * The tracker to attach to tracking rockets.
     */
    private Tracker tracker;

    /**
     * Create a new instance of the TrackingRocketLauncher class.
     * @param tracker The tracker to attach to tracking rockets.
     */
    public TrackingRocketLauncher(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    protected void onFire() {
        // TODO Make sound/ add muzzle flash texture.
        System.out.print("Fired Tracking Rocket!");
    }

    /**
     * Generate projectiles for a single fire of this weapon.
     */
    @Override
    protected ArrayList<Projectile> generateProjectiles() {
        final Tracker tracker = this.tracker;
        // A rocket launcher can only shoot a single rocket at a time.
        return new ArrayList<Projectile>() {{
            add(new TrackingRocket(tracker));
        }};
    }

    /**
     * Gets the type of the weapon.
     * @return The type of the weapon.
     */
    @Override
    public WeaponType getType() {
        return WeaponType.TRACKING_ROCKET_LAUNCHER;
    }
}
