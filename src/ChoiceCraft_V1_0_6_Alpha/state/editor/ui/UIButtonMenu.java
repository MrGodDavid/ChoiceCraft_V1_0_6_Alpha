/**
 * ========================================================================================================================
 * Button menu UI class for editor state of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.editor.ui;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.map.MapIO;
import ChoiceCraft_V1_0_6_Alpha.state.menu.MenuState;
import ChoiceCraft_V1_0_6_Alpha.ui.HorizontalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIButton;

/**
 * Button menu UI class for editor state of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/27/2026
 */
public final class UIButtonMenu extends HorizontalContainer {

    public UIButtonMenu(Size windowSize) {
        super(windowSize);

        addUIComponent(new UIButton("MAIN MENU",
                state -> state.setNextState(new MenuState(state.getCamera().getSize(), state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("SAVE", state -> MapIO.save(state.getGameMap())));
        addUIComponent(new UIButton("LOAD", state -> state.loadGameMap()));
    }
}
