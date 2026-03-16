/**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game;

import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.display.Display;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class ChoiceCraft {

    private final Display display;
    private final List<GameObject> gameObjects;
    private final KeyboardInput keyboardInput;

    /**
     * Construct ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game.
     * <p>Precondition: input dimension that is positive for both width and height.</p>
     * <p>Postcondition: initialize ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game with desired width and height.</p>
     *
     * @param width  that is a positive integer.
     * @param height that is a positive integer.
     */
    public ChoiceCraft(int width, int height) {
        this.keyboardInput = new KeyboardInput();
        this.display = new Display(width, height, keyboardInput);
        this.gameObjects = new ArrayList<>();

        gameObjects.add(new Player(new PlayerController(keyboardInput)));
    }

    /**
     * Update ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once.</p>
     */
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    /**
     * Render ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once. Call this method multiple times in one second. (FPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: render ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once.</p>
     */
    public void render() {
        display.render(this);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
