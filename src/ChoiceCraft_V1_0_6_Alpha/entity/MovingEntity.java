/**
 * ========================================================================================================================
 * Moving entity class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.display.Display;
import ChoiceCraft_V1_0_6_Alpha.entity.action.Action;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.effect.Effect;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.*;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Moveable entity in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public abstract class MovingEntity extends GameObject {

    protected EntityController entityController;
    protected AnimationManager animationManager;

    protected Motion motion;
    protected Direction direction;
    protected List<Effect> effects;
    protected Optional<Action> action;

    protected Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion = new Motion(1);
        this.direction = Direction.SOUTH;
        this.animationManager = new AnimationManager("player_idle_8dir_spritesheet", spriteLibrary.getEntitySprite("player"));
        this.effects = new ArrayList<>();
        this.action = Optional.empty();
        this.collisionBoxSize = new Size(14, 34);
        this.renderOffset = new Position(
                size.getWidth() / 2,
                size.getHeight() / 2 + 12
        );
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
        handleAction(state);
        handleMotion();

        animationManager.update(direction);

        Iterator<Effect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            Effect effect = iterator.next();
            effect.update(state, this);
        }

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanEffects();
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);
    }

    /**
     * Handle collisions between different game objects.
     * <p>Precondition: other game object is not null.</p>
     * <p>Postcondition: tell ChoiceCraft to handle different collisions based on subclass's custom implementation
     * of this method.</p>
     *
     * @param other game object that is not null.
     */
    protected abstract void handleCollision(GameObject other);

    private void handleAction(State state) {
        if (action.isPresent()) {
            action.get().update(state, this);
        }
    }

    private void handleMotion() {
        if (!action.isPresent()) {
            motion.update(entityController);
        } else {
            motion.stop(true, true);
        }
    }

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

    /**
     * When adding a new NPC or MovingEntity that overrides the AnimationManager in its constructor, must also handle
     * this case as well.
     */
    private void decideAnimation() {
        if (this instanceof Player) {
            if (motion.isMoving()) {
                animationManager.playAnimation("player_walking_8dir_spritesheet");
            } else {
                animationManager.playAnimation("player_idle_8dir_spritesheet");
            }
        } else if (this instanceof NPC) {
            if (action.isPresent()) {
                animationManager.playAnimation(action.get().getAnimationName());
            } else if (motion.isMoving()) {
                animationManager.playAnimation("enchanter_walking_8dir_spritesheet");
            } else {
                animationManager.playAnimation("enchanter_idle_8dir_spritesheet");
            }
        }
    }

    private void manageDirection() {
        if (motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
        }
    }

    public void multiplySpeed(double speedMultiplier) {
        motion.multiply(speedMultiplier);
    }

    /**
     * Game objects of ChoiceCraft each provide sprite (graphic) to rendering system
     * {@link Display Display class}.
     * <p>Wrapper method of {@link AnimationManager#getSprite()}</p>
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the sprite of game object of ChoiceCraft</p>
     *
     * @return the sprite of game object of ChoiceCraft.
     */
    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    /**
     * Game objects of ChoiceCraft each provide collision box to physics detecting methods.
     * {@link CollisionBox CollisionBox class}
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the calculated collision box of game object of ChoiceCraft</p>
     *
     * @return the calculated collision box of game object of ChoiceCraft.
     */
    @Override
    public CollisionBox getCollisionBox() {
        Position positionWithMotion = Position.copyOf(getPosition());
        positionWithMotion.apply(motion);
        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.intX() - collisionBoxSize.getWidth() / 2,
                        positionWithMotion.intY() - collisionBoxSize.getHeight() / 2,
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
    }

    public boolean willCollideX(GameObject other) {
        CollisionBox otherCollisionBox = other.getCollisionBox();
        Position positionWithXApplied = Position.copyOf(position);
        positionWithXApplied.applyX(motion);

        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherCollisionBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherCollisionBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);

        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherCollisionBox);
    }

    public void perform(Action action) {
        this.action = Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public EntityController getController() {
        return entityController;
    }

    /**
     * Wrapper method of {@link List#clear()}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: clear all {@link MovingEntity}'s effects.</p>
     */
    public void clearEffects() {
        effects.clear();
    }

    public boolean isAffectedBy(Class<? extends Effect> effectClass) {
        return effects.stream().anyMatch(effect -> effectClass.isInstance(effect));
    }

    public Optional<Action> getCurrentAction() {
        return action;
    }
}
