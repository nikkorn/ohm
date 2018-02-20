package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.nbp.Environment;
import java.util.ArrayList;

/**
 * The pool of projectiles within an area.
 */
public class ProjectilePool {
    /**
     * The active projectiles in the pool.
     */
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    /**
     * The physics world for this area.
     */
    private Environment physicsEnvironment;

    /**
     * Creates a new instance of the ProjectilePool class.
     * @param physicsEnvironment The physics environment of the area.
     */
    public ProjectilePool(Environment physicsEnvironment) {
        this.physicsEnvironment = physicsEnvironment;
    }

    /**
     * Tick the projectiles pool.
     */
    public void tick() {
        // Check for projectile collisions with other entities.
        checkForCollisions();
        // Remove stale projectiles.
        removeStaleProjectiles();
    }

    /**
     * Add a projectile to this pool.
     * @param projectile The projectile to add.
     */
    public void add(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    /**
     * Get the projectiles in this pool.
     * @return The projectiles in this pool.
     */
    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    /**
     *
     */
    private void checkForCollisions() {

    }

    private void removeStaleProjectiles() {
    }
}
