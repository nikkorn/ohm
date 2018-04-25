package com.dumbpug.ohm.pickup;

import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;

/**
 * Represents a pickup.
 */
public class Pickup implements IPhysicsEntity {
    /**
     * The Type of the pickup.
     */
    private PickupType type;
    /**
     * The physics box for the pickup.
     */
    private PickupPhysicsBox physicsBox;
    /**
     * The capacity of the pickup. (e.g. Ammo)
     */
    private int capacity = 0;

    /**
     * Create a new instance of the Pickup class.
     * @param type The type of the pickup.
     * @param x
     * @param y
     */
    public Pickup(PickupType type, float x, float y) {
        // Create the physics box for this pickup.
        physicsBox = new PickupPhysicsBox(x, y);
        // Set the user data of the physics box to be the pickup.
        physicsBox.setUserData(this);
        // Set the type of the pickup.
        this.type = type;
    }

    /**
     * Get the capacity of this pickup.
     * @return The capacity of this pickup.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of this pickup.
     * @param capacity The capacity of this pickup.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get the type of the pickup.
     * @return The pickup type.
     */
    public PickupType getType() {
        return this.type;
    }

    /**
     * Gets the category of this pickup.
     * @return The category of this pickup.
     */
    public PickupCategory getCategory() {
        // The category of this pickup depends on its type.
        switch (this.type) {
            case PISTOL:
            case SHOTGUN:
            case GRENADE:
            case UZI:
            case SNIPER:
            case ROCKET_LAUNCHER:
            case REMOTE_ROCKET_LAUNCHER:
            case TRACKING_ROCKET_LAUNCHER:
            case AUTO_TURRET:
                return PickupCategory.WEAPON;
            case SHIELD:
            case CHARGE:
            case BUILDER:
            case REFLECT:
                return PickupCategory.POWER;
            case AMMO:
                return PickupCategory.AMMO;
            default:
                throw new RuntimeException("Unknown weapon type: " + this.type);
        }
    }

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    @Override
    public Box getPhysicsBox() { return this.physicsBox; }

    /**
     * Whether the entity is airborne.
     * @return Whether the entity is airborne.
     */
    @Override
    public boolean isAirborne() { return false; }
}
