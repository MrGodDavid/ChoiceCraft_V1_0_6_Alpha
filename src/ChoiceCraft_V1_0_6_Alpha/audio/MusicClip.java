/**
 * ========================================================================================================================
 * Music clips of ChoiceCraft's audio.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.AudioSettings;

import javax.sound.sampled.Clip;

/**
 * Music clips of ChoiceCraft's audio.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class MusicClip extends AudioClip{

    public MusicClip(Clip clip) {
        super(clip);
    }

    /**
     * Get the volume of an Audio clip. Wrapper method of {@link ChoiceCraft_V1_0_6_Alpha.game.settings.AudioSettings#getMusicVolume()}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the volume of an Audio clip. </p>
     *
     * @param audioSettings ChoiceCraft game settings.
     * @return the volume of an Audio clip.
     */
    @Override
    protected float getVolume(AudioSettings audioSettings) {
        return audioSettings.getMusicVolume();
    }
}
