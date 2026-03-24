/**
 * ========================================================================================================================
 * Humanoid class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/23/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid;

import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.MovingEntity;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.action.Action;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Effect;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Humanoid class.
 *
 * @author David Liu
 * @since 3/23/2026
 */
public abstract class Humanoid extends MovingEntity {

    protected List<Effect> effects;
    protected Optional<Action> action;

    public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        this.effects = new ArrayList<>();
        this.action = Optional.empty();

        this.collisionBoxSize = new Size(14, 34);
        this.renderOffset = new Position(size.getWidth() / 2, size.getHeight() / 2 + 12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth() / 2, collisionBoxSize.getHeight());
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Update movement of Moving Entity</p>
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     *
     * @param state current ChoiceCraft_V1_0_6_Alpha.state that is not null.
     */
    @Override
    @SuppressWarnings("all")
    public void update(State state) {
        super.update(state);
        handleAction(state);
        Iterator<Effect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.update(state, this);
        }

        decideAnimation();
        position.apply(motion);
        cleanEffects();
    }

    @SuppressWarnings("all")
    private void handleAction(State state) {
        if (action.isPresent()) {
            action.get().update(state, this);
        }
    }

    /**
     * Handles motion of moving entity of ChoiceCraft. Each subclass of {@code MovingEntity} needs an implementation of
     * this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: tell ChoiceCraft to handle different motion based on subclass's custom implementation
     * of this method.</p>
     */
    @SuppressWarnings("all")
    @Override
    protected void handleMotion() {
        if (!action.isPresent()) {

        } else {
            motion.stop(true, true);
        }
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

    }

    @Override
    protected String decideAnimation() {
        if (this instanceof Player) {
            if (motion.isMoving()) {
                return "player_walking_8dir_spritesheet";
            } else {
                return "player_idle_8dir_spritesheet";
            }
        } else if (this instanceof NPC) {
            if (action.isPresent()) {
                return action.get().getAnimationName();
            } else if (motion.isMoving()) {
                return "enchanter_walking_8dir_spritesheet";
            } else {
                return "enchanter_idle_8dir_spritesheet";
            }
        } else if (this instanceof Enemy) {
            if (motion.isMoving()) {
                return "zombie_basic_walking_8dir_spritesheet";
            } else {
                return "zombie_basic_idle_8dir_spritesheet";
            }
        }
        return "";
    }

    @SuppressWarnings("all")
    private void cleanEffects() {
        // CLEAN UP EFFECTS
        Iterator<Effect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            if (effect.shouldDelete()) {
                iterator.remove();
            }
        }
        // CLEAN UP MOTIONS
        if (action.isPresent() && action.get().isDone()) {
            action = Optional.empty();
        }
    }

    public void perform(Action action) {
        if (this.action.isPresent() && !this.action.get().isInterruptable()) return;
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    /**
     * Wrapper method of {@link List#clear()}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: clear all {@link MovingEntity}'s effects.</p>
     */
    protected void clearEffects() {
        effects.clear();
    }

    public boolean isAffectedBy(Class<? extends Effect> effectClass) {
        return effects.stream().anyMatch(effect -> effectClass.isInstance(effect));
    }

    public Optional<Action> getCurrentAction() {
        return action;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
