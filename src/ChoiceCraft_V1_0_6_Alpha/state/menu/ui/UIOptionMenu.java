/**
 * ========================================================================================================================
 * Option menu of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.menu.ui;

import ChoiceCraft_V1_0_6_Alpha.game.settings.GameSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.state.menu.MenuState;
import ChoiceCraft_V1_0_6_Alpha.ui.UIComponent;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIButton;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UISlider;

import java.awt.*;

/**
 * Option menu of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public final class UIOptionMenu extends VerticalContainer {

    private final UISlider musicVolumeSlider;
    private final UIText musicVolumeLabel;

    private final UISlider soundVolumeSlider;
    private final UIText soundVolumeLabel;

    public UIOptionMenu(Size windowSize, GameSettings gameSettings) {
        super(windowSize);
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);

        musicVolumeSlider = new UISlider(0, 1);
        musicVolumeSlider.setValue(gameSettings.getAudioSettings().getMusicVolume());
        musicVolumeLabel = new UIText("");

        soundVolumeSlider = new UISlider(0, 1);
        soundVolumeSlider.setValue(gameSettings.getAudioSettings().getSoundVolume());
        soundVolumeLabel = new UIText("");

        UIContainer labelContainer = new VerticalContainer(windowSize);
        labelContainer.setMargin(UIComponent.ZERO_MARGIN);
        labelContainer.setBackground(Color.DARK_GRAY);
        labelContainer.addUIComponent(new UIText("OPTIONS"));

        UIContainer contentContainer = new VerticalContainer(windowSize);
        contentContainer.setMargin(UIComponent.ZERO_MARGIN);
        contentContainer.setPadding(new Spacing(10));
        contentContainer.setBackground(Color.DARK_GRAY);
        contentContainer.addUIComponent(musicVolumeLabel);
        contentContainer.addUIComponent(musicVolumeSlider);
        contentContainer.addUIComponent(soundVolumeLabel);
        contentContainer.addUIComponent(soundVolumeSlider);
        contentContainer.addUIComponent(new UIButton("BACK",
                (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));

        addUIComponent(labelContainer);
        addUIComponent(contentContainer);
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
        musicVolumeLabel.setText(String.format("MUSIC VOL: %d", Math.round(musicVolumeSlider.getValue() * 100)));

        state.getGameSettings().getAudioSettings().setSoundVolume((float) soundVolumeSlider.getValue());
        soundVolumeLabel.setText(String.format("SOUND VOL: %d", Math.round(soundVolumeSlider.getValue() * 100)));
    }
}
