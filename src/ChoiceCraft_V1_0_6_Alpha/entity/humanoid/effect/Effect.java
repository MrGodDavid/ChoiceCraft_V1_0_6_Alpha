/**
 * ========================================================================================================================
 * ChoiceCraft game entity effect class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect;

import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.state.State;

/**
 * Superclass for all ChoiceCraft game entity effects.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public abstract class Effect {

    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }

    /**
     * Update moving humanoid's effect.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update moving humanoid's effect.</p>
     *
     * @param state  that is not null.
     * @param humanoid that is not null.
     */
    public void update(State state, Humanoid humanoid) {
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdates <= 0;
    }
}
