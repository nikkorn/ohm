package com.dumbpug.ohm.input;

import com.dumbpug.ohm.nbp.NBPMath;
import com.dumbpug.ohm.nbp.point.Point;
import java.util.ArrayList;

/**
 * A buffer used to smooth angles of aim.
 */
public class AimBuffer {
    /**
     * The buffer size.
     */
    public static final int BUFFER_SIZE = 15;
    /**
     * The buffer.
     */
    private ArrayList<Point> buffer = new ArrayList<Point>();

    /**
     * Create a new instance of the AimBuffer class.
     */
    public AimBuffer() {
        // Add the initial point to our buffer.
        buffer.add(new Point(0, 0));
    }

    public void update(float x, float y) {
        // Get the latest point.
        Point latest  = this.getLatestPoint();
        float latestX = latest.getX();
        float latestY = latest.getY();

        // Add a new point to the buffer.
        buffer.add(new Point(latestX + x, latestY + y));

        // Do we need to remove the trailing point?
        if (buffer.size() > AimBuffer.BUFFER_SIZE) {
            buffer.remove(this.getTrailingPoint());
        }

        // TODO Move all of our points so that the trailing point is at 0,0 to avoid overflow.
        // moveBufferToOrigin();
    }

    /**
     * Get the smoothed angle of aim.
     * @return The smoothed angle of aim.
     */
    public float getAngleOfAim() {
        return NBPMath.getAngleBetweenPoints(this.getTrailingPoint(), this.getLatestPoint(), true, false);
    }

    /**
     * Get the latest point added.
     * @return The latest point added.
     */
    private Point getLatestPoint() {
        return buffer.get(buffer.size() - 1);
    }

    /**
     * Get the trailing point.
     * @return The trailing point.
     */
    private Point getTrailingPoint() {
        return buffer.get(0);
    }
}
