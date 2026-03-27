/**
 * ========================================================================================================================
 * NPC of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.ai.AIManager;
import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.HumanoidID;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.util.Collections;

/**
 * Superclass for all ChoiceCraft npc.
 *
 * @author David Liu
 * @since 3/19/2026
 */
public abstract class NPC extends Humanoid {

    private final AIManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        HumanoidID randomID = getRandomNPMHumanoid();
        this.animationManager = new AnimationManager("enchanter_2_idle_8dir_spritesheet",
                spriteLibrary.getSpriteSet("enchanter_2"));
        aiManager = new AIManager("npc_idle");
    }

    private HumanoidID getRandomNPMHumanoid() {
        Collections.shuffle(humanoidAssetsIds);
        return humanoidAssetsIds.getFirst();
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Update movement of Moving Entity</p>
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     *
     * @param state current ChoiceCraft_V1_0_6_Alpha.state that is not null.
     */
    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    /**
     * Handle collisions between different game objects.
     * <p>Precondition: other game object is not null.</p>
     * <p>Postcondition: tell ChoiceCraft to handle different collisions based on subclass's custom implementation
     * of this method.</p>
     *
     * @param other game object that is not null.
     */
    @Override
    protected void handleCollision(GameObject other) {
        if (other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }

    @Override
    public String toString() {
        return "[NPC]";
    }
}
