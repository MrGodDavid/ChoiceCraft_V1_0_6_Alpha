/**
 * ========================================================================================================================
 * Player of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.character.player;

import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.SelectionCircle;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action.PunchNPC;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Scared;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.util.Comparator;
import java.util.Optional;

/**
 * Player class.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Player extends Humanoid {

    private Humanoid target;
    private double targetRange;
    private SelectionCircle selectionCircle;

    public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle) {
        super(entityController, spriteLibrary);
        this.selectionCircle = selectionCircle;
        this.targetRange = ChoiceCraft.SPRITE_SIZE;
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
    protected void handleCollision(GameObject other) {}

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
        handleTarget(state);
        handleInput(state);
    }

    private void handleInput(State state) {
        if (entityController.isRequestingAction()) {
            if (target != null) {
                perform(new PunchNPC(target));
            }
        }
    }

    private void handleTarget(State state) {
        Optional<Humanoid> closetHumanoid = findClosetMovingEntity(state);

        if (closetHumanoid.isPresent()) {
            Humanoid humanoid = closetHumanoid.get();
            if (!humanoid.equals(target)) {
                selectionCircle.parent(humanoid);
                target = humanoid;
            }
        } else {
            selectionCircle.clearParent();
            target = null;
        }
    }

    private Optional<Humanoid> findClosetMovingEntity(State state) {
        return state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> getPosition().distanceTo(humanoid.getPosition()) < targetRange)
                .filter(humanoid -> isFacing(humanoid.getPosition()))
                .filter(humanoid -> !humanoid.isAffectedBy(Scared.class))
                .min(Comparator.comparingDouble(humanoid -> position.distanceTo(humanoid.getPosition())));
    }

    @Override
    public String toString() {
        return "[PLAYER]";
    }
}
