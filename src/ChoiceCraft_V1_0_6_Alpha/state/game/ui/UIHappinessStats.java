/**
 * ========================================================================================================================
 * Statistics for NPC Happiness.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.game.ui;

import ChoiceCraft_V1_0_6_Alpha.state.game.GameState;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.ui.HorizontalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

/**
 * Statistics UI for displaying NPC Happiness texts.
 *
 * @author David Liu.
 * @since 3/21/2026
 */
public final class UIHappinessStats extends HorizontalContainer {

    private UIText numberOfHappy;
    private UIText numberOfNonchalant;

    public UIHappinessStats(Size windowSize) {
        super(windowSize);
        this.numberOfHappy = new UIText(" ");
        this.numberOfNonchalant = new UIText(" ");

        UIContainer happinessContainer = new VerticalContainer(windowSize);
        happinessContainer.setPadding(new Spacing(0));
        happinessContainer.addUIComponent(new UIText("HAPPY"));
        happinessContainer.addUIComponent(numberOfHappy);

        UIContainer nonchalantContainer = new VerticalContainer(windowSize);
        nonchalantContainer.setPadding(new Spacing(0));
        nonchalantContainer.addUIComponent(new UIText("NONCHALANT"));
        nonchalantContainer.addUIComponent(numberOfNonchalant);

        super.addUIComponent(happinessContainer);
        super.addUIComponent(nonchalantContainer);
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
        if (state instanceof GameState gameState) {
            numberOfHappy.setText(String.format("%d [%d]", gameState.getNumberOfHappy(), gameState.getNumberOfScared()));
            numberOfNonchalant.setText(String.valueOf(gameState.getNumberOfNonchalant()));
        }
    }
}
