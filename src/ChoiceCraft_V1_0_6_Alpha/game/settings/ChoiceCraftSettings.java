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

    public ChoiceCraftSettings(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
