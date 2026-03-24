/**
 * ========================================================================================================================
 * Punching npc.
 * <p>
 * Author: David Liu.                                                                                   Date:3/23/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action;

import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.entity.Angry_Particle;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Scared;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;

/**
 * Punching npc.
 *
 * @author David Liu.
 * @since 3/23/2026
 */
public class PunchNPC extends Action {

    private int lifeSpanInUpdates;
    private Humanoid target;
    private Angry_Particle particle;

    public PunchNPC(Humanoid target) {
        super();
        this.target = target;
        lifeSpanInUpdates = GameLoop.UPDATES_PER_SECOND;
        interruptable = false;
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
        return "player_attacking_fist_8dir_spritesheet";
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
        lifeSpanInUpdates--;
        if (particle == null) {
            emitParticles(state);
        } else {
            particle.halt();
        }
        if (isDone()) {
            target.setRenderOrder(6);
            particle.setRenderOrder(6);
        }
    }

    private void emitParticles(State state) {
        target.perform(new JumpScared());
        target.addEffect(new Scared());
        particle = new Angry_Particle(new NPCController(), state.getSpriteLibrary());
        particle.setEmitter(target);
        state.spawn(particle);
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
        return lifeSpanInUpdates == 0;
    }
}
