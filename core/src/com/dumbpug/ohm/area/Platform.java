package com.dumbpug.ohm.area;

/**
 * Represents a platform in an area.
 */
public class Platform {
    private int x, y;
    private boolean isRaised = true;
    public void startLoweringTimer() {}

    public Platform(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRaised() {
        return isRaised;
    }

    public void setRaised(boolean raised) {
        isRaised = raised;
    }
}
