/**
 * ========================================================================================================================
 * ChoiceCraft game.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

import java.awt.*;

/**
 * ChoiceCraft game.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class ChoiceCraft {

    private final Display display;
    private final Rectangle rectangle;

    /**
     * Construct ChoiceCraft game.
     * <p>Precondition: input dimension that is positive for both width and height.</p>
     * <p>Postcondition: initialize ChoiceCraft game with desired width and height.</p>
     *
     * @param width  that is a positive integer.
     * @param height that is a positive integer.
     */
    public ChoiceCraft(int width, int height) {
        display = new Display(width, height);
        rectangle = new Rectangle(0, 0, 50, 50);
    }

    /**
     * Update ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft game once.</p>
     */
    public void update() {
        rectangle.setLocation((int) (rectangle.getX() + 1), (int) (rectangle.getY() + 1));
    }

    /**
     * Render ChoiceCraft game once. Call this method multiple times in one second. (FPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: render ChoiceCraft game once.</p>
     */
    public void render() {
        display.render(this);
    }


    public Rectangle getRectangle() {
        return rectangle;
    }
}
