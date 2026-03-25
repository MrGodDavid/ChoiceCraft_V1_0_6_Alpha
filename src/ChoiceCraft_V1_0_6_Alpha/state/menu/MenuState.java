/**
 * ========================================================================================================================
 * Menu state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.menu;

import ChoiceCraft_V1_0_6_Alpha.game.settings.GameSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.state.menu.ui.UIMainMenu;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;

/**
 * Menu state in ChoiceCraft. This state indicates displaying menu graphics of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class MenuState extends State {

    public MenuState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        this.gameMap = new ChoiceCraftMap(new Size(20, 20), spriteLibrary);

        uiContainers.add(new UIMainMenu(windowSize));
        audioPlayer.playMusic("ChoiceCraft_MainTheme.wav");
    }

    public void enterMenu(UIContainer container) {
        uiContainers.clear();
        uiContainers.add(container);
    }
}
