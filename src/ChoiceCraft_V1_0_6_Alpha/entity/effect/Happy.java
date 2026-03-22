/**
 * ========================================================================================================================
 * Happy effect of NPC.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.effect;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.action.Greeting;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Happy effect of NPC. NPC has discount buff when having this effect.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public final class Happy extends Effect{

    private static final double GREETING_RATE = 1d / GameLoop.UPDATES_PER_SECOND / 10d;

    public Happy() {
        super(Integer.MAX_VALUE);
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

        if (Math.random() < GREETING_RATE) {
            entity.perform(new Greeting());
        }
    }
}
