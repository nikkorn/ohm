package com.dumbpug.ohm.area;

import com.dumbpug.ohm.Constants;
import java.util.Random;

/**
 * Represents a period of camera shake.
 */
public class CameraShake {
    /**
     * The time that this shake expires.
     */
    private long expires;
    /**
     * The RNG to use in generating the camera translation offsets.
     */
    private Random rng;

    /**
     * Enumeration of camera shake magnitudes.
     */
    public enum Magnitude { SMALL, MEDIUM, LARGE }

    /**
     * Creates a new instance of the CameraShake class.
     * @param magnitude The magnitude of the shake.
     */
    public CameraShake(Magnitude magnitude) {
        this.expires = System.currentTimeMillis() + getShakeDurationForMagnitude(magnitude);
        this.rng     = new Random();
    }

    /**
     * Apply the shake to the camera.
     * @param camera The camera to apply the shake to.
     * @return Whether the shake has expired.
     */
    public boolean apply(AreaCamera camera) {
        // Apply a tiny translation to the camera to simulate a shake.
        camera.translate(getShakeTranslationOffset(), getShakeTranslationOffset());
        // Get whether this shake has expired.
        boolean hasExpired = System.currentTimeMillis() >= this.expires;
        // Reset the area camera to it original position if this shake has expired.
        if (hasExpired) {
            camera.resetPosition();
        }
        // Return whether the camera shake has expired.
        return hasExpired;
    }

    /**
     * Get the duration of a camera shake based on a shake magnitude.
     * @param magnitude The shake magnitude.
     * @return The duration of the shake.
     */
    private long getShakeDurationForMagnitude(Magnitude magnitude) {
        switch(magnitude) {
            case SMALL:
                return Constants.AREA_CAMERA_SMALL_SHAKE_DURATION;
            case MEDIUM:
                return Constants.AREA_CAMERA_MEDIUM_SHAKE_DURATION;
            case LARGE:
                return Constants.AREA_CAMERA_LARGE_SHAKE_DURATION;
            default:
                return Constants.AREA_CAMERA_SMALL_SHAKE_DURATION;
        }
    }

    /**
     * Gets a translation offset to apply to a camera axis to simulate a shake.
     * @return The translation offset to apply to a camera axis to simulate a shake.
     */
    private float getShakeTranslationOffset() {
        return this.rng.nextFloat() - 0.5f;
    }
}
