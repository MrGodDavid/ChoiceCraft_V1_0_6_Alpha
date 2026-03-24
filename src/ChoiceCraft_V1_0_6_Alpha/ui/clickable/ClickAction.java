/**
 * ========================================================================================================================
 * Click action functional interface.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Click action functional interface.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public interface ClickAction {

    /**
     * Execute a click action.
     * <p>Precondition: none.</p>
     * <p>Postcondition: execute a click action.</p>
     *
     * @param state that is not null.
     */
    void execute(State state);
}
