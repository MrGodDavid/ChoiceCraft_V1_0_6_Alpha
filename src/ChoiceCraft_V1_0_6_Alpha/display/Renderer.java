/**
 * ========================================================================================================================
 * Rendering system of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;

import java.awt.*;

/**
 * Rendering system of ChoiceCraft. Acts like a rendering pipeline of ChoiceCraft (Not actually rendering pipeline.)
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class Renderer {

    /**
     * Render all game objects' sprites in ChoiceCraft.
     * <p>Precondition: input ChoiceCraft is not null. Input graphics from BufferStrategy is not null</p>
     * <p>Postcondition: renders ChoiceCraft.</p>
     *
     * @param choiceCraft that is not null.
     * @param g           that is not null.
     */
    public void render(ChoiceCraft choiceCraft, Graphics g) {
        for (GameObject gameObject : choiceCraft.getGameObjects()) {
            g.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().getX(),
                    gameObject.getPosition().getY(),
                    null
            );
        }
    }
}
