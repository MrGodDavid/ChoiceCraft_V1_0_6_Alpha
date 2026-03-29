/**
 * ========================================================================================================================
 * Act like a consumer for Mouse input of ChoiceCraft
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input.mouse;

import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Act like a consumer for Mouse input of ChoiceCraft
 *
 * @author David Liu
 * @since 3/28/2026
 */
public interface MouseConsumer {

    /**
     * Define the click function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onClick() that is
     * specified by user.</p>
     *
     * @param state that is not null.
     */
    void onClick(State state);

    /**
     * Define the drag function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onDrag() that is
     * specified by user.</p>
     *
     * @param state that is not null.
     */
    void onDrag(State state);
}
