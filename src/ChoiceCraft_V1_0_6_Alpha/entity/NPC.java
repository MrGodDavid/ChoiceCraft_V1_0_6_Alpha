/**
 * ========================================================================================================================
 * NPC of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

/**
 * Superclass for all ChoiceCraft npc.
 *
 * @author David Liu
 * @since 3/19/2026
 */
public class NPC extends MovingEntity{

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager("zombie_basic_idle_8dir_spritesheet", spriteLibrary.getEntitySprite("zombie_basic"));
    }
}
