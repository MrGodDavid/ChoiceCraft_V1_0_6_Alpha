/**
 * ========================================================================================================================
 * ChoiceCraft debugger renderer
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox;

import java.awt.*;

/**
 * ChoiceCraft debugger renderer.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public final class DebugRenderer {

    /**
     * Render all game objects' sprites in ChoiceCraft.
     * <p>Precondition: input ChoiceCraft is not null. Input graphics from BufferStrategy is not null</p>
     * <p>Postcondition: renders ChoiceCraft.</p>
     *
     * @param state that is not null.
     * @param g     that is not null.
     */
    public void render(State state, Graphics g) {
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, g, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics g, Camera camera) {
        g.setColor(Color.RED);
        g.drawRect(
                (int) collisionBox.getBound().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBound().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBound().getWidth(),
                (int) collisionBox.getBound().getHeight()
        );
    }
}
