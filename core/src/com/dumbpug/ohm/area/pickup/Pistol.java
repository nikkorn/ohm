package com.dumbpug.ohm.area.pickup;

/**
 * A pistol weapon pickup.
 */
public class Pistol extends Pickup {

    /**
     * Create a new instance of the Pistol class.
     * @param x
     * @param y
     */
    public Pistol(float x, float y) {
        super(x, y);
    }

    /**
     * Get the type of the pickup.
     * @return The pickup type.
     */
    @Override
    public PickupType getType() { return PickupType.PISTOL; }
}
