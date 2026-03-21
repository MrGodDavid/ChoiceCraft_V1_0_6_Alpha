/**
 * ========================================================================================================================
 * Player of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

/**
 * Player class.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Player extends MovingEntity {

    public Player(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
    }

    @Override
    public String toString() {
        return "[PLAYER]";
    }
}
