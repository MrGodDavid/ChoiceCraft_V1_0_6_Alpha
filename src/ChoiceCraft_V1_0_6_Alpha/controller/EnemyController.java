/**
 * ========================================================================================================================
 * Enemy controller of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.controller;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;

/**
 * Controller for enemy in ChoiceCraft
 *
 * @author David Liu
 * @since 3/19/2026
 */

public final class EnemyController implements Controller {

    private boolean up, left, right, down;
    /**
     * Check if the user is requesting down command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by down command.</p>
     *
     * @return true if user is requesting down command.
     */
    @Override
    public boolean isRequestingDown() {
        return down;
    }

    /**
     * Check if the user is requesting up command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by up command.</p>
     *
     * @return true if user is requesting up command.
     */
    @Override
    public boolean isRequestingUp() {
        return up;
    }

    /**
     * Check if the user is requesting left command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by left command.</p>
     *
     * @return true if user is requesting left command.
     */
    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    /**
     * Check if the user is requesting right command in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: game object being updated by right command.</p>
     *
     * @return true if user is requesting right command.
     */
    @Override
    public boolean isRequestingRight() {
        return right;
    }

    /**
     * Move the NPC from current position to its target position.
     * <p>Precondition: none.</p>
     * <p>Postcondition: npc moves from its current position to its target position.</p>
     *
     * @param target  position that npc will move to.
     * @param current position of the npc right now.
     */
    public void moveToPlayer(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        up = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        left = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        down = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    }

    /**
     * Stop the NPC from moving to different targets or anywhere else.
     * <p>Precondition: none.</p>
     * <p>Postcondition: stop the NPC from moving to different targets or anywhere else.</p>
     */
    public void stop() {
        up = left = down = right = false;
    }
}
