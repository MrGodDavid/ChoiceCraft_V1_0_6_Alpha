/**
 * ========================================================================================================================
 * Enemy of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.ai.AIManager;
import ChoiceCraft_V1_0_6_Alpha.controller.EntityController;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

/**
 * Superclass for all ChoiceCraft npc.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public class Enemy extends MovingEntity {

    private final AIManager aiManager;

    public Enemy(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        this.animationManager = new AnimationManager("zombie_basic_idle_8dir_spritesheet",
                spriteLibrary.getEntitySprite("zombie_basic"));
        aiManager = new AIManager("enemy_idle");
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
        aiManager.update(state, this);
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
        if (other instanceof Player) {
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }

    @Override
    public String toString() {
        return "[ENEMY]";
    }
}
