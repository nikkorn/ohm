package com.dumbpug.ohm.area;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.nbp.Bloom;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.BoxType;
import com.dumbpug.ohm.nbp.Sensor;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;

/**
 * Represents a platform in an area.
 */
public class Platform extends Box {
    /**
     * The physics box which represents the size/location of this platform.
     */
    private Box box;
    /**
     * Whether the platform is raised.
     */
    private boolean isRaised = true;
    /**
     * The platform position within a grid of platforms.
     */
    private int platformPositionX, platformPositionY;

    /**
     * Creates a new instance if the Platform class.
     * @param x
     * @param y
     */
    public Platform(int x, int y) {
        super(x * Constants.PLATFORM_SIZE, y * Constants.PLATFORM_SIZE,
                Constants.PLATFORM_SIZE, Constants.PLATFORM_SIZE, BoxType.STATIC);
        this.platformPositionX = x;
        this.platformPositionY = y;
    }

    /**
     * Get the X platform (not world) position of this platform.
     * @return The X platform (not world) position of this platform.
     */
    public int getPlatformPositionX() {
        return platformPositionX;
    }

    /**
     * Get the Y platform (not world) position of this platform.
     * @return The Y platform (not world) position of this platform.
     */
    public int getPlatformPositionY() {
        return platformPositionY;
    }

    /**
     * Gets whether the platform is raised.
     * @return Whether the platform is raised.
     */
    public boolean isRaised() {
        return isRaised;
    }

    /**
     * Sets whether the platform is raised.
     * @param raised Whether the platform is raised.
     */
    public void setRaised(boolean raised) {
        isRaised = raised;
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {
    }

    @Override
    protected void onCollisonWithStaticBox(Box collidingBox, IntersectionPoint originAtCollision) {
    }

    @Override
    protected void onSensorEntry(Sensor sensor, Box enteredBox) {
    }

    @Override
    protected void onSensorExit(Sensor sensor, Box exitedBox) {
    }

    @Override
    protected boolean onBloomPush(Bloom bloom, float angleOfForce, float force, float distance) {
        return false;
    }

    @Override
    protected void onBeforeUpdate() {
    }

    @Override
    protected void onAfterUpdate() {
    }

    @Override
    protected void onDeletion() {
    }
}
