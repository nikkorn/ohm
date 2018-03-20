package com.dumbpug.ohm.area;

import com.dumbpug.ohm.pickup.Pickup;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.player.Player;
import java.util.ArrayList;

/**
 * Represents an area physics environment.
 */
public class AreaEnvironment extends Environment {

    /**
     * Gets a list of pickups that are in touching range of a player.
     * @return A list of pickups that are in touching range of a player.
     */
    public ArrayList<Pickup> getPickupsTouchingPlayer(Player player) {
        ArrayList<Pickup> intersectingPickups = new ArrayList<Pickup>();
        // Go over all boxes in this environment, looking for pickup boxes.
        for (Box box : this.getBoxes()) {
            // If the current box is a pickup box and intersects our player box, grab it.
            if (box.getName().equals("PICKUP") && NBPMath.doBoxesCollide(box, player.getPhysicsBox())) {
                // The current box is a pickup box within reach of the specified player.
                intersectingPickups.add((Pickup)box.getUserData());
            }
        }
        return intersectingPickups;
    }
}
