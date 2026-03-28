/**
 * ========================================================================================================================
 * UIRenderSettings of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.editor.ui;

import ChoiceCraft_V1_0_6_Alpha.game.settings.RenderSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UICheckBox;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIMinimap;

/**
 * A subclass of {@link ChoiceCraft_V1_0_6_Alpha.ui.UIContainer}. UIRenderSettings graphics of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/27/2026
 */
public final class UIRenderSettings extends VerticalContainer {

    public UIRenderSettings(Size windowSize, RenderSettings renderSettings, ChoiceCraftMap gameMap) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        setCenterChildren(true);

        addUIComponent(new UIMinimap(gameMap));
        addUIComponent(new UIText("RENDER SETTINGS"));
        addUIComponent(new UICheckBox("GRID", renderSettings.getShouldRenderGrid()));
    }
}
