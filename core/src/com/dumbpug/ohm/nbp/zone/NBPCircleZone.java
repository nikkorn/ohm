package com.dumbpug.ohm.nbp.zone;

import com.dumbpug.ohm.nbp.NBPBox;

/**
 * Represents a circular zone of force pushing outwards from its origin.
 */
public class NBPCircleZone extends NBPZone {

    /** The radius of the circle zone. */
    private float radius;

    /**
     * Create a new instance of the NBPCircleZone class.
     * @param x
     * @param y
     * @param radius
     */
    public NBPCircleZone(float x, float y, float force, float radius) {
        super(x, y, force);
        this.radius = radius;
    }

    /**
     * Get the radius of this zone.
     * @return The radius of the zone.
     */
    public float getRadius() { return radius; }

    /**
     * Set the radius of this zone.
     * @param radius The radius of the zone.
     */
    public void setRadius(float radius) { this.radius = radius; }

    @Override
    public void influence(NBPBox box) {}
}