package com.dumbpug.ohm.player;

import com.dumbpug.ohm.nbp.point.Point;
import java.util.ArrayList;

/**
 * Used to track enemies.
 */
public class Tracker {
    /**
     * The players.
     */
    private ArrayList<IngamePlayer> players;
    /**
     * The owner.
     */
    private IngamePlayer owner;

    /**
     * Create a new instance of the Tracker class.
     * @param owner The owner.
     * @param players The players to track.
     */
    public Tracker(IngamePlayer owner, ArrayList<IngamePlayer> players) {
        this.owner   = owner;
        this.players = players;
    }

    /**
     * Gets the angle between the origin and the closest enemy player.
     * @return The angle between the origin and the closest enemy player.
     */
    public float getAngleToClosestEnemy(Point origin) {
        return 45f;
    }

    /**
     * Gets the angle between the player and the closest enemy player.
     * @return The angle between the player and the closest enemy player.
     */
    public float getAngleToClosestEnemy() {
        return getAngleToClosestEnemy(owner.getPlayer().getPhysicsBox().getCurrentOriginPoint());
    }
}
