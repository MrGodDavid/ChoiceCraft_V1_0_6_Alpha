/**
 * ========================================================================================================================
 * Render settings.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.settings;

/**
 * Render settings. Should render grid, etc.
 *
 * @author David Liu.
 * @since 3/27/2026
 */
public final class RenderSettings {

    private final Setting<Boolean> shouldRenderGrid;

    public RenderSettings() {
        shouldRenderGrid = new Setting<>(false);
    }

    public Setting<Boolean> getShouldRenderGrid() {
        return shouldRenderGrid;
    }
}
