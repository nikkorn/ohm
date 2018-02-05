package com.dumbpug.ohm;

import com.badlogic.gdx.Gdx;

/**
 * Game constants.
 */
public class Constants {

    /** Area. */
    public static final int BLOCK_SIZE                   = 16;
    public static final int AREA_TILE_HEIGHT             = 8;
    public static final long AREA_TRANSITION_DURATION_MS = 2000;

    public static final int PLATFORM_SIZE                = 32;
    public static final int AREA_PLATFORMS_SIZE          = 7;
    public static final float AREA_ZOOM                  = (float)(PLATFORM_SIZE * AREA_PLATFORMS_SIZE) / Gdx.graphics.getHeight();

    /** Physics. */
    public static final float PHYSICS_GRAVITY = 0.09f;

    /** Player. */
    public static final int PLAYER_SIZE                         = 14;
    public static final int PLAYER_PHYSICS_SIZE_WIDTH           = 8;
    public static final int PLAYER_PHYSICS_SIZE_HEIGHT          = 10;
    public static final int PLAYER_ELECTRO_CHARGE_POINTS        = 10;
    public static final int PLAYER_ELECTRO_UPDATES_PER_POINT    = 50;
    public static final float PLAYER_ELECTRO_WALKING_MODIFIER   = 2f;

    /* Character. */
    public static final float CHARACTER_PHYSICS_MAX_WALKING_VELOCITY    = 1f;
    public static final float CHARACTER_PHYSICS_MAX_WALKING_SLOWDOWN    = 0.2f;
    public static final float CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE   = 0.2f;
    public static final float CHARACTER_JUMPING_IMPULSE                 = 2f;
    public static final float CHARACTER_PHYSICS_MAX_VELOCITY            = 1.6f;
    public static final float CHARACTER_PHYSICS_FRICTION                = 0.75f;
    public static final float CHARACTER_PHYSICS_RESTITUTION             = 0f;
    public static final float CHARACTER_WALL_JUMP_X_OFFSET              = 0.3f;

    /* Enemy. */
    public static final float ENEMY_HORIZONTAL_SENSOR_WIDTH    = 6f;

    /** Particles. */
    public static final long PARTICLES_DEFAULT_LIFE           = 1000;
    public static final long PARTICLES_DEFAULT_GEN_DELAY      = 50;
    public static final float PARTICLES_ELECTRO_SIZE_SMALL    = 2f;
    public static final float PARTICLES_ELECTRO_SIZE_LARGE    = 4f;
    public static final int PARTICLES_EMITTER_PARTICLE_LIMIT  = 80;

    /** Wires. */
    public static final float WIRE_SEGMENT_SIZE               = BLOCK_SIZE / 2f;
    public static final float WIRE_MOVEMENT_STEP              = WIRE_SEGMENT_SIZE / 4f;

    /** Signalling. */
    public static final int SIGNALLING_BEACON_HEIGHT          = 7;
    public static final int SIGNALLING_BEACON_WIDTH           = 9;
    public static final int SIGNALLING_PANEL_SIZE             = 8;
    public static final long SIGNALLING_MOVEMENT_UPDATE_MS    = 1000;
    public static final long SIGNALLING_CREATION_UPDATE_MS    = 5000;
}
