package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.nbp.point.Point;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.weapons.Weapon;
import java.util.ArrayList;
import java.util.Iterator;

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
        checkForNewProjectiles(players);
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
    private void checkForNewProjectiles(ArrayList<IngamePlayer> players) {
        // Check each player in turn.
        for (IngamePlayer player : players) {
            // Get the player's current weapon (if any).
            Weapon activeWeapon = player.getStatus().getEquippedWeapon();
            // Do we have an equipped weapon?
            if (activeWeapon != null) {
                // Get any newly generated projectiles.
                ArrayList<Projectile> newlyGeneratedProjectiles = activeWeapon.getNewProjectiles();
                // We don't want to do anything if we haven't spawned any new projectiles.
                if (newlyGeneratedProjectiles.isEmpty()) {
                    continue;
                }
                // Get the point from which any projectiles will be spawned.
                // This takes into account the player position, the angle of aim
                // The player weapon offset and the weapon length.
                Point projectileSpawnPoint = getProjectileSpawnPoint(player);
                // Process each of the newly generated projectiles.
                for (Projectile projectile : newlyGeneratedProjectiles) {
                    // Set the owner of the projectile to be the player who fired it.
                    projectile.setOwner(player.getPlayer());
                    // Determine the angle at which this projectile will be fired.
                    // This is the sum of the projectile angle offset and the player angle of aim.
                    float angleOfFire = player.getPlayer().getAngleOfAim() + projectile.getAngleOfFireOffset();
                    // Apply the rotation/position of the player to the projectile.
                    projectile.fireAt(projectileSpawnPoint.getX(), projectileSpawnPoint.getY(), angleOfFire);
                    // Add the projectile to the pool.
                    this.projectiles.add(projectile);
                    // Add the projectile physics box to the physics environment.
                    this.physicsEnvironment.addBox(projectile.getPhysicsBox());
                }
                // If this weapon needs to be un-equipped when empty then do that now if it is empty.
                if (activeWeapon.unEquipWhenEmpty() && activeWeapon.isEmpty()) {
                    // Take this weapon from the player.
                    player.getStatus().setEquippedWeapon(null);
                }
            }
        }
    }

    /**
     * Gets the spawn point of any generated projectiles for a player.
     * @param player The player spawning the projectiles.
     * @return The spawn point of any generated projectiles for a player.
     */
    private Point getProjectileSpawnPoint(IngamePlayer player) {
        // Get the origin point of the player.
        Point playerOrigin = player.getPlayer().getPhysicsBox().getCurrentOriginPoint();
        // Calculate the distance between the player origin and spawn point.
        float spawnOffset = Constants.PLAYER_WEAPON_OFFSET + player.getStatus().getEquippedWeapon().getLength();
        // Calculate the x/y position of the spawn point.
        float angleOfAim  = player.getPlayer().getAngleOfAim();
        float spawnPointX = playerOrigin.getX() + spawnOffset * (float) Math.cos(Math.toRadians(angleOfAim));
        float spawnPointY = playerOrigin.getY() + spawnOffset * (float) Math.sin(Math.toRadians(angleOfAim));
        // Return the spawn point.
        return new Point(spawnPointX, spawnPointY);
    }

    /**
     * Remove any stale projectiles from the pool, and their physics boxes from the environment.
     */
    private void removeStaleProjectiles() {
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            // Get the next projectile from the iterator.
            Projectile projectile = iterator.next();
            // Check whether it is stale.
            if (projectile.isStale()) {
                // Remove the current projectile from the pool.
                iterator.remove();
                // Mark the projectile physics box as 'to be deleted' by the physics engine.
                projectile.getPhysicsBox().markForDeletion();
            }
        }
    }
}
