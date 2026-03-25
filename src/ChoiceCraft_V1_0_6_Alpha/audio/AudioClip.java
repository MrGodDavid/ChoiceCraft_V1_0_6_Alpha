/**
 * ========================================================================================================================
 * Audio clips of ChoiceCraft's audio.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.ChoiceCraftSettings;

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

    public void update(ChoiceCraftSettings settings) {
        final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(getVolume(settings));
    }

    /**
     * Get the volume of an Audio clip. {}
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the volume of an Audio clip. </p>
     *
     * @param settings ChoiceCraft game settings.
     * @return the volume of an Audio clip.
     */
    protected abstract float getVolume(ChoiceCraftSettings settings);

    public boolean hasFinishedPlaying() {
        return !clip.isRunning();
    }

    public void cleanUp() {
        clip.close();
    }
}
