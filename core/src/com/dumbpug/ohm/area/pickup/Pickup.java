package com.dumbpug.ohm.area.pickup;

/**
 * Represents a pickup.
 */
public class Pickup {
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
     * Get the physics box for this pickup.
     * @return The physics box for this pickup.
     */
    public PickupPhysicsBox getPhysicsBox() {
        return physicsBox;
    }

    /**
     * Get the type of the pickup.
     * @return The pickup type.
     */
    public PickupType getType() {
        return this.type;
    }
}
