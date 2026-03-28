/**
 * ========================================================================================================================
 * Editor state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.editor;

import ChoiceCraft_V1_0_6_Alpha.game.settings.GameSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.state.editor.ui.UIButtonMenu;
import ChoiceCraft_V1_0_6_Alpha.state.editor.ui.UIRenderSettings;

/**
 * Editor state. Editor of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/27/2026
 */
public final class EditorState extends State {

    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        this.gameMap = new ChoiceCraftMap(new Size(32, 32), spriteLibrary);

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings()));
    }
}
