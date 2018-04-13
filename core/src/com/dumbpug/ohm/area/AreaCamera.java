package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.dumbpug.ohm.Constants;

/**
 * A camera used to render a game area.
 */
public class AreaCamera extends OrthographicCamera {
    /**
     * The current camera shake.
     */
    private CameraShake shake;
    /**
     * The initial position.
     */
    private Vector3 initialPosition;

    /**
     * Creates a new instance of the AreaCamera class.
     */
    public AreaCamera() {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // Set whether our Y axis is flipped.
        this.setToOrtho(false);
        // Zoom in the camera to look at all the pixel-y goodness.
        this.zoom = Constants.AREA_DEFAULT_CAMERA_ZOOM;
        // Get the area size.
        float areaSize = Constants.AREA_PLATFORMS_SIZE * Constants.PLATFORM_SIZE;
        // Move the camera to the middle of the area.
        float translateY = (Gdx.graphics.getHeight() / 2f) - (areaSize / 2f);
        float translateX = (Gdx.graphics.getWidth() / 2f) - (areaSize / 2f);
        this.translate(-translateX, -translateY, 0);
        // Get the initial position.
        this.initialPosition = this.position;
    }

    /**
     * Apply a camera shake of the specified magnitude.
     * @param magnitude The camera shake magnitude.
     */
    public void shake(CameraShake.Magnitude magnitude) {
        this.shake = new CameraShake(magnitude);
    }

    /**
     * Apply the default translation for the area camera.
     */
    public void resetPosition() {
        this.position.set(this.initialPosition.x, this.initialPosition.y, 0);
    }

    /**
     * Update the camera, applying any custom behaviour.
     */
    @Override
    public void update() {
        // Apply camera shake if we have an active one.
        if (this.shake != null) {
            // Shake the camera, getting whether it has expired.
            boolean shakeHasExpired = this.shake.apply(this);
            // Remove the camera shake if it has expired.
            if (shakeHasExpired) {
                this.shake = null;
            }
        }
        // Call default update logic.
        super.update();
    }
}
