/**
 * ========================================================================================================================
 * Idle state. (Stand state).
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Idle state. (Stand state).
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class Idle extends AIState{

    private int updatesAlive;

    /**
     * Initialize AI transition in subclasses of all AI states.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initialize AI transition in subclasses of all AI states.</p>
     *
     * @return initialized aiTransition.
     */
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", this::processIsAIConditionMet);
    }

    /**
     * Update ChoiceCraft game object ai state.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object ai state.</p>
     *
     * @param state            that is not null.
     * @param currentCharacter that is not null.
     */
    @Override
    public void update(State state, MovingEntity currentCharacter) {
        updatesAlive++;
    }

    private boolean processIsAIConditionMet(State state, MovingEntity currentCharacter) {
        return updatesAlive >= state.getTime().getUpdatesFromSeconds(3);
    }
}
