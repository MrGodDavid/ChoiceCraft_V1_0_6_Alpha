/**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.component.Position component of game objects of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

import ChoiceCraft_V1_0_6_Alpha.math.vector.Vector2d;

/**
 * ChoiceCraft_V1_0_6_Alpha.component.Position component of game objects of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Position {

    private double x;
    private double y;

    /**
     * Construct a position component for game objects in ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
     * <p>Precondition: input x coordinate and y coordinate are integers.</p>
     * <p>Postcondition: create the position component of game object of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft</p>
     *
     * @param x that is an integer.
     * @param y that is an integer.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void apply(Motion motion) {
        Vector2d vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    public int intX() {
        return (int) Math.round(x);
    }

    public int intY() {
        return (int) Math.round(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
