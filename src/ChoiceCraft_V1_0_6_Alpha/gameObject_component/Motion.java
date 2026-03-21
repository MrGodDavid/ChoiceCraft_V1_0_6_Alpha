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
public final class Motion {

    private Vector2d vector;
    private double speed;

    public Motion(double speed) {
        this.speed = speed;
        this.vector = new Vector2d();
    }

    /**
     * Update player's directional vector based in inputs from controller.
     * <p>The input controller defines the direction of player's movement. North (up), South (down), East (right), West (left)
     * Northeast, Southeast, Northwest, and Southwest. Before applying the directional vector on movement component in
     * {@link ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity MovingEntity}, first normalize the vector, then multiply it by
     * the speed of the player. </p>
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
        vector.multiply(speed);
    }

    public void stop(boolean stopX, boolean stopY) {
        vector = new Vector2d(
                stopX ? 0 : vector.getX(),
                stopY ? 0 : vector.getY()
        );
    }

    public void multiply(double speedMultiplier) {
        vector.multiply(speedMultiplier);
    }

    public Vector2d getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }
}
