/**
 * ========================================================================================================================
 * Manages animation sheets of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Manages animation sheets of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public class AnimationManager {

    private final SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private int currentFrameTime;
    private int updatesPerFrame;
    private int frameIndex;

    public AnimationManager(SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 5;
        this.frameIndex = 0;
        this.currentFrameTime = 0;

        playAnimation("player_idle_8dir_spritesheet");
    }

    /**
     * Return the current animation sprite.
     * <p>Precondition: none.</p>
     * <p>Postcondition: return the current animation sprite.</p>
     *
     * @return the current animation sprite.
     */
    public Image getSprite() {
        return currentAnimationSheet.getSubimage(
                frameIndex * ChoiceCraft.SPRITE_SIZE, 0, ChoiceCraft.SPRITE_SIZE, ChoiceCraft.SPRITE_SIZE
        );
    }

    /**
     * Update the current animation frame in AnimationManager.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update the current animation frame in AnimationManager.</p>
     */
    public void update() {
        currentFrameTime++;

        if (currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            if (frameIndex >= currentAnimationSheet.getWidth() / ChoiceCraft.SPRITE_SIZE - 1) {
                frameIndex = 0;
            }
        }
    }

    /**
     * Play the animation by a specific name.
     * <p>Precondition: name String is not null.</p>
     * <p>Postcondition: play the animation specified by name key.</p>
     *
     * @param name that is used to find the animation that user wants to play.
     */
    public void playAnimation(String name) {
        this.currentAnimationSheet = (BufferedImage) spriteSet.getAnimationSheet(name);
    }
}
