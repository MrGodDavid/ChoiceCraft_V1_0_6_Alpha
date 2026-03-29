/**
 * ========================================================================================================================
 * KeyHandler, MouseListener, and MouseMotionListener of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

import java.awt.event.*;

/**
 * Listen user's keyboard input. Process ChoiceCraft based on user's input.
 * <p>Listen user's mouse clicking, pressing, and moving event. Process ChoiceCraft based on user's input.</p>
 * <p>This class DOES NOT define behaviors (key input commands) in ChoiceCraft.</p>
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Input implements KeyListener, MouseListener, MouseMotionListener {

    private final boolean[] currentlyPressed;
    private final boolean[] pressed;

    private Position cursorPosition;
    private boolean mouseClicked;
    private boolean mousePressed;

    /**
     * Construct KeyHandler class of ChoiceCraft. Initializes pressed boolean array that indicates whether user
     * pressed a specific key or not.
     */
    public Input() {
        currentlyPressed = new boolean[255];
        pressed = new boolean[256];
        cursorPosition = new Position(-1, -1);
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

    public Position getCursorPosition() {
        return cursorPosition;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void clearMouseClick() {
        mouseClicked = false;
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

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = true;
        mousePressed = false;
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        cursorPosition =  new Position(e.getPoint().getX(), e.getPoint().getY());
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        cursorPosition =  new Position(e.getPoint().getX(), e.getPoint().getY());
    }
}
