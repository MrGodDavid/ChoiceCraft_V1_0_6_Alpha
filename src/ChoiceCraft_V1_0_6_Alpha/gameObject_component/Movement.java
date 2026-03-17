/**
 * ========================================================================================================================
 * Movement component of game object in ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.math.vector.Vector2d;

/**
 * Movement component of game objects in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public class Movement {

    private Vector2d vector;
    private double speed;

    public Movement(double speed) {
        this.speed = speed;
        this.vector = new Vector2d();
    }

    /**
     * Update movement component of game objects of ChoiceCraft every frame.
     * <p>Precondition: input controller is not null.</p>
     * <p>Postcondition: game loop update movement component of game object.</p>
     *
     * @param controller that is not null.
     */
    public void update(Controller controller) {
        int deltaX = 0;
        int deltaY = 0;
        if (controller.isRequestingUp())
            deltaY--;
        if (controller.isRequestingDown())
            deltaY++;
        if (controller.isRequestingLeft())
            deltaX--;
        if (controller.isRequestingRight())
            deltaX++;
        vector = new Vector2d(deltaX, deltaY);
        vector.normalize();
        System.out.println(vector.length());
        vector.multiply(speed);
    }

    public Vector2d getVector() {
        return vector;
    }
}
