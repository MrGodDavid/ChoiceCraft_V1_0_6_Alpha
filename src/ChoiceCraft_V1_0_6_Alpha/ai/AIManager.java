/**
 * ========================================================================================================================
 * Manages NPC and Enemy's behaviors.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai;

import ChoiceCraft_V1_0_6_Alpha.ai.state.*;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Manages ChoiceCraft's game objects (npc and enemy)'s behaviors.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class AIManager {

    private AIState currentAIState;

    public AIManager(String initialState) {
        transitionTo(initialState);
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

    @SuppressWarnings("all")
    private void transitionTo(String nextState) {
        switch (nextState) {
            case "wander" -> currentAIState = new Wander();
            case "patrol" -> currentAIState = new Patrol();
            case "npc_idle" -> currentAIState = new NPC_Idle();
            case "enemy_idle" -> currentAIState = new Enemy_Idle();
            default -> currentAIState = new NPC_Idle();
        }
    }
}
