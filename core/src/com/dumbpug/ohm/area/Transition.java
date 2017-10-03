package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.resources.AreaResources;

/**
 * A transition between areas.
 */
public class Transition {

    /** Flag defining whether this transition is complete. */
    private boolean isComplete = false;

    /** The time in millis when this transition was created. */
    private long startTime = System.currentTimeMillis();

    /**
     * Draw the transition.
     * @param batch
     */
    void draw(SpriteBatch batch) {
        // Draw the transition overlay.
        batch.draw(AreaResources.transition_overlay, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Gets whether the transition is complete.
     * @return is complete
     */
    boolean isComplete() {
        return (System.currentTimeMillis() - Constants.AREA_TRANSITION_DURATION_MS) >= startTime;
    }
}
