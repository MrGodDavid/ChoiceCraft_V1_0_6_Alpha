/**
 * ========================================================================================================================
 * Manages NPC and Enemy's behaviors.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai;

import ChoiceCraft_V1_0_6_Alpha.ai.state.AIState;
import ChoiceCraft_V1_0_6_Alpha.ai.state.Idle;
import ChoiceCraft_V1_0_6_Alpha.ai.state.Wander;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Manages ChoiceCraft's game objects (npc and enemy)'s behaviors.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class AIManager {

    private AIState currentAIState;

    public AIManager() {
        transitionTo("idle");
    }

    /**
     * Update ChoiceCraft game object's ai states.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object ai state.</p>
     *
     * @param state            that is not null.
     * @param currentCharacter that is not null.
     */
    public void update(State state, MovingEntity currentCharacter) {
        currentAIState.update(state, currentCharacter);

        if (currentAIState.shouldTransition(state, currentCharacter)) {
            transitionTo(currentAIState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
//        System.out.println("Transitioning to " + nextState);
        switch (nextState) {
            case "wander":
                currentAIState = new Wander();
                return;
            default:
                currentAIState = new Idle();
        }
    }
}
