package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.area.pickup.Pickup;
import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.player.Status;
import com.dumbpug.ohm.projectiles.Projectile;
import com.dumbpug.ohm.weapons.Pistol;
import com.dumbpug.ohm.weapons.Weapon;
import java.util.ArrayList;

/**
 * Represents an area in game.
 */
public class Area {
    /** The area camera. */
    private OrthographicCamera camera;
    /** The physics world for this area. */
    private Environment physicsEnvironment;
    /** The platforms that make up this area. */
    private ArrayList<Platform> platforms;
    /** The projectiles in this area. */
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    /** The pickups in this area. */
    private ArrayList<Pickup> pickups = new ArrayList<Pickup>();
    /** The in-game players. */
    private ArrayList<IngamePlayer> players;

    /**
     * Create a new instance of the Area class.
     * @param players The players.
     */
    public Area(ArrayList<IngamePlayer> players) {
        // Create the physics world.
        this.physicsEnvironment = new Environment();
        // Create the platforms.
        createPlatforms();
        // Prepare the players that have been added to the area.
        preparePlayers(players);
        // Create the area camera.
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);
        camera.zoom = Constants.AREA_ZOOM;
        camera.position.set(70, 70, 0);
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
            ingamePlayer.getPlayer().addToPhysicsWorld(this.physicsEnvironment, players.indexOf(ingamePlayer) * 50, 50);
            // Prepare the player with a fresh new status.
            ingamePlayer.setStatus(new Status());
            // TODO REmove! Give the player a pistol!
            ingamePlayer.getStatus().setEquippedWeapon(new Pistol());
        }
        this.players = players;
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
     * Tick the area.
     */
    public void tick() {
        // Update the physics environment.
        physicsEnvironment.update();
        // Determine whether any players have generated any projectiles.
        CheckForGeneratedProjectiles();
        // Process fall-outs.
        CheckForFallOuts();
    }

    /**
     * Check whether any players have generated any projectiles.
     */
    private void CheckForGeneratedProjectiles() {
        // Check each player in turn.
        for (IngamePlayer player : players) {
            // Get the player's current weapon (if any).
            Weapon activeWeapon = player.getStatus().getEquippedWeapon();
            // Do we have an equipped weapon?
            if (activeWeapon != null) {
                // Has this weapon generated any projectiles?
                for (Projectile projectile : activeWeapon.getNewProjectiles()) {
                    // Apply the rotation/position of the player to the projectile.
                    projectile.fireAt(player.getPlayer().getX(), player.getPlayer().getY(), player.getPlayer().getAngleOfAim());
                    // Add the projectile to the area.
                    this.projectiles.add(projectile);
                    // Add the projectile physics box to the physics environment.
                    this.physicsEnvironment.addBox(projectile.getPhysicsBox());
                }
            }
        }
    }

    /**
     * Check for fall-outs (player, pickup, projectile)
     */
    private void CheckForFallOuts() {
        // TODO Check for whether players have fallen out.
        // TODO Check for whether projectiles have fallen out.
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
     * Get the projectiles in this area.
     * @return The projectiles in this area.
     */
    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    /**
     * Get the pickups in this area.
     * @return The pickups in this area.
     */
    public ArrayList<Pickup> getPickups() {
        return pickups;
    }

    /**
     * Get the area camera.
     * @return The area camera.
     */
    public OrthographicCamera getCamera() { return this.camera; }

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
}
