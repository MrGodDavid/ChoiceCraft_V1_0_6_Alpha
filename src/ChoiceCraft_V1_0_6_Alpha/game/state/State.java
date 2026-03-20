/**
 * ========================================================================================================================
 * State class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.state;

import ChoiceCraft_V1_0_6_Alpha.display.Camera;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.game.Time;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;

import java.util.ArrayList;
import java.util.Comparator;
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

    protected ChoiceCraftMap gameMap;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, KeyboardInput keyboardInput) {
        this.gameObjects = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.keyboardInput = keyboardInput;
        this.camera = new Camera(windowSize);
        this.time = new Time();
    }

    /**
     * Update state in ChoiceCraft multiple times per frame. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update State once.</p>
     */
    public void update() {
        sortObjectsByPosition();
        for (GameObject gameObject : gameObjects) {
            gameObject.update(this);
        }
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public ChoiceCraftMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }
}
