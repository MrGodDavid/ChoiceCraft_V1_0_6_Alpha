/**
 * ========================================================================================================================
 * AI Wander States.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai.state;

import ChoiceCraft_V1_0_6_Alpha.ai.AITransition;
import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * AI Wander state.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public final class Wander extends AIState {

    private final List<Position> targets;

    public Wander() {
        super();
        targets = new ArrayList<>();
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
        return new AITransition("npc_idle", this::processNewAICondition);
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
    @SuppressWarnings("all")
    public void update(State state, MovingEntity currentCharacter) {
        if (targets.isEmpty()) {
            targets.add(state.getRandomPosition());
        }

        if (currentCharacter instanceof NPC) {
            NPCController controller = (NPCController) currentCharacter.getController();
            controller.moveToTarget(targets.get(0), currentCharacter.getPosition());

            if (arrived(currentCharacter)) {
                controller.stop();
            }
        }
    }

    private boolean processNewAICondition(State state, MovingEntity currentCharacter) {
        return arrived(currentCharacter);
    }

    @SuppressWarnings("all")
    private boolean arrived(MovingEntity currentCharacter) {
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));
    }
}
