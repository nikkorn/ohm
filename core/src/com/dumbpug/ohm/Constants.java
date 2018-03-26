package com.dumbpug.ohm;

import com.badlogic.gdx.Gdx;

/**
 * Game constants.
 */
public class Constants {

    /** Area. */
    public static final int BLOCK_SIZE                      = 16;
    public static final int AREA_TILE_HEIGHT                = 8;
    public static final long AREA_TRANSITION_DURATION_MS    = 2000;

    public static final int PLATFORM_SIZE                   = 48;
    public static final int AREA_PLATFORMS_SIZE             = 7;
    public static final float AREA_DEFAULT_CAMERA_ZOOM      = ((AREA_PLATFORMS_SIZE * PLATFORM_SIZE) * 0.8f) / Gdx.graphics.getHeight();

    /** Pickups. */
    public static final int PICKUP_SIZE = 12;

    /** Projectiles. */
    public static final float PROJECTILE_BULLET_SIZE            = 6;
    public static final float PROJECTILE_BULLET_FIRE_VELOCITY   = 2;

    /** Weapons. */
    public static final float PROJECTILE_BASIC_RECOIL           = 1.2f;

    public static final int PROJECTILE_BASIC_BULLET_DAMAGE      = 1;
    public static final int PROJECTILE_SNIPER_BULLET_DAMAGE     = 4;

    public static final long PROJECTILE_PISTOL_COOL_DOWN        = 500l;
    public static final int PROJECTILE_PISTOL_MAX_AMMO          = 6;

    public static final long PROJECTILE_SNIPER_COOL_DOWN        = 1500l;
    public static final int PROJECTILE_SNIPER_MAX_AMMO          = 3;

    public static final long PROJECTILE_SHOTGUN_COOL_DOWN        = 1000l;
    public static final int PROJECTILE_SHOTGUN_MAX_AMMO          = 5;
    public static final int PROJECTILE_SHOTGUN_SPRAY_COUNT       = 8;
    public static final int PROJECTILE_SHOTGUN_SPRAY_ANGLE       = 45;

    /** Player. */
    public static final int PLAYER_SIZE                         = 14;
    public static final int PLAYER_PHYSICS_SIZE_WIDTH           = 8;

    public static final float PLAYER_AIM_TARGET_DISTANCE        = PLAYER_SIZE * 2;
    public static final int PLAYER_LIFE                         = 8;

    /* Character. */
    public static final float CHARACTER_PHYSICS_MAX_WALKING_VELOCITY    = 1.2f;
    public static final float CHARACTER_PHYSICS_WALKING_IMPULSE_VALUE   = 0.4f;
    public static final float CHARACTER_PHYSICS_MAX_VELOCITY            = 1.6f;
    public static final float CHARACTER_PHYSICS_FRICTION                = 0.75f;
    public static final float CHARACTER_PHYSICS_RESTITUTION             = 0f;

    /** Particles. */
    public static final long PARTICLES_DEFAULT_LIFE           = 1000;
    public static final long PARTICLES_DEFAULT_GEN_DELAY      = 50;
    public static final float PARTICLES_ELECTRO_SIZE_SMALL    = 2f;
    public static final float PARTICLES_ELECTRO_SIZE_LARGE    = 4f;
    public static final int PARTICLES_EMITTER_PARTICLE_LIMIT  = 80;

    /** HUD. */
    public static final float HUD_CAMERA_ZOOM                  = 0.5f;
    public static final int HUD_STATUS_BOX_WIDTH               = 50;
    public static final int HUD_STATUS_BOX_HEIGHT              = 27;
}
