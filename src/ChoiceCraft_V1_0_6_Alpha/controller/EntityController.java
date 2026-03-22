/**
 * ========================================================================================================================
 * Controller interface.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.controller;

/**
 * Define behaviors of all input listeners that implements this interface of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public interface EntityController {

    /**
     * Check if the user is requesting up command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by up command.</p>
     *
     * @return true if user is requesting up command.
     */
    boolean isRequestingUp();

    /**
     * Check if the user is requesting down command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by down command.</p>
     *
     * @return true if user is requesting down command.
     */
    boolean isRequestingDown();

    /**
     * Check if the user is requesting left command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by left command.</p>
     *
     * @return true if user is requesting left command.
     */
    boolean isRequestingLeft();

    /**
     * Check if the user is requesting right command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by right command.</p>
     *
     * @return true if user is requesting right command.
     */
    boolean isRequestingRight();
}
