/**
 * ========================================================================================================================
 * Option menu of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.menu.ui;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.state.menu.MenuState;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIButton;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UISlider;

/**
 * Option menu of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class UIOptionMenu extends VerticalContainer {

    private UISlider musicVolumeSlider;
    private UIText musicVolumeLabel;

    public UIOptionMenu(Size windowSize) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolumeSlider = new UISlider(0, 1);
        musicVolumeLabel = new UIText("");

        addUIComponent(new UIText("OPTIONS"));
        addUIComponent(musicVolumeSlider);
        addUIComponent(musicVolumeLabel);
        addUIComponent(new UIButton("BACK", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }

    /**
     * Update the UIComponents in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: UIComponent subclasses each behaves the way defined by the implementation of this method.</p>
     *
     * @param state that is the current game ChoiceCraft_V1_0_6_Alpha.state.
     */
    @Override
    public void update(State state) {
        super.update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getGameSettings().getAudioSettings().setMusicVolume((float) musicVolumeSlider.getValue());
        musicVolumeLabel.setText(String.format("VOLUME: %d", Math.round(musicVolumeSlider.getValue() * 100)));
    }
}
