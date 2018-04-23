package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Particle Resources.
 */
public class ParticleResources {

    /** The rocket smoke particle texture. */
    public static Sprite rocket_smoke_particle;

    /**
     * Load resources.
     */
    public static void load() {
        rocket_smoke_particle = new Sprite(new Texture(Gdx.files.internal("graphics/particles/rocket_smoke_particle.png")));
    }
}