/**
 * ========================================================================================================================
 * Greeting from ChoiceCraft game object.
 * <p>
 * Author: David Liu.                                                                                   Date:3/20/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action;

import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Happy;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.GameLoop;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;

import java.awt.*;
import java.util.List;

/**
 * Greeting from ChoiceCraft game object.
 *
 * @author David Liu.
 * @since 3/20/2026
 */
public final class Greeting extends Action {

    public static final double SUCCESSFULLY_SPREADING_RATE = 0.1;

    private int liveSpanInSeconds;
    private final Size spreadAreaSize;
    private CollisionBox spreadingCollisionBox;

    public Greeting() {
        liveSpanInSeconds = GameLoop.UPDATES_PER_SECOND;
        spreadAreaSize = new Size(2 * ChoiceCraft.SPRITE_SIZE, 2 * ChoiceCraft.SPRITE_SIZE);
        spreadingCollisionBox = new CollisionBox(new Rectangle());
    }

    /**
     * Update ChoiceCraft game object's action.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update ChoiceCraft game object's action.</p>
     *
     * @param state  that is not null.
     * @param performer that is not null.
     */
    @Override
    public void update(State state, Humanoid performer) {
        if (--liveSpanInSeconds <= 0) {
            Position spreadAreaPosition = new Position(
                    performer.getPosition().getX() - spreadAreaSize.getWidth() / 2d,
                    performer.getPosition().getY() - spreadAreaSize.getHeight() / 2d
            );

            spreadingCollisionBox = CollisionBox.of(spreadAreaPosition, spreadAreaSize);

            List<Humanoid> humanoidList = state.getGameObjectsOfClass(Humanoid.class);
            for (Humanoid humanoid : humanoidList) {
                if (humanoid.getCollisionBox().collidesWith(spreadingCollisionBox) && !humanoid.isAffectedBy(Happy.class)) {
                    double fallOut = Math.random();
                    if (fallOut < SUCCESSFULLY_SPREADING_RATE) {
                        humanoid.addEffect(new Happy());
                    }
                }
            }
        }
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
        return liveSpanInSeconds <= 0;
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
        return "enchanter_greeting_8dir_spritesheet";
    }

    /**
     * Get the key of sound's name.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the key of sound's name.</p>
     *
     * @return the key of sound's name.
     */
    @Override
    public String getSoundName() {
        return null;
    }

    public CollisionBox getSpreadingCollisionBox(MovingEntity entity) {
        Position spreadAreaPosition = new Position(
                entity.getPosition().getX() - spreadAreaSize.getWidth() / 2d,
                entity.getPosition().getY() - spreadAreaSize.getHeight() / 2d
        );

        return CollisionBox.of(spreadAreaPosition, spreadAreaSize);
    }
}
