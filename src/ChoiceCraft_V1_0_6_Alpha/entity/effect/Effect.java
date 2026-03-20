/**
 * ========================================================================================================================
 * ChoiceCraft game entity effect class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.effect;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

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
     * Update moving entity's effect.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update moving entity's effect..</p>
     *
     * @param state  that is not null.
     * @param entity that is not null.
     */
    public void update(State state, MovingEntity entity) {
        lifeSpanInUpdates--;
    }

    public boolean shouldDelete() {
        return lifeSpanInUpdates <= 0;
    }
}
