/**
 * ========================================================================================================================
 * Position component of game objects of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

import ChoiceCraft_V1_0_6_Alpha.math.vector.Vector2d;

/**
 * Position component of game objects of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Position {

    public static final int PROXIMITY_RANGE = 5;

    private double x;
    private double y;

    /**
     * Construct a position component for game objects in ChoiceCraft.
     * <p>Precondition: input x coordinate and y coordinate are integers.</p>
     * <p>Postcondition: create the position component of game object of ChoiceCraft</p>
     *
     * @param x that is an integer.
     * @param y that is an integer.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construct a position component for game objects in ChoiceCraft.
     * <p>Precondition: input x coordinate and y coordinate are doubles.</p>
     * <p>Postcondition: create the position component of game object of ChoiceCraft</p>
     *
     * @param x that is an integer.
     * @param y that is an integer.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void apply(Motion motion) {
        Vector2d vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    public boolean isInRangeOf(Position position) {
        boolean inRangeX = Math.abs(x - position.getX()) < PROXIMITY_RANGE;
        boolean inRangeY = Math.abs(y - position.getY()) < PROXIMITY_RANGE;
        return inRangeX && inRangeY;
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
