package com.dumbpug.ohm.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

/**
 * All audio resources.
 */
public class AudioResources {
    /** All cached sounds effects. */
    private static HashMap<SoundEffect, Sound> cachedSoundEffects = new HashMap<SoundEffect, Sound>();
    /** All cached music. */
    private static HashMap<Music, Sound> cachedMusic = new HashMap<Music, Sound>();

    /**
     * All game sound effects.
     */
    public enum SoundEffect {
        BASIC_SHOT,
        LIGHT_SHOT,
        HEAVY_SHOT,
        DRY_SHOT,
        PICKUP,
        SWAP,
        WIN
    }

    /**
     * All game music.
     */
    public enum Music {
        MAIN_THEME
    }

    /**
     * Load all audio files.
     */
    public static void load() {
        // Populate our sound effect map.
        for(SoundEffect soundEffect : SoundEffect.values()) {
            String soundEffectPath = "audio/sounds/" + soundEffect + ".wav";
            cachedSoundEffects.put(soundEffect, Gdx.audio.newSound(Gdx.files.internal(soundEffectPath)));
        }
        // Populate our music map.
        for(Music music : Music.values()) {
            String musicPath = "audio/music/" + music + ".wav";
            cachedMusic.put(music, Gdx.audio.newSound(Gdx.files.internal(musicPath)));
        }
    }

    /**
     * Get sound effect.
     * @return The sound effect.
     */
    public static Sound getSoundEffect(SoundEffect soundEffect) {
        return cachedSoundEffects.get(soundEffect);
    }

    /**
     * Get music track.
     * @return The music track.
     */
    public static Sound getMusic(Music music) {
        return cachedMusic.get(music);
    }
}