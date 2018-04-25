package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.dumbpug.ohm.pickup.PickupType;
import java.util.HashMap;

/**
 * Pickup resources.
 */
public class PickupResources {
    /**
     * The map of pickup animations.
     */
    private static HashMap<PickupType, Animation> animations;

    /**
     * Load resources.
     */
    public static void load() {
        animations = new HashMap<PickupType, Animation>();
        animations.put(PickupType.PISTOL, new Animation(new Texture(Gdx.files.internal("graphics/pickups/pistol.png")), 4, 1, 0.5f));
        animations.put(PickupType.SNIPER, new Animation(new Texture(Gdx.files.internal("graphics/pickups/sniper.png")), 4, 1, 0.5f));
        animations.put(PickupType.SHOTGUN, new Animation(new Texture(Gdx.files.internal("graphics/pickups/shotgun.png")), 4, 1, 0.5f));
        animations.put(PickupType.GRENADE, new Animation(new Texture(Gdx.files.internal("graphics/pickups/grenade.png")), 4, 1, 0.5f));
        animations.put(PickupType.UZI, new Animation(new Texture(Gdx.files.internal("graphics/pickups/uzi.png")), 4, 1, 0.5f));
        animations.put(PickupType.ROCKET_LAUNCHER, new Animation(new Texture(Gdx.files.internal("graphics/pickups/rocket_launcher.png")), 4, 1, 0.5f));
        animations.put(PickupType.TRACKING_ROCKET_LAUNCHER, new Animation(new Texture(Gdx.files.internal("graphics/pickups/rocket_launcher.png")), 4, 1, 0.5f));
    }

    /**
     * Gets the animation for the pickup type.
     * @param type The pickup type.
     * @return The animation for the pickup type.
     */
    public static Animation getAnimationFor(PickupType type) {
        return animations.get(type);
    }
}
