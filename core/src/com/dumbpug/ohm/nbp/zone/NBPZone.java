package com.dumbpug.ohm.nbp.zone;

import com.dumbpug.ohm.nbp.NBPBox;
import com.dumbpug.ohm.nbp.NBPPoint;

/**
 * Represents an area in a physics world where a continuous force influences overlapping kinematic objects.
 */
public abstract class NBPZone {

    /** The position point of the zone. */
    private NBPPoint position;

    /** The amount of force to apply to intersecting physics boxes. */
    private float force;

    /**
     * Create a new instance of the NBPZone class.
     * @param x
     * @param y
     * @param force
     */
    public NBPZone(float x, float y, float force) {
        this.position = new NBPPoint(x, y);
        this.force    = force;
    }

    /**
     * Get the position of this zone.
     * @return The zone position.
     */
    public NBPPoint getPosition() { return this.position; }

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
     * Determine whether the specified physics box intersects with this zone of force.
     * @param box The physics box to check for intersection.
     * @return Whether the specified physics box intersects with this zone of force.
     */
    public abstract boolean intersects(NBPBox box);

    /**
     * Apply a force to an overlapping physics box.
     * @param box The physics box to influence.
     */
    public abstract void influence(NBPBox box);
}
