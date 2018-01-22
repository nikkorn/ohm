package com.dumbpug.ohm.nbp;

/**
 * The gravity applied to a physics world.
 */
public class NBPGravity {
    /** The gravitational force to apply. */
    private float force;
    /** The direction of gravity. */
    private NBPDirection direction;
    /** Whether the gravity is enabled. */
    private boolean isEnabled = true;

    /**
     * Create a new instance of the NBPGravity class.
     * @param direction The direction of gravity.
     * @param force The gravitational force to apply.
     */
    public NBPGravity(NBPDirection direction, float force) {
        this.direction = direction;
        this.force     = force;
    }

    /**
     * Get the gravitational force to apply.
     * @return The gravitational force to apply.
     */
    public float getForce() { return this.force; }

    /**
     * Set the gravitational force to apply.
     * @param force The gravitational force to apply.
     */
    public void setForce(float force) { this.force = force; }

    /**
     * Get the direction of gravity.
     * @return The direction of gravity.
     */
    public NBPDirection getDirection() { return this.direction; }

    /**
     * Set the direction of gravity.
     * @param direction The direction of gravity.
     */
    public void setDirection(NBPDirection direction) { this.direction = direction; }

    /**
     * Get whether the gravity is enabled.
     * @return Whether the gravity is enabled.
     */
    public boolean isEnabled() { return this.isEnabled; }

    /**
     * Set whether the gravity is enabled.
     * @param isEnabled Whether the gravity is enabled.
     */
    public void setEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }
}
