/**
 * ========================================================================================================================
 * Sound clips of ChoiceCraft's audio.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.ChoiceCraftSettings;

import javax.sound.sampled.Clip;

/**
 * Sound clips of ChoiceCraft's audio.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class SoundClip extends AudioClip{

    public SoundClip(Clip clip) {
        super(clip);
    }

    /**
     * Get the volume of an Audio clip. Wrapper method of {@link ChoiceCraftSettings#getSoundVolume()} ()}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the volume of an Audio clip. </p>
     *
     * @param settings ChoiceCraft game settings.
     * @return the volume of an Audio clip.
     */
    @Override
    protected float getVolume(ChoiceCraftSettings settings) {
        return settings.getSoundVolume();
    }
}
