/**
 * ========================================================================================================================
 * ChoiceCraft debugger settings.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.settings;

/**
 * ChoiceCraft debugger settings.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public final class ChoiceCraftSettings {

    private boolean debugMode;
    private double gameSpeedMultiplier;
    private float musicVolume;
    private float soundVolume;

    public ChoiceCraftSettings(boolean debugMode) {
        this.debugMode = debugMode;
        this.gameSpeedMultiplier = 1.0;
        musicVolume = 0f;
        soundVolume = 0f;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void toggleDebugMode() {
        debugMode = !debugMode;
    }

    public void increaseGameSpeedMultiplier() {
        gameSpeedMultiplier += 0.25;
    }

    public void decreaseGameSpeedMultiplier() {
        gameSpeedMultiplier -= 0.25;
    }

    public double getGameSpeedMultiplier() {
        return gameSpeedMultiplier;
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
