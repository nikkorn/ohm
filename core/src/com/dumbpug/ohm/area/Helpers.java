package com.dumbpug.ohm.area;

import com.dumbpug.ohm.nbp.Box;

/**
 * Helper functions.
 */
public class Helpers {

    /**
     * Get whether the specified physics bix is currently idle (barely moving)
     * @return is idle.
     */
    public static boolean isBoxIdle(Box box) {
        return (box.getVelX() < 0.2f && box.getVelX() > -0.2f) && (box.getVelY() < 0.2f && box.getVelY() > -0.2f);
    }
}
