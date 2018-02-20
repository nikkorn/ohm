package com.dumbpug.ohm.projectiles.contactbloom;

import com.dumbpug.ohm.nbp.Bloom;

/**
 * The collision bloom to add to a physics environment on a rocket collision.
 */
public class RocketCollisionBloom extends Bloom {

    /**
     * Create a new instance of the RocketCollisionBloom class.
     * @param x      The X position of the bloom.
     * @param y      The Y position of the bloom.
     * @param radius The radius of the bloom.
     * @param force  The force of the bloom.
     */
    public RocketCollisionBloom(float x, float y, float radius, float force) {
        super(x, y, radius, force);
    }
}
