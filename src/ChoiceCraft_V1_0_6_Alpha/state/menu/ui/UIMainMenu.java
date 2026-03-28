/**
 * ========================================================================================================================
 * Main menu of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.menu.ui;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.state.editor.EditorState;
import ChoiceCraft_V1_0_6_Alpha.state.game.GameState;
import ChoiceCraft_V1_0_6_Alpha.state.menu.MenuState;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIButton;

import java.awt.*;

/**
 * Main menu of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class UIMainMenu extends VerticalContainer {

    public UIMainMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
        setBackground(Color.DARK_GRAY);

        addUIComponent(new UIText("ChoiceCraft V1.0.6a"));
        addUIComponent(new UIButton("PLAY", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("OPTIONS", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize, state.getGameSettings()))));
        addUIComponent(new UIButton("EDITOR", (state) -> state.setNextState(new EditorState(windowSize, state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("EXIT", (state) -> System.exit(0)));
    }
}
