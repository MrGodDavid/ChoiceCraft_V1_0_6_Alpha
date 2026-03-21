/**
 * ========================================================================================================================
 * ChoiceCraft game entity action class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.action;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Superclass for all ChoiceCraft game entity actions.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public abstract class Action {

    /**
     * Update ChoiceCraft game object's action.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object's action.</p>
     *
     * @param state  that is not null.
     * @param entity that is not null.
     */
    public abstract void update(State state, MovingEntity entity);

    /**
     * Check if a ChoiceCraft's game object's action is done.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return true if an action is done. False otherwise.</p>
     *
     * @return true if an action is done. False otherwise.
     */
    public abstract boolean isDone();

    /**
     * Get the key of entity's animation name.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the key of entity's animation name.</p>
     *
     * @return the key of entity's animation name.
     */
    public abstract String getAnimationName();
}
