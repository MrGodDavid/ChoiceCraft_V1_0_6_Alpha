/**
 * ========================================================================================================================
 * State class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.state;

import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.Player;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclass for all states in ChoiceCraft. (i.e. PlayState, DebugState, MenuState, etc.)
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public abstract class State {

    protected final List<GameObject> gameObjects;
    protected final SpriteLibrary spriteLibrary;
    protected final KeyboardInput keyboardInput;

    private ChoiceCraftMap gameMap;

    public State(KeyboardInput keyboardInput) {
        this.gameObjects = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.keyboardInput = keyboardInput;
        this.gameMap = new ChoiceCraftMap(new Size(20, 20), spriteLibrary);
    }

    /**
     * Update state in ChoiceCraft multiple times per frame. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update State once.</p>
     */
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ChoiceCraftMap getGameMap() {
        return gameMap;
    }
}
