/**
 * ========================================================================================================================
 * Speeding state of ChoiceCraft's entity. This state increases the speed of ChoiceCraft entity.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.effect;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Speeding state of ChoiceCraft's entity. This state increases the speed of ChoiceCraft entity.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public class Speeding extends Effect {

    private double speedMultiplier;

    public Speeding() {
        super(GameLoop.UPDATES_PER_SECOND * 5);
        speedMultiplier = 2.5;
    }

    /**
     * Update moving entity's effect.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update moving entity's effect..</p>
     *
     * @param state  that is not null.
     * @param entity that is not null.
     */
    @Override
    public void update(State state, MovingEntity entity) {
        super.update(state, entity);

        entity.multiplySpeed(speedMultiplier);
    }
}
