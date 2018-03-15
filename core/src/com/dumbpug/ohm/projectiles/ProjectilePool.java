package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.weapons.Weapon;
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
     * Get the projectiles in this pool.
     * @return The projectiles in this pool.
     */
    public ArrayList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    /**
     * Tick the projectiles pool.
     * @param players The in-game players who can generate projectiles.
     */
    public void tick(ArrayList<IngamePlayer> players) {
        // Check for whether any projectiles have not left their owner players box.
        checkForProjectilesPrimedForOwnerHit();
        // Check whether any players have generated any projectiles and adds them to the pool.
        checkForPlayerGeneratedProjectiles(players);
        // Check for projectile collisions with other entities.
        checkForCollisions();
        // Remove stale projectiles.
        removeStaleProjectiles();
    }

    /**
     * Check for whether any projectiles have not left their owner players box.
     */
    private void checkForProjectilesPrimedForOwnerHit() {
        // TODO Check for any projectiles that cannot hit their owner.
        // TODO If that projectile is NOT intersecting their owners physics box then make it so they can.
    }

    /**
     * Check whether any players have generated any projectiles and adds them to the pool.
     * @param players The in-game players who can generate projectiles.
     */
    private void checkForPlayerGeneratedProjectiles(ArrayList<IngamePlayer> players) {
        // Check each player in turn.
        for (IngamePlayer player : players) {
            // Get the player's current weapon (if any).
            Weapon activeWeapon = player.getStatus().getEquippedWeapon();
            // Do we have an equipped weapon?
            if (activeWeapon != null) {
                // Has this weapon generated any projectiles?
                for (Projectile projectile : activeWeapon.getNewProjectiles()) {
                    // Set the owner of the projectile to be the player who fired it.
                    projectile.setOwner(player.getPlayer());
                    // Apply the rotation/position of the player to the projectile.
                    projectile.fireAt(player.getPlayer().getX(), player.getPlayer().getY(), player.getPlayer().getAngleOfAim());
                    // Add the projectile to the pool.
                    this.projectiles.add(projectile);
                    // Add the projectile physics box to the physics environment.
                    this.physicsEnvironment.addBox(projectile.getPhysicsBox());
                }
            }
        }
    }

    /**
     * Check for projectile collisions.
     */
    private void checkForCollisions() {}

    /**
     * Remove any stale projectiles.
     */
    private void removeStaleProjectiles() {}
}
