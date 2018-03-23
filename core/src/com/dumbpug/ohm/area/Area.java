package com.dumbpug.ohm.area;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.pickup.Pickup;
import com.dumbpug.ohm.pickup.PickupCategory;
import com.dumbpug.ohm.pickup.PickupPool;
import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.nbp.point.Point;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.Status;
import com.dumbpug.ohm.projectiles.ProjectilePool;
import com.dumbpug.ohm.weapons.Pistol;
import com.dumbpug.ohm.weapons.WeaponFactory;
import java.util.ArrayList;

/**
 * Represents an area in game.
 */
public class Area {
    /** The physics environment for this area. */
    private AreaEnvironment physicsEnvironment;
    /** The platforms that make up this area. */
    private ArrayList<Platform> platforms;
    /** The pool of projectiles in this area. */
    private ProjectilePool projectilePool;
    /** The pool of pickups in this area. */
    private PickupPool pickupPool;
    /** The in-game players. */
    private ArrayList<IngamePlayer> players;

    /**
     * Create a new instance of the Area class.
     * @param players The players.
     */
    public Area(ArrayList<IngamePlayer> players) {
        // Create the physics world.
        this.physicsEnvironment = new AreaEnvironment();
        // Create the pool of projectiles for this area.
        this.projectilePool = new ProjectilePool(this.physicsEnvironment);
        // Create the pool of pickups for this area.
        this.pickupPool = new PickupPool(this.physicsEnvironment);
        // Create the platforms.
        createPlatforms();
        // Prepare the players that have been added to the area.
        preparePlayers(players);

        // TODO REMOVE!
        this.pickupPool.drop(new Point(70, 50), new Pistol());
    }

    /**
     * Gets the pool of projectiles in this area.
     * @return The pool of projectiles in this area.
     */
    public ProjectilePool getProjectilePool() {
        return projectilePool;
    }

    /**
     * Gets the pool of pickups in this area.
     * @return The pool of pickups in this area.
     */
    public PickupPool getPickupPool() {
        return pickupPool;
    }

    /**
     * Get the in-game players.
     * @return The areas in-game players.
     */
    public ArrayList<IngamePlayer> getIngamePlayers() { return this.players; }

    /**
     * Get the platforms in this area.
     * @return The platforms in this area.
     */
    public ArrayList<Platform> getPlatforms() { return this.platforms; }

    /**
     * Tick the area.
     */
    public void tick() {
        // Update the physics environment.
        physicsEnvironment.update();
        // Update the projectilePool pool.
        projectilePool.tick(this.players);
        // Process fall-outs.
        CheckForFallOuts();
    }

    /**
     * Gets whether the match has finished.
     * This is determined by
     *  - Whether only  a single player remains alive.
     *  - All the players are dead.
     * @return Whether the match has finished.
     */
    public boolean hasMatchFinished() {
        int aliveCount = 0;
        for (IngamePlayer player : this.players) {
            if (!player.getStatus().isDead()) {
                // This player is still alive!
                aliveCount++;
            }
        }
        return aliveCount <= 1;
    }

    /**
     * Create the area platforms.
     */
    private void createPlatforms() {
        this.platforms = new ArrayList<Platform>();
        for (int x = 0; x < Constants.AREA_PLATFORMS_SIZE; x++) {
            for (int y = 0; y < Constants.AREA_PLATFORMS_SIZE; y++) {
                this.platforms.add(new Platform(x, y));
            }
        }
    }

    /**
     * Prepare players for a game in this area.
     * @param players The players to prepare.
     */
    private void preparePlayers(ArrayList<IngamePlayer> players) {
        // Prepare each player.
        for(IngamePlayer ingamePlayer : players) {
            // Add player to this area at the player spawn and
            // Set the player at a free spawn. TODO Add correct positions.
            ingamePlayer.getPlayer().setPosition(players.indexOf(ingamePlayer) * 50, 50);
            // Add the characters physics box to the world.
            physicsEnvironment.addBox(ingamePlayer.getPlayer().getPhysicsBox());
            // Prepare the player with a fresh new status.
            ingamePlayer.setStatus(new Status());

            // TODO Remove! Give the player a pistol!
            ingamePlayer.getStatus().setEquippedWeapon(new Pistol());
        }
        this.players = players;
    }

    /**
     * Check for fall-outs (player, pickup, projectile)
     */
    private void CheckForFallOuts() {
        // TODO Check for whether players have fallen out.
        // TODO Check for whether projectilePool have fallen out.
        // TODO Check for whether pickups have fallen out.
    }

    /**
     * Whether the specified entity should fall out of the area.
     * This will happen when the entity is not over ground (e.g. platform) and is not airborne.
     * @param entity The entity to check.
     * @return Whether the specified entity should fall out of the area.
     */
    private boolean shouldFallOut(IPhysicsEntity entity) {
        // An airborne entity cannot fall out.
        if (entity.isAirborne()) {
            return false;
        }
        // The entity should be on the ground, check to make sure it is on a platform.
        for (Platform platform : platforms) {
            if (NBPMath.doBoxesCollide(platform, entity.getPhysicsBox())) {
                return false;
            }
        }
        // We could not find any piece of ground that the entity is standing on.
        return true;
    }

    /**
     * Handles a player request to pick up a weapon.
     * This can only happen if the player is standing over a weapon pickup.
     * @param ingamePlayer The player requesting to pick up a weapon.
     */
    public void pickUpPlayerWeapon(IngamePlayer ingamePlayer) {
        // Get all pickups in the area that the player is standing over.
        ArrayList<Pickup> closePickups = this.physicsEnvironment.getPickupsTouchingPlayer(ingamePlayer.getPlayer());
        // Pick up the first one we are standing over that is a weapon.
        for (Pickup pickup : closePickups) {
            // We only care about weapon pickups.
            if (pickup.getCategory() == PickupCategory.WEAPON) {
                // Remove this pickup from the pickup pool.
                this.pickupPool.remove(pickup);
                // Give the player the weapon that this pickup represents.
                ingamePlayer.getStatus().setEquippedWeapon(WeaponFactory.createFromPickup(pickup));
                // We only want to pick up one weapon.
                return;
            }
        }
    }

    /**
     * Handles a player requesting to swap or drop a weapon.
     * @param ingamePlayer The player requesting to swap or drop a weapon.
     */
    public void swapOrDropPlayerWeapon(IngamePlayer ingamePlayer) {
        // Get all pickups in the area that the player is standing over.
        ArrayList<Pickup> closePickups = this.physicsEnvironment.getPickupsTouchingPlayer(ingamePlayer.getPlayer());
        // Try to find a weapon pickup that the player is standing over.
        // If there is one then we are dropping the player's current
        // weapon and picking up the one we are standing over.
        Pickup closeWeaponPickup = null;
        // Try to find a weapon pickup we are close enough to grab.
        for (Pickup pickup : closePickups) {
            // We only care about weapon pickups.
            if (pickup.getCategory() == PickupCategory.WEAPON) {
                // We have found a close weapon pickup to swap with our current weapon.
                closeWeaponPickup = pickup;
                // We only want to pick up one weapon.
                break;
            }
        }
        // Just drop the current weapon.
        this.pickupPool.drop(new Point(ingamePlayer.getPlayer().getX(),
                ingamePlayer.getPlayer().getY()), ingamePlayer.getStatus().getEquippedWeapon());
        // Un-equip the current weapon.
        ingamePlayer.getStatus().setEquippedWeapon(null);
        // Whether we are swapping depends on whether we have a weapon to swap with.
        if (closeWeaponPickup != null) {
            // Remove this pickup from the pickup pool.
            this.pickupPool.remove(closeWeaponPickup);
            // Give the player the weapon that this pickup represents.
            ingamePlayer.getStatus().setEquippedWeapon(WeaponFactory.createFromPickup(closeWeaponPickup));
        }
    }
}
