package com.dumbpug.ohm.nbp.zone;

import com.dumbpug.ohm.nbp.*;

/**
 * Represents a square zone of force pushing in a specified direction.
 */
public class NBPSquareZone extends NBPZone {

    /** The width/height of the zone. */
    private float width, height;

    /** The direction of the force. */
    private com.dumbpug.ohm.nbp.NBPDirection direction;

    /**
     * Create a new instance of the NBPSquareZone class.
     * @param x
     * @param y
     * @param width
     * @param height
     * @param force
     * @param direction
     */
    public NBPSquareZone(float x, float y, float width, float height, float force, com.dumbpug.ohm.nbp.NBPDirection direction) {
        super(x, y, force);
        this.width     = width;
        this.height    = height;
        this.direction = direction;
    }

    /**
     * Get the width of the zone.
     * @return The width.
     */
    public float getWidth() { return width; }

    /**
     * Set the width of the zone.
     * @param width The width.
     */
    public void setWidth(float width) { this.width = width; }

    /**
     * Get the height of the zone.
     * @return The height.
     */
    public float getHeight() { return height; }

    /**
     * Set the height of the zone.
     * @param height The height.
     */
    public void setHeight(float height) { this.height = height; }

    /**
     * Get the direction of the force to be applied.
     * @return The direction of the force to be applied.
     */
    public com.dumbpug.ohm.nbp.NBPDirection getDirection() { return direction; }

    /**
     * Set the direction of the force to be applied.
     * @param direction The direction of the force to be applied.
     */
    public void setDirection(com.dumbpug.ohm.nbp.NBPDirection direction) { this.direction = direction; }

    @Override
    public boolean intersects(NBPBox box) {
        return NBPMath.doSquaresIntersect(box.getX(), box.getY(), box.getWidth(), box.getHeight(),
                this.getPosition().getX(), this.getPosition().getY(), getWidth(), getHeight());
    }

    @Override
    public void influence(NBPBox box) {
        // Apply the force based on the force direction.
        switch(this.direction) {
            case UP:
                box.applyImpulse(0f, getForce());
                return;
            case DOWN:
                box.applyImpulse(0f, -getForce());
                return;
            case LEFT:
                box.applyImpulse(-getForce(), 0f);
                return;
            case RIGHT:
                box.applyImpulse(getForce(), 0f);
        }
    }
}