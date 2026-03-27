/**
 * ========================================================================================================================
 * Scaring npc.
 * <p>
 * Author: David Liu.                                                                                   Date:3/23/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action;

import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Scaring npc.
 *
 * @author David Liu.
 * @since 3/23/2026
 */
public final class JumpScared extends Action{

    public JumpScared() {
        super();
        interruptable = false;
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
        return false;
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
        return "enchanter_scared_8dir_spritesheet";
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
