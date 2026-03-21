/**
 * ========================================================================================================================
 * Greeting from ChoiceCraft game object.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.action;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Greeting from ChoiceCraft game object.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public class Greeting extends Action {

    private int liveSpanInSeconds;

    public Greeting() {
        liveSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
    }
    /**
     * Update ChoiceCraft game object's action.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object's action.</p>
     *
     * @param state  that is not null.
     * @param entity that is not null.
     */
    @Override
    public void update(State state, MovingEntity entity) {
        liveSpanInSeconds--;
    }

    /**
     * Check if a ChoiceCraft's game object's action is done.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return true if an action is done. False otherwise.</p>
     *
     * @return true if an action is done. False otherwise.
     */
    @Override
    public boolean isDone() {
        return liveSpanInSeconds <= 0;
    }

    /**
     * Get the key of entity's animation name.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the key of entity's animation name.</p>
     *
     * @return the key of entity's animation name.
     */
    @Override
    public String getAnimationName() {
        return "enchanter_greeting_8dir_spritesheet";
    }
}
