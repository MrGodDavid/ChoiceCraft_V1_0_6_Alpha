/**
 * ========================================================================================================================
 * Moving entity class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.display.Display;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Direction;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Motion;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.awt.*;

/**
 * Moveable entity in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    protected AnimationManager animationManager;

    protected Motion motion;
    protected Direction direction;

    public MovingEntity(Controller controller, SpriteLibrary spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(1);
        this.direction = Direction.SOUTH;
        this.animationManager = new AnimationManager("player_idle_8dir_spritesheet", spriteLibrary.getEntitySprite("player"));
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Update movement of Moving Entity</p>
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     */
    @Override
    public void update() {
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animationManager.update(direction);
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
            if (motion.isMoving()) {
                animationManager.playAnimation("zombie_basic_walking_8dir_spritesheet");
            } else {
                animationManager.playAnimation("zombie_basic_idle_8dir_spritesheet");
            }
        }
    }

    private void manageDirection() {
        if (motion.isMoving()) {
            this.direction = Direction.fromMotion(motion);
        }
    }

    /**
     * Game objects of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft each provide sprite (graphic) to rendering system
     * {@link Display ChoiceCraft_V1_0_6_Alpha.display.Display class}
     * <p>Precondition: game object exists in ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.</p>
     * <p>Postcondition: return the sprite of game object of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft</p>
     *
     * @return the sprite of game object of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
     */
    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }
}
