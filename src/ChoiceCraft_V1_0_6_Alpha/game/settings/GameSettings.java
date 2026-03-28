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
public final class GameSettings {

    private final AudioSettings audioSettings;
    private final RenderSettings renderSettings;

    private boolean debugMode;
    private double gameSpeedMultiplier;

    public GameSettings(boolean debugMode) {
        this.audioSettings = new AudioSettings();
        this.renderSettings = new RenderSettings();
        this.debugMode = debugMode;
        this.gameSpeedMultiplier = 1.0;
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

    public AudioSettings getAudioSettings() {
        return audioSettings;
    }

    public RenderSettings getRenderSettings() {
        return renderSettings;
    }
}
