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
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.entity.SelectionCircle;
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
public final class Player extends MovingEntity {

    private MovingEntity target;
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
    protected void handleCollision(GameObject other) {
        if (other instanceof NPC npc) {
            npc.clearEffects();
        }
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
        handleTarget(state);
    }

    private void handleTarget(State state) {
        Optional<MovingEntity> closetMovingEntity = findClosetMovingEntity(state);

        if (closetMovingEntity.isPresent()) {
            MovingEntity movingEntity = closetMovingEntity.get();
            if (!movingEntity.equals(target)) {
                selectionCircle.setParent(movingEntity);
                target = movingEntity;
            }
        } else {
            selectionCircle.clearParent();
            target = null;
        }
    }

    private Optional<MovingEntity> findClosetMovingEntity(State state) {
        return state.getGameObjectsOfClass(MovingEntity.class).stream()
                .filter(movingEntity -> getPosition().distanceTo(movingEntity.getPosition()) < targetRange)
                .filter(movingEntity -> isFacing(movingEntity.getPosition()))
                .min(Comparator.comparingDouble(movingEntity -> position.distanceTo(movingEntity.getPosition())));
    }

    @Override
    public String toString() {
        return "[PLAYER]";
    }
}
