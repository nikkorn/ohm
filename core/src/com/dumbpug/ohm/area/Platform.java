package com.dumbpug.ohm.area;

/**
 * Represents a platform in an area.
 */
public class Platform {
    private int x, y;
    private boolean isRaised;
    public void startLoweringTimer() {}

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
    }
}