/**
 * ========================================================================================================================
 * Transition between different AI behaviors of game object of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Transition between different AI behaviors of game object of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class AITransition {

    private String nextState;
    private final AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    /**
     * Wrapper method of {@link AICondition#isMet(State, MovingEntity)}.
     * <p>Precondition: ChoiceCraft_V1_0_6_Alpha.state and currentCharacter are not null.</p>
     * <p>Postcondition: return a boolean that indicates whether the game object should transit to other AIState.</p>
     *
     * @param state            current game ChoiceCraft_V1_0_6_Alpha.state that is not null.
     * @param currentCharacter current game object that is not null.
     * @return true if current game object should transit to its next AIState.
     */
    public boolean shouldTransition(State state, MovingEntity currentCharacter) {
        return condition.isMet(state, currentCharacter);
    }

    /**
     * Get the name key that indicates the next ChoiceCraft_V1_0_6_Alpha.state of game object.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the name key that indicates the next ChoiceCraft_V1_0_6_Alpha.state of game object.</p>
     *
     * @return the name key that indicates the next ChoiceCraft_V1_0_6_Alpha.state of game object.
     */
    public String getNextState() {
        return nextState;
    }

    /**
     * Set a new ChoiceCraft_V1_0_6_Alpha.state for current AI State. Use this method when point to multiple ChoiceCraft_V1_0_6_Alpha.state.
     * <p>Precondition: none.</p>
     * <p>Postcondition: set a new ChoiceCraft_V1_0_6_Alpha.state for current AI State.</p>
     *
     * @param nextState that is not null.
     */
    public void setNextState(String nextState) {
        this.nextState = nextState;
    }
}
