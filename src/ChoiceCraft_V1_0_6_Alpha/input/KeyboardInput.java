/**
 * ========================================================================================================================
 * KeyHandler of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listen user keyboard input. Process ChoiceCraft based on user input.
 * <p>This class DOES NOT define behaviors (key input commands) in ChoiceCraft.</p>
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class KeyboardInput implements KeyListener {

    private final boolean[] currentlyPressed;
    private final boolean[] pressed;

    /**
     * Construct KeyHandler class of ChoiceCraft. Initializes pressed boolean array that indicates whether user
     * pressed a specific key or not.
     */
    public KeyboardInput() {
        currentlyPressed = new boolean[255];
        pressed = new boolean[256];
    }

    public boolean isCurrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }

    public boolean isPressed(int keyCode) {
        if (!pressed[keyCode] && currentlyPressed[keyCode]) {
            pressed[keyCode] = true;
            return true;
        }
        return false;
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        currentlyPressed[keyCode] = true;
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        currentlyPressed[keyCode] = false;
        pressed[keyCode] = false;
    }
}
