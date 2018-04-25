package com.dumbpug.ohm.player;

import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.nbp.point.Point;
import java.util.ArrayList;

/**
 * Used to track players.
 */
public class Tracker {
    /**
     * The players to track.
     */
    private ArrayList<IngamePlayer> players;
    /**
     * The tracker owner.
     */
    private IngamePlayer owner;

    /**
     * Create a new instance of the Tracker class.
     * @param owner The tracker owner.
     * @param players The players to track.
     */
    public Tracker(IngamePlayer owner, ArrayList<IngamePlayer> players) {
        this.owner   = owner;
        this.players = players;
    }

    /**
     * Gets the angle between the origin and the closest enemy player.
     * @param origin The origin at which the angle starts.
     * @return The angle between the origin and the closest enemy player.
     */
    public float getAngleToClosestEnemy(Point origin) {
        IngamePlayer closestPlayer = null;
        // Firstly, find the closest player to the specified point that is not the owner.
        for (IngamePlayer player : this.players) {
            // Do nothing if the current player is also the tracker owner.
            if (player == this.owner) {
                continue;
            }
            // If this is the first player we are checking then they are closest so far.
            if (closestPlayer == null) {
                closestPlayer = player;
            } else {
                // Get the distance to the closest player.
                float closestPlayerDistance = NBPMath.getDistanceBetweenPoints(origin, closestPlayer.getPlayer().getPhysicsBox().getCurrentOriginPoint());
                // Get the distance to the current player.
                float currentPlayerDistance = NBPMath.getDistanceBetweenPoints(origin, player.getPlayer().getPhysicsBox().getCurrentOriginPoint());
                // Is the current player closer?
                if (currentPlayerDistance < closestPlayerDistance) {
                    // The current player is actually the closest.
                    closestPlayer = player;
                }
            }
        }
        // If we have no closest player then just return 0.
        if (closestPlayer == null) {
            return 0f;
        }
        // Now that we know who the closest player is, return the angle between the specified point and them.
        return NBPMath.getAngleBetweenPoints(origin, closestPlayer.getPlayer().getPhysicsBox().getCurrentOriginPoint());
    }

    /**
     * Gets the angle between the player and the closest enemy player.
     * @return The angle between the player and the closest enemy player.
     */
    public float getAngleToClosestEnemy() {
        return getAngleToClosestEnemy(owner.getPlayer().getPhysicsBox().getCurrentOriginPoint());
    }
}
