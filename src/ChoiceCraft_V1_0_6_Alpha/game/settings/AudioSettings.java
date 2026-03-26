/**
 * ========================================================================================================================
 * ChoiceCraft audio settings.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.settings;

/**
 * ChoiceCraft audio settings.
 *
 * @author David Liu
 * @since 3/24/2026
 */
public final class AudioSettings {

    private float musicVolume;
    private float soundVolume;

    public AudioSettings() {
        musicVolume = 1.0f;
        soundVolume = 1.0f;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
