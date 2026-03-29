/**
 * ========================================================================================================================
 * Abstract mouse action class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input.mouse.action;

import ChoiceCraft_V1_0_6_Alpha.input.mouse.MouseConsumer;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.UIImage;

/**
 * Abstract mouse action class.
 *
 * @author David Liu
 * @since 3/28/2026
 */
public abstract class MouseAction implements MouseConsumer {

    /**
     * Update ChoiceCraft mouse action based on current ChoiceCraft state.
     * <p>Precondition: current state is not null.</p>
     * <p>Postcondition: update ChoiceCraft mouse action based on current game state.</p>
     *
     * @param state current ChoiceCraft game state that is not null.
     */
    public abstract void update(State state);

    public abstract UIImage getSprite();
}
