package com.dumbpug.ohm.area.pickup;

/**
 * Represents a pickup.
 */
public abstract class Pickup {
    /**
     * The physics box for the pickup.
     */
    private PickupPhysicsBox physicsBox;

    /**
     * Create a new instance of the Pickup class.
     * @param x
     * @param y
     */
    public Pickup(float x, float y) {
        // Create the physics box for this pickup.
        physicsBox = new PickupPhysicsBox(x, y);
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
    public abstract PickupType getType();
}
