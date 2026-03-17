/**
 * ========================================================================================================================
 * 2 dimensional vector that x and y component each is double val.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.math.vector;

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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
