/**
 * ========================================================================================================================
 * Game controller of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/22/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.controller;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.input.Input;

import java.awt.event.KeyEvent;

/**
 * Listen user keyboard input. Process ChoiceCraft based on user input to ChoiceCraft (Button, hovering effect, etc.)
 * <p>This class DOES NOT define behaviors (key input commands) in ChoiceCraft.</p>
 *
 * @author David Liu
 * @since 3/22/2026
 */
public final class GameController {

    private final Input keyInput;

    public GameController(Input keyInput) {
        this.keyInput = keyInput;
    }

    /**
     * Update the user input to ChoiceCraft. Handles user command keys. For instance, pressing F3 key to
     * toggle debug mode.
     * <p>Precondition: none.</p>
     * <p>Postcondition: handle user command keys.</p>
     *
     * @param game that is not null.
     */
    public void update(ChoiceCraft game) {
        if (keyInput.isPressed(KeyEvent.VK_F3)) {
            game.getSettings().toggleDebugMode();
        }

        if (keyInput.isPressed(KeyEvent.VK_F9)) {
            game.getSettings().increaseGameSpeedMultiplier();
        }
        if (keyInput.isPressed(KeyEvent.VK_F7)) {
            game.getSettings().decreaseGameSpeedMultiplier();
        }
    }
}
