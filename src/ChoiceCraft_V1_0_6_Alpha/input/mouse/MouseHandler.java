/**
 * ========================================================================================================================
 * Process user's mouse input.
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input.mouse;

import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.input.mouse.action.MouseAction;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.UIImage;

import java.util.Optional;

/**
 * Handle user's mouse inputs.
 *
 * @author David Liu
 * @since 3/28/2026
 */
public final class MouseHandler {

    private MouseAction primaryButtonAction;
    private MouseConsumer activeConsumer;

    /**
     * Update ChoiceCraft mouse handler based on current ChoiceCraft state.
     * <p>Precondition: current state is not null.</p>
     * <p>Postcondition: update ChoiceCraft mouse handler based on current game state.</p>
     *
     * @param state current ChoiceCraft game state that is not null.
     */
    public void update(State state) {
        final Input input = state.getInput();

        handlePrimaryButton(state);
        handleActiveConsumer(state, input);

        cleanUp(input);
    }

    private void handlePrimaryButton(State state) {
        if (primaryButtonAction != null) {
            setActiveConsumer(primaryButtonAction);
            primaryButtonAction.update(state);
        }
    }

    private void cleanUp(Input input) {
        if (!input.isMousePressed()) {
            activeConsumer = null;
        }
        input.clearMouseClick();
    }

    private void handleActiveConsumer(State state, Input input) {
        if (activeConsumer != null) {
            if (input.isMouseClicked()) {
                activeConsumer.onClick(state);
            } else if (input.isMousePressed()) {
                activeConsumer.onDrag(state);
            }
        }
    }

    public MouseConsumer getActiveConsumer() {
        return activeConsumer;
    }

    public void setActiveConsumer(MouseConsumer mouseConsumer) {
        if (activeConsumer == null) {
            activeConsumer = mouseConsumer;
        }
    }

    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
    }

    public Optional<UIImage> getPrimaryButtonUI() {
        if (primaryButtonAction != null) {
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }
        return Optional.empty();
    }
}
