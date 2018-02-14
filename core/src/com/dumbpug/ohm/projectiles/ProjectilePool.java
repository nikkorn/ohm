package com.dumbpug.ohm.projectiles;

import java.util.ArrayList;

/**
 * The pool of projectiles within an area.
 */
public class ProjectilePool {
    /**
     * The active projectiles in the pool.
     */
    private ArrayList<Projectile> projectiles;

    /**
     * Tick the projectiles pool.
     */
    public void tick() {
        // TODO Remove stale projectiles.
    }

    /**
     * Get the projectiles in this pool.
     * @return The projectiles in this pool.
     */
    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }
}
