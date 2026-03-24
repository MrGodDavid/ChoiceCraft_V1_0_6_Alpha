/**
 * ========================================================================================================================
 * General condition component for game object in ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

/**
 * General condition component for game object in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/24/2026
 */
public interface Condition {

    /**
     * Check whether the game object meets certain conditions. Once the game object met certain conditions, the game
     * will trigger other behaviors of game object.
     * <p>Precondition: none.</p>
     * <p>Postcondition: output a boolean value that indicates whether the current game object has met certain
     * conditions</p>
     *
     * @return a boolean value that indicates whether the current game object has met certain conditions.
     */
    boolean isMet();
}
