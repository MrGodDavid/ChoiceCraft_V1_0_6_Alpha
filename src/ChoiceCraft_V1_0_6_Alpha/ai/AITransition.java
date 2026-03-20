/**
 * ========================================================================================================================
 * Transition between different AI behaviors of game object of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai;

import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Transition between different AI behaviors of game object of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class AITransition {

    private final String nextState;
    private final AICondition condition;

    public AITransition(String nextState, AICondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return condition.isMet(state, currentCharacter);
    }

    public String getNextState() {
        return nextState;
    }
}
