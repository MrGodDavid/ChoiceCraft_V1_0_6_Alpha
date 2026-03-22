/**
 * ========================================================================================================================
 * 2 dimensional vector that x and y component each is double val.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.math.vector;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Direction;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

/**
 * Vector 2d class.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public class Vector2d {

    private double x;
    private double y;

    public Vector2d() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void multiply(double speed) {
        this.x *= speed;
        this.y *= speed;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        if (this.length() == 0) return;
        double length = this.length();
        this.x /= length;
        this.y /= length;
    }

    public Direction toDirection() {
        if (x == 0d && y > 0d) return Direction.SOUTH;
        if (x < 0d && y > 0d) return Direction.SOUTH_WEST;
        if (x < 0d && y == 0d) return Direction.WEST;
        if (x < 0d && y < 0d) return Direction.NORTH_WEST;
        if (x == 0d && y < 0d) return Direction.NORTH;
        if (x > 0d && y < 0d) return Direction.NORTH_EAST;
        if (x > 0d && y == 0d) return Direction.EAST;
        if (x > 0d && y > 0d) return Direction.SOUTH_EAST;
        return Direction.SOUTH;
    }

    public static Vector2d copyOf(Vector2d vector) {
        return new Vector2d(vector.getX(), vector.getY());
    }

    public static Vector2d directionBetweenTwoPositions(Position start, Position end) {
        Vector2d direction = new Vector2d(start.getX() - end.getX(),  start.getY() - end.getY());
        direction.normalize();
        return direction;
    }

    public static double dotProduct(Vector2d v1, Vector2d v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
