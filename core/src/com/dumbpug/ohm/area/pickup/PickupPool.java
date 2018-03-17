package com.dumbpug.ohm.area.pickup;

import com.dumbpug.ohm.nbp.Environment;
import com.dumbpug.ohm.nbp.point.Point;
import com.dumbpug.ohm.player.IngamePlayer;
import com.dumbpug.ohm.powers.Power;
import com.dumbpug.ohm.weapons.Weapon;
import java.util.ArrayList;

/**
 * The pool of pickups within an area.
 */
public class PickupPool {
    /**
     * The active pickups in the pool.
     */
    private ArrayList<Pickup> pickups = new ArrayList<Pickup>();
    /**
     * The physics environment for this area.
     */
    private Environment physicsEnvironment;

    /**
     * Creates a new instance of the PickupPool class.
     * @param physicsEnvironment The physics environment of the area.
     */
    public PickupPool(Environment physicsEnvironment) {
        this.physicsEnvironment = physicsEnvironment;
    }

    /**
     * Get the pickups in this pool.
     * @return The pickups in this pool.
     */
    public ArrayList<Pickup> getPickups() {
        return this.pickups;
    }

    /**
     * Gets the pickups in this pool that are weapons.
     * @return The pickups in this pool that are weapons.
     */
    public ArrayList<Pickup> getWeaponPickups() {
        ArrayList<Pickup> weaponPickups = new ArrayList<Pickup>();
        for (Pickup pickup : this.pickups) {
            if (pickup.getCategory() == PickupCategory.WEAPON) {
                weaponPickups.add(pickup);
            }
        }
        return weaponPickups;
    }

    /**
     * Gets the pickups in this pool that are powers.
     * @return The pickups in this pool that are powers.
     */
    public ArrayList<Pickup> getPowerPickups() {
        ArrayList<Pickup> powerPickups = new ArrayList<Pickup>();
        for (Pickup pickup : this.pickups) {
            if (pickup.getCategory() == PickupCategory.POWER) {
                powerPickups.add(pickup);
            }
        }
        return powerPickups;
    }

    /**
     * Drop a weapon as a pickup.
     * @param position The position to drop the weapon at.
     * @param weapon The weapon to drop as a pickup.
     */
    public void drop(Point position, Weapon weapon) {
        PickupType pickupType;
        // Determine the pickup type based on the weapon type.
        switch (weapon.getType()) {
            case PISTOL:
                pickupType = PickupType.PISTOL;
                break;
            default:
                throw new RuntimeException("Unexpected weapon type: " + weapon.getType());
        }
        // Create the pickup at the specified position.
        Pickup pickup = new Pickup(pickupType, position.getX(), position.getY());
        // Set the ammo capacity for this weapon pickup.
        pickup.setCapacity(weapon.getAmmo());
        // Add the pickup to this pool.
        this.pickups.add(pickup);
    }

    /**
     * Drop a power as a pickup.
     * @param position The position to drop the power at.
     * @param power The power to drop as a pickup.
     */
    public void drop(Point position, Power power) {

    }

    /**
     * Remove a pickup from this pool.
     * @param pickup The pickup to remove.
     */
    public void remove(Pickup pickup) {
        this.pickups.remove(pickup);
    }
}
