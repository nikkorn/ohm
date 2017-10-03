package com.dumbpug.ohm;

import com.badlogic.gdx.Gdx;

/**
 * Created by nik on 23/09/17.
 */
public class Constants {

    /** Area. */
    public static final int BLOCK_SIZE                   = 16;
    public static final int AREA_TILE_HEIGHT             = 8;
    public static final float AREA_ZOOM                  = (float)(BLOCK_SIZE * AREA_TILE_HEIGHT) / Gdx.graphics.getHeight();
    public static final long AREA_TRANSITION_DURATION_MS = 2000;

    /** Physics. */
    public static final float PHYSICS_GRAVITY      = 0.09f;

    /** Player. */
    public static final int PLAYER_SIZE                 = 14;
    public static final int PLAYER_PHYSICS_SIZE_WIDTH   = 8;
    public static final int PLAYER_PHYSICS_SIZE_HEIGHT  = 10;

    /* Character. */
    public static final float CHARACTER_PHYSICS_MAX_WALKING_VELOCITY    = 1f;
    public static final float CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE   = 0.2f;
    public static final float CHARACTER_JUMPING_IMPULSE                 = 2f;
    public static final float CHARACTER_PHYSICS_MAX_VELOCITY            = 1.6f;
    public static final float CHARACTER_PHYSICS_FRICTION                = 0.75f;
    public static final float CHARACTER_PHYSICS_RESTITUTION             = 0f;
    public static final float CHARACTER_WALL_JUMP_X_OFFSET              = 0.3f;
}
