/**
 * ========================================================================================================================
 * Enemy patrolling state of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.controller.EnemyController;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

/**
 * Enemy patrolling state of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public class Patrol extends AIState{

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
        return new AITransition("idle", this::processNewAICondition);
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
        if (currentCharacter instanceof Enemy) {
            EnemyController controller = (EnemyController) currentCharacter.getController();
            controller.moveToPlayer(new Position(
                    2 * ChoiceCraft.SPRITE_SIZE,
                    2 * ChoiceCraft.SPRITE_SIZE
            ), currentCharacter.getPosition());
        }
    }

    private boolean processNewAICondition(State state, MovingEntity currentCharacter) {
        return arrived(currentCharacter);
    }

    private boolean arrived(MovingEntity currentCharacter) {
        return currentCharacter.getPosition().isInRangeOf(new Position(
                2 * ChoiceCraft.SPRITE_SIZE,
                2 * ChoiceCraft.SPRITE_SIZE
        ));
    }
}
