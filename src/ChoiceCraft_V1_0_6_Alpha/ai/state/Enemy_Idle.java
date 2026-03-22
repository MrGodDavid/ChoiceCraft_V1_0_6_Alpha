/**
 * ========================================================================================================================
 * Idle state. (Stand state).
 * <p>
 * Author: David Liu.                                                                                   Date:3/22/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

/**
 * Idle state. (Stand state).
 *
 * @author David Liu.
 * @since 3/22/2026
 */
public final class Enemy_Idle extends AIState{

    private double distanceToPlayer;

    /**
     * Initialize AI transition in subclasses of all AI states.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initialize AI transition in subclasses of all AI states.</p>
     *
     * @return initialized aiTransition.
     */
    @Override
    protected AITransition initializeTransition() {
        return new AITransition("patrol", this::processIsAIConditionMet);
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
        Player player = state.getGameObjectsOfClass(Player.class).get(0);
        if (currentCharacter instanceof Enemy enemy) {
            Position enemyPosition = enemy.getPosition();
            distanceToPlayer = enemyPosition.distanceTo(player.getPosition());
        }
    }

    private boolean processIsAIConditionMet(State state, MovingEntity currentCharacter) {
        return distanceToPlayer <= 2 * ChoiceCraft.SPRITE_SIZE;
    }
}
