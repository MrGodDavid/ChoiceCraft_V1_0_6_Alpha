/**
 * ========================================================================================================================
 * Audio clips of ChoiceCraft's audio.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.AudioSettings;
import ChoiceCraft_V1_0_6_Alpha.game.settings.GameSettings;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Audio clips of ChoiceCraft's audio.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public abstract class AudioClip {

    private final Clip clip;

    public AudioClip(Clip clip) {
        this.clip = clip;
        clip.start();
    }

    public void update(AudioSettings audioSettings) {
        setVolume(audioSettings);
    }

    public void setVolume(AudioSettings audioSettings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = control.getMaximum() - control.getMinimum();
        float gain = (range * getVolume(audioSettings)) + control.getMinimum();
        control.setValue(gain);
    }

    /**
     * Get the volume of an Audio clip. {}
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the volume of an Audio clip. </p>
     *
     * @param audioSettings ChoiceCraft game settings.
     * @return the volume of an Audio clip.
     */
    protected abstract float getVolume(AudioSettings audioSettings);

    public boolean hasFinishedPlaying() {
        return !clip.isRunning();
    }

    public void cleanUp() {
        clip.close();
    }
}
