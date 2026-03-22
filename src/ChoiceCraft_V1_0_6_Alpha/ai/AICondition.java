/**
 * ========================================================================================================================
 * Defines the condition of AI of ChoiceCraft's game objects.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ai;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Defines the condition of AI of ChoiceCraft's game objects. Once the game object meets certain AI conditions, ai manager
 * will trigger some behavior for that game object.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public interface AICondition {

    /**
     * Check whether the game object meets certain conditions. Once the game object met certain conditions, the game
     * will trigger other behaviors of game object.
     * <p>Precondition: none.</p>
     * <p>Postcondition: output a boolean value that indicates whether the current game object has met certain
     * conditions</p>
     *
     * @param state            current game state.
     * @param currentCharacter current game object.
     * @return a boolean value that indicates whether the current game object has met certain conditions.
     */
    boolean isMet(State state, MovingEntity currentCharacter);
}
