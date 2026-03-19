/**
 * ========================================================================================================================
 * ChoiceCraft game.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game;

import ChoiceCraft_V1_0_6_Alpha.game.state.GameState;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.display.Display;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ChoiceCraft game.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class ChoiceCraft {

    public static final int SPRITE_SIZE = 64;

    private final Display display;
    private final KeyboardInput keyboardInput;
    private State state;

    /**
     * Construct ChoiceCraft game.
     * <p>Precondition: input dimension that is positive for both width and height.</p>
     * <p>Postcondition: initialize ChoiceCraft game with desired width and height.</p>
     *
     * @param width  that is a positive integer.
     * @param height that is a positive integer.
     */
    public ChoiceCraft(int width, int height) {
        this.keyboardInput = new KeyboardInput();
        this.display = new Display(width, height, keyboardInput);
        this.state = new GameState(new Size(width, height), this.keyboardInput);
    }

    /**
     * Update ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft game once.</p>
     */
    public void update() {
        state.update();
    }

    /**
     * Render ChoiceCraft game through Display class.
     * <p>Precondition: input ChoiceCraft game is not null.</p>
     * <p>Postcondition: renders ChoiceCraft game.</p>
     */
    public void render() {
        display.render(state);
    }
}
