/**
 * ========================================================================================================================
 * Enchanter of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.character.npc;

import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

/**
 * This npc sells Enchanted books to player.
 *
 * @author David Liu
 * @since 3/20/2026
 */
public final class Enchanter extends NPC {

    public Enchanter(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
    }
}
