/**
 * ========================================================================================================================
 * Player's walk in direction when starting ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action;

import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Motion;
import ChoiceCraft_V1_0_6_Alpha.math.vector.Vector2d;
import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Player's walk in direction when starting ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/27/2026
 */
public class WalkInDirection extends Action {

    private int walkTime;
    private Motion motion;

    public WalkInDirection(Vector2d direction) {
        walkTime = 3 * GameLoop.UPDATES_PER_SECOND;
        motion = new Motion(1);
        motion.add(direction);
    }

    /**
     * Update ChoiceCraft game object's action.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object's action.</p>
     *
     * @param state    that is not null.
     * @param humanoid that is not null.
     */
    @Override
    public void update(State state, Humanoid humanoid) {
        walkTime--;
//        System.out.println(motion);
        humanoid.apply(motion);
    }

    /**
     * Check if a ChoiceCraft's game object's action is done.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return true if an action is done. False otherwise.</p>
     *
     * @return true if an action is done. False otherwise.
     */
    @Override
    public boolean isDone() {
        return walkTime <= 0;
    }

    /**
     * Get the key of entity's animation name.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the key of entity's animation name.</p>
     *
     * @return the key of entity's animation name.
     */
    @Override
    public String getAnimationName() {
        return "player_walking_8dir_spritesheet";
    }

    /**
     * Get the key of sound's name.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the key of sound's name.</p>
     *
     * @return the key of sound's name.
     */
    @Override
    public String getSoundName() {
        return null;
    }
}
