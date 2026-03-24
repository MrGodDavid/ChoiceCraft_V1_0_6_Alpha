/**
 * ========================================================================================================================
 * Enemy patrolling ChoiceCraft_V1_0_6_Alpha.state of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.controller.EnemyController;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Enemy patrolling ChoiceCraft_V1_0_6_Alpha.state of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public final class Patrol extends AIState {

    public Patrol() {
        super();
    }

    /**
     * Initialize AI transition in subclasses of all AI states.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initialize AI transition in subclasses of all AI states.</p>
     *
     * @return initialized aiTransition.
     */
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("wander", this::processNewAICondition);
    }

    /**
     * Update ChoiceCraft game object ai ChoiceCraft_V1_0_6_Alpha.state.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object ai ChoiceCraft_V1_0_6_Alpha.state.</p>
     *
     * @param state            that is not null.
     * @param currentCharacter that is not null.
     */
    @Override
    @SuppressWarnings("all")
    public void update(State state, MovingEntity currentCharacter) {
        if (currentCharacter instanceof Enemy) {
            Player player = state.getGameObjectsOfClass(Player.class).get(0);
            EnemyController controller = (EnemyController) currentCharacter.getController();
            controller.moveToPlayer(player.getPosition(), currentCharacter.getPosition());
        }
    }

    @SuppressWarnings("all")
    private boolean processNewAICondition(State state, MovingEntity currentCharacter) {
        Player player = state.getGameObjectsOfClass(Player.class).get(0);
        return currentCharacter.collidesWith(player);
    }
}
