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
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.*;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.math.vector.Vector2d;

import java.awt.*;

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
    protected Vector2d directionVector;
    protected Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion = new Motion(1);
        this.direction = Direction.SOUTH;
        this.animationManager = new AnimationManager("player_idle_8dir_spritesheet", spriteLibrary.getSpriteSet("player"));
        this.directionVector = new Vector2d(0, 0);
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());
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
        motion.update(entityController);
        handleMotion();
        animationManager.update(direction);

        handleCollisions(state);
        animationManager.playAnimation(this.decideAnimation());
        apply(motion);
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

    /**
     * Handles motion of moving entity of ChoiceCraft. Each subclass of {@code MovingEntity} needs an implementation of
     * this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: tell ChoiceCraft to handle different motion based on subclass's custom implementation
     * of this method.</p>
     */
    protected abstract void handleMotion();


    /**
     * When adding a new NPC or MovingEntity that overrides the AnimationManager in its constructor, must also handle
     * this case as well.
     */
    protected abstract String decideAnimation();

    private void manageDirection(Motion motion) {
        if (motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
            this.directionVector = motion.getDirection();
        }
    }

    /**
     * Apply a new {@link Motion} to {@link MovingEntity}. This method first use {@link MovingEntity#manageDirection(Motion)}
     * to covert the get the direction from {@link Motion}, then convert directional vector from {@link Direction} via
     * {@link Motion#getDirection()}.
     * <p>Secondly, this method apply the motion to its position via {@link Position#apply(Motion)}.</p>
     * <p>Precondition: motion is not null.</p>
     * <p>Postcondition: apply the motion to current {@link MovingEntity}.</p>
     *
     * @param motion that is not null.
     */
    public void apply(Motion motion) {
        manageDirection(motion);
        position.apply(motion);
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
        positionWithXApplied.subtract(collisionBoxOffset);
        return CollisionBox.of(positionWithXApplied, collisionBoxSize).collidesWith(otherCollisionBox);
    }

    public boolean willCollideY(GameObject other) {
        CollisionBox otherCollisionBox = other.getCollisionBox();
        Position positionWithYApplied = Position.copyOf(position);
        positionWithYApplied.applyY(motion);
        positionWithYApplied.subtract(collisionBoxOffset);
        return CollisionBox.of(positionWithYApplied, collisionBoxSize).collidesWith(otherCollisionBox);
    }

    public boolean isFacing(Position other) {
        Vector2d direction = Vector2d.directionBetweenTwoPositions(other, getPosition());
        double dotProduct = Vector2d.dotProduct(direction, directionVector);
        return dotProduct > 0;
    }

    public EntityController getController() {
        return entityController;
    }
}
