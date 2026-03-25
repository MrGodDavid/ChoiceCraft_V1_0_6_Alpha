/**
 * ========================================================================================================================
 * AudioPlayer in ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.audio;

import ChoiceCraft_V1_0_6_Alpha.game.settings.ChoiceCraftSettings;

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

    private List<AudioClip> audioClips;

    public AudioPlayer() {
        audioClips = new ArrayList<>();
    }

    public void update(ChoiceCraftSettings settings) {
        Iterator<AudioClip> iterator = audioClips.iterator();
        while (iterator.hasNext()) {
            AudioClip audioClip = iterator.next();
            audioClip.update(settings);
            if (audioClip.hasFinishedPlaying()) {
                audioClip.cleanUp();
                iterator.remove();
            }
        }
    }

    public void playMusic(String fileName) {
        final Clip clip = getClip(fileName);
        audioClips.add(new MusicClip(clip));
    }

    public void playSound(String fileName) {
        final Clip clip = getClip(fileName);
        audioClips.add(new SoundClip(clip));
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
