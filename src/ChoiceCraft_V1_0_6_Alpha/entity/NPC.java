/**
 * ========================================================================================================================
 * NPC of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.ai.AIManager;
import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.entity.action.Action;
import ChoiceCraft_V1_0_6_Alpha.entity.action.Greeting;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.util.Optional;

/**
 * Superclass for all ChoiceCraft npc.
 *
 * @author David Liu
 * @since 3/19/2026
 */
public class NPC extends MovingEntity{

    private final AIManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        this.animationManager = new AnimationManager("enchanter_idle_8dir_spritesheet", spriteLibrary.getEntitySprite("enchanter"));
        aiManager = new AIManager();
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Update movement of Moving Entity</p>
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     *
     * @param state current state that is not null.
     */
    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    public String toString() {
        return "[NPC]";
    }
}
