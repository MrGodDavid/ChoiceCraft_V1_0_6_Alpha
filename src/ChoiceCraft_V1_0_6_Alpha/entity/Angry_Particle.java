/**
 * ========================================================================================================================
 * Angry particles of Enchanters.
 * <p>
 * Author: David Liu.                                                                                   Date:3/23/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.gfx.AnimationManager;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteSet;

/**
 * Angry particles of Enchanters.
 *
 * @author David Liu
 * @since 3/23/2026
 */
public class Angry_Particle extends MovingEntity{

    private NPCController controller;

    public Angry_Particle(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);
        this.controller = npcController;

        this.animationManager = new AnimationManager("angry_particle_default_spritesheet",
                new SpriteSet(spriteLibrary.getImage("angry_particle_default_spritesheet")), false);
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

    /**
     * Handles motion of moving entity of ChoiceCraft. Each subclass of {@code MovingEntity} needs an implementation of
     * this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: tell ChoiceCraft to handle different motion based on subclass's custom implementation
     * of this method.</p>
     */
    @Override
    protected void handleMotion() {
        motion.update(controller);
    }

    /**
     * When adding a new NPC or MovingEntity that overrides the AnimationManager in its constructor, must also handle
     * this case as well.
     */
    @Override
    protected String decideAnimation() {
        return "angry_particle_default_spritesheet";
    }

    public void setEmitter(GameObject emitter) {
        this.position = emitter.getPosition();
        this.renderOffset = emitter.getRenderOffset();
        emitter.parent(this);
    }
}
