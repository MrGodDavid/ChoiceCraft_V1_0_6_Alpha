/**
 * ========================================================================================================================
 * ChoiceCraft game.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

/**
 * ChoiceCraft game.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class ChoiceCraft {

    private final Display display;

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
    }

    public void update() {

    }

    public void render() {
        display.render(this);
    }
}
