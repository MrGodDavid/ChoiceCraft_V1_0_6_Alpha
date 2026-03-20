/**
 * ========================================================================================================================
 * AI States.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * AI States.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public abstract class AIState {

    private AITransition transition;

    public AIState() {
        this.transition = initializeTransition();
    }

    /**
     * Initialize AI transition in subclasses of all AI states.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initialize AI transition in subclasses of all AI states.</p>
     *
     * @return initialized aiTransition.
     */
    protected abstract AITransition initializeTransition();

    /**
     * Update ChoiceCraft game object ai state.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object ai state.</p>
     *
     * @param state            that is not null.
     * @param currentCharacter that is not null.
     */
    public abstract void update(State state, NPC currentCharacter);

    public boolean shouldTransition(State state, NPC currentCharacter) {
        return transition.shouldTransition(state, currentCharacter);
    }

    public String getNextState() {
        return transition.getNextState();
    }
}
