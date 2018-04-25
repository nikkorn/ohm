package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.pickup.Pickup;
import com.dumbpug.ohm.pickup.PickupCategory;
import com.dumbpug.ohm.player.Tracker;

/**
 * Factory for creating Weapon instances.
 */
public class WeaponFactory {

    /**
     * Creates a weapon base on a pickup.
     * @param pickup The pickup.
     * @param tracker The player tracker to use for tracking type weapons.
     * @return The created weapon.
     */
    public static Weapon createFromPickup(Pickup pickup, Tracker tracker) {
        // The pickup category type must be the weapon category.
        if (pickup.getCategory() != PickupCategory.WEAPON) {
            throw new RuntimeException("Cannot create weapon based on a pickup that is not a weapon pickup");
        }
        Weapon weapon;
        // The type of weapon we create depends on the pickup type.
        switch (pickup.getType()) {
            case PISTOL:
                weapon = new Pistol();
                break;
            case SNIPER:
                weapon = new Sniper();
                break;
            case SHOTGUN:
                weapon = new Shotgun();
                break;
            case GRENADE:
                weapon = new Grenade();
                break;
            case UZI:
                weapon = new Uzi();
                break;
            case ROCKET_LAUNCHER:
                weapon = new RocketLauncher();
                break;
            case TRACKING_ROCKET_LAUNCHER:
                weapon = new TrackingRocketLauncher(tracker);
                break;
            default:
                throw new RuntimeException("Cannot create weapon based on pickup type: " + pickup.getType());
        }
        // Set the ammo capacity of the new weapon to match the capacity defined by the pickup.
        weapon.setAmmo(pickup.getCapacity());
        // Return the newly created weapon.
        return weapon;
    }

    /**
     * Creates a weapon based on a weapon type.
     * @param type The weapon type.
     * @param tracker The player tracker to use for tracking type weapons.
     * @return The created weapon.
     */
    public static Weapon createFromType(WeaponType type, Tracker tracker) {
        // The type of weapon we create depends on the weapon type.
        switch (type) {
            case PISTOL:
                return new Pistol();
            case SNIPER:
                return new Sniper();
            case SHOTGUN:
                return new Shotgun();
            case GRENADE:
                return new Grenade();
            case UZI:
                return new Uzi();
            case ROCKET_LAUNCHER:
                return new RocketLauncher();
            case TRACKING_ROCKET_LAUNCHER:ROCKET_LAUNCHER:
                return new TrackingRocketLauncher(tracker);
            default:
                throw new RuntimeException("Cannot create weapon based on weapon type: " + type);
        }
    }
}
