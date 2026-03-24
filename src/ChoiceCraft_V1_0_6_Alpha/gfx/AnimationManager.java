/**
 * ========================================================================================================================
 * Manages animation sheets of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Manages animation sheets of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public final class AnimationManager {

    private SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private String currentAnimationName;
    private int currentFrameTime;
    private int updatesPerFrame;
    private int frameIndex;
    private int directionIndex;
    private boolean looping;

    public AnimationManager(String name, SpriteSet spriteSet) {
        this(name, spriteSet, true);
    }

    public AnimationManager(String name, SpriteSet spriteSet, boolean looping) {
        this.spriteSet = spriteSet;
        this.updatesPerFrame = 5;
        this.frameIndex = 0;
        this.currentFrameTime = 0;
        this.directionIndex = 0;
        this.currentAnimationName = "";
        this.looping = looping;

        playAnimation(name);
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
                frameIndex * ChoiceCraft.SPRITE_SIZE,
                directionIndex * ChoiceCraft.SPRITE_SIZE,
                ChoiceCraft.SPRITE_SIZE, ChoiceCraft.SPRITE_SIZE
        );
    }

    /**
     * Update the current animation frame in AnimationManager.
     * <p>Precondition: none.</p>
     * <p>Postcondition: update the current animation frame in AnimationManager.</p>
     *
     * @param direction indicates the direction of game entity. It also indicates the row index of its animation sheet.
     */
    public void update(Direction direction) {
        currentFrameTime++;
        directionIndex = direction.getAnimationRow();

        if (currentFrameTime >= updatesPerFrame) {
            currentFrameTime = 0;
            frameIndex++;

            int animationSize = currentAnimationSheet.getWidth() / ChoiceCraft.SPRITE_SIZE;
            if (frameIndex >= animationSize) {
                frameIndex = looping ? 0 : animationSize - 1;
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
        if (!name.equals(currentAnimationName)) {
            this.currentAnimationSheet = (BufferedImage) spriteSet.getOrGetDefault(name);
            this.currentAnimationName = name;
            frameIndex = 0;
        }
    }

    public void playNewAnimation(String name, SpriteSet spriteSet) {
        this.spriteSet = spriteSet;
        playAnimation(name);
    }
}
