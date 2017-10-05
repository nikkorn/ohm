package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Particle Resources.
 */
public class ParticleResources {

    /** The electro particle texture. */
    public static Sprite electro_particle;

    /**
     * Load resources.
     */
    public static void load() {
        electro_particle = new Sprite(new Texture(Gdx.files.internal("graphics/particles/electro_particle.png")));
    }
}