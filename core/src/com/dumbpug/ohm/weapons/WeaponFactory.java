package com.dumbpug.ohm.weapons;

import com.dumbpug.ohm.area.pickup.Pickup;
import com.dumbpug.ohm.area.pickup.PickupCategory;

/**
 * Factory for creating Weapon instances.
 */
public class WeaponFactory {

    /**
     * Creates a weapon base on a pickup.
     * @param pickup The pickup.
     * @return The created weapon.
     */
    public static Weapon createFromPickup(Pickup pickup) {
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
     * @return The created weapon.
     */
    public static Weapon createFromType(WeaponType type) {
        // The type of weapon we create depends on the weapon type.
        switch (type) {
            case PISTOL:
                return new Pistol();
            default:
                throw new RuntimeException("Cannot create weapon based on weapon type: " + type);
        }
    }
}
