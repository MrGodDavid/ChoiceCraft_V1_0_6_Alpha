/**
 * ========================================================================================================================
 * ChoiceCraft debugger renderer
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action.Action;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action.Greeting;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Collectors;

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
        for (GameObject gameObject : state.getGameObjects()) {
            if (camera.isInView(gameObject)) {
                CollisionBox collisionBox = gameObject.getCollisionBox();
                drawCollisionBox(collisionBox, g, camera);

                if (gameObject instanceof Humanoid humanoid) {
                    Optional<Action> action = humanoid.getCurrentAction();
                    if (action.isPresent() && action.get() instanceof Greeting greeting) {
                        CollisionBox spreadingCollisionBox = greeting.getSpreadingCollisionBox(humanoid);
                        drawSpreadingArea(spreadingCollisionBox, g, camera);
                    }
                }
//                drawEffectLabel(state, g);
            }
        }
    }

    private void drawEffectLabel(State state, Graphics g) {
        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(Humanoid.class)
                .forEach(humanoid -> {
                    UIText label = new UIText(humanoid.getEffects().stream()
                            .map(effect -> effect.getClass().getSimpleName())
                            .collect(Collectors.joining(",")));
                    label.update(state);
                    g.drawImage(
                            label.getSprite(),
                            humanoid.getPosition().intX() - camera.getPosition().intX(),
                            humanoid.getPosition().intY() - camera.getPosition().intY(),
                            null
                    );
                });
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

    private void drawSpreadingArea(CollisionBox collisionBox, Graphics g, Camera camera) {
        g.setColor(Color.BLUE);
        g.drawRect(
                (int) collisionBox.getBound().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBound().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBound().getWidth(),
                (int) collisionBox.getBound().getHeight()
        );
    }
}
