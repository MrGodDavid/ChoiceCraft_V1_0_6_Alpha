/**
 * ========================================================================================================================
 * ChoiceCraft game.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game;

import ChoiceCraft_V1_0_6_Alpha.controller.GameController;
import ChoiceCraft_V1_0_6_Alpha.game.settings.ChoiceCraftSettings;
import ChoiceCraft_V1_0_6_Alpha.state.game.GameState;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.display.Display;

/**
 * ChoiceCraft game.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class ChoiceCraft {

    public static final int SPRITE_SIZE = 64;

    private final Display display;
    private final Input input;
    private final ChoiceCraftSettings settings;
    private final GameController gameController;
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
        this.input = new Input();
        this.display = new Display(width, height, input);
        this.settings = new ChoiceCraftSettings(false);
        this.gameController = new GameController(input);
        this.state = new GameState(new Size(width, height), this.input);
    }

    /**
     * Update ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft game once.</p>
     */
    public void update() {
        state.update();
        gameController.update(this);
    }

    /**
     * Render ChoiceCraft game through Display class.
     * <p>This is the wrapper method of {@link Display#render(State, boolean)}.</p>
     * <p>Precondition: input ChoiceCraft game is not null.</p>
     * <p>Postcondition: renders ChoiceCraft game.</p>
     */
    public void render() {
        display.render(state, settings.isDebugMode());
    }

    public ChoiceCraftSettings getSettings() {
        return settings;
    }
}
