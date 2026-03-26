/**
 * ========================================================================================================================
 * AudioPlayer in ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.AudioSettings;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Play Audio in ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class AudioPlayer {

    private final AudioSettings audioSettings;
    private final List<AudioClip> audioClips;

    public AudioPlayer(AudioSettings audioSettings) {
        this.audioSettings = audioSettings;
        audioClips = new ArrayList<>();
    }

    /**
     * Update all {@link AudioClip} in the {@code audioClips} list. Remove any audio clip if the clip is finished
     * playing. First use the method {@link AudioClip#cleanUp()} to free the memory that stored the audio clip. Second,
     * remove the audio clip from the {@code audioClips} list.
     * <p>Precondition: none.</p>
     * <p>Postcondition: remove all audio clips from the list if the clip finished playing in ChoiceCraft.</p>
     */
    public void update() {
        Iterator<AudioClip> iterator = audioClips.iterator();
        while (iterator.hasNext()) {
            AudioClip audioClip = iterator.next();
            audioClip.update(audioSettings);
            if (audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                iterator.remove();
            }
        }
    }

    /**
     * Play a specific music clip with the name specified in the {@code fileName}. Note: use the
     * {@link MusicClip#setVolume(AudioSettings)} to set the volume of music clip. Second, add the audio clip into the
     * {@code audioClips} list.
     * <p>Precondition: fileName is not null.</p>
     * <p>Postcondition: set the volume of the clip and add the clip into the {@code audioClips} list.</p>
     *
     * @param fileName that is not null.
     */
    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        final MusicClip musicClip = new MusicClip(clip);
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        musicClip.setVolume(audioSettings);
        audioClips.add(musicClip);
    }

    /**
     * Play a specific music clip with the name specified in the {@code fileName}. Note: use the
     * {@link SoundClip#setVolume(AudioSettings)} to set the volume of music clip. Second, add the audio clip into the
     * {@code audioClips} list.
     * <p>Precondition: fileName is not null.</p>
     * <p>Postcondition: set the volume of the clip and add the clip into the {@code audioClips} list.</p>
     *
     * @param fileName that is not null.
     */
    public void playSound(String fileName) {
        final Clip clip = getClip(fileName);
        SoundClip soundClip = new SoundClip(clip);
        soundClip.setVolume(audioSettings);
        audioClips.add(soundClip);
    }

    private Clip getClip(String fileName) {
        final URL soundFile = AudioPlayer.class.getResource("/sounds/" + fileName);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
            final Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(0);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
