package ChoiceCraft_V1_0_6_Alpha.gameObject_component; /**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.component.Size component of game objects of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

/**
 * ChoiceCraft_V1_0_6_Alpha.component.Size component of game objects of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class Size {

    private int width;
    private int height;

    /**
     * Construct a size component for game objects in ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
     * <p>Precondition: input width and height are positive integers.</p>
     * <p>Postcondition: create the size component of game object of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft</p>
     *
     * @param width that is a positive integer.
     * @param height that is a positive integer.
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
