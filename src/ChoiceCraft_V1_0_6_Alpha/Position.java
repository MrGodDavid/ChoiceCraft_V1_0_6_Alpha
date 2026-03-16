package ChoiceCraft_V1_0_6_Alpha; /**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.Position component of game objects of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

/**
 * ChoiceCraft_V1_0_6_Alpha.Position component of game objects of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class Position {

    private int x;
    private int y;

    /**
     * Construct a position component for game objects in ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.
     * <p>Precondition: input x coordinate and y coordinate are integers.</p>
     * <p>Postcondition: create the position component of game object of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft</p>
     *
     * @param x that is an integer.
     * @param y that is an integer.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
