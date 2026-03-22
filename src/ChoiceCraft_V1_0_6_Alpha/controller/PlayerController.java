/**
 * ========================================================================================================================
 * Player controller of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.controller;

import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;

import java.awt.event.KeyEvent;

/**
 * Player controller of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class PlayerController implements EntityController {

    private final KeyboardInput keyInput;

    public PlayerController(KeyboardInput keyInput) {
        this.keyInput = keyInput;
    }

    /**
     * Check if the user is requesting up command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by up command.</p>
     *
     * @return true if user is requesting up command.
     */
    @Override
    public boolean isRequestingUp() {
        return keyInput.isCurrentlyPressed(KeyEvent.VK_W) || keyInput.isCurrentlyPressed(KeyEvent.VK_UP);
    }

    /**
     * Check if the user is requesting down command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by down command.</p>
     *
     * @return true if user is requesting down command.
     */
    @Override
    public boolean isRequestingDown() {
        return keyInput.isCurrentlyPressed(KeyEvent.VK_S) || keyInput.isCurrentlyPressed(KeyEvent.VK_DOWN);
    }

    /**
     * Check if the user is requesting left command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by left command.</p>
     *
     * @return true if user is requesting left command.
     */
    @Override
    public boolean isRequestingLeft() {
        return keyInput.isCurrentlyPressed(KeyEvent.VK_A) || keyInput.isCurrentlyPressed(KeyEvent.VK_LEFT);
    }

    /**
     * Check if the user is requesting right command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by right command.</p>
     *
     * @return true if user is requesting right command.
     */
    @Override
    public boolean isRequestingRight() {
        return keyInput.isCurrentlyPressed(KeyEvent.VK_D) || keyInput.isCurrentlyPressed(KeyEvent.VK_RIGHT);
    }
}
