package com.dumbpug.ohm.nbp.zone;

import com.dumbpug.ohm.nbp.NBPBox;

/**
 * Represents an area in a physics world where a continuous force influences overlapping kinematic objects.
 */
public abstract class NBPZone {

    /** The x/y position of the zone. */
    private float x, y;

    /** The amount of force to apply to intersecting physics boxes. */
    private float force;

    /**
     * Create a new instance of the NBPZone class.
     * @param x
     * @param y
     * @param force
     */
    public NBPZone(float x, float y, float force) {
        this.x     = x;
        this.y     = y;
        this.force = force;
    }

    /**
     * Get the x position of this zone.
     * @return x position.
     */
    public float getX() { return this.x; }

    /**
     * Set the x position of this zone.
     * @param x The x position
     */
    public void setX(float x) { this.x = x; }

    /**
     * Get the y position of this zone.
     * @return y position.
     */
    public float getY() { return this.y; }

    /**
     * Set the y position of this zone.
     * @param y The y position
     */
    public void setY(float y) { this.y = y; }

    /**
     * Get the force that is applied to intersecting physics boxes.
     * @return force.
     */
    public float getForce() { return force; }

    /**
     * Set the force that is applied to intersecting physics boxes.
     * @param force
     */
    public void setForce(float force) { this.force = force; }

    /**
     * Apply a force to an overlapping physics box.
     * @param box The physics box to influence.
     */
    public abstract void influence(NBPBox box);
}
