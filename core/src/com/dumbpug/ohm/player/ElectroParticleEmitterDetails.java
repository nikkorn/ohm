package com.dumbpug.ohm.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.particles.IEmitterDetails;

/**
 * Emitter details for the players electro particle emitter.
 */
public class ElectroParticleEmitterDetails implements IEmitterDetails {

    /** The player emitting the particles. */
    private Player player;

    /**
     * Create a new instance of the ElectroParticleEmitterDetails class.
     * @param player
     */
    public ElectroParticleEmitterDetails(Player player) { this.player = player; }

    @Override
    public float positionX() { return player.getX() + 6; }

    @Override
    public float positionY() { return player.getY() + 6; }
}
