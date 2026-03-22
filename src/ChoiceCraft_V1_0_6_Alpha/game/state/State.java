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
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Superclass for all states in ChoiceCraft. (i.e. PlayState, DebugState, MenuState, etc.)
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public abstract class State {

    protected final List<GameObject> gameObjects;
    protected final List<UIContainer> uiContainers;
    protected final SpriteLibrary spriteLibrary;
    protected final KeyboardInput keyboardInput;

    protected ChoiceCraftMap gameMap;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, KeyboardInput keyboardInput) {
        this.gameObjects = new ArrayList<>();
        this.uiContainers = new ArrayList<>();
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
        time.update();
        sortObjectsByPosition();
        for (GameObject gameObject : gameObjects) {
            gameObject.update(this);
        }
        for (UIContainer uiContainer : uiContainers) {
            uiContainer.update(this);
        }
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    /**
     * Wrapper method of getRandomPosition() in {@link ChoiceCraftMap#getRandomPosition()}.
     * <p>Precondition: see ChoiceCraftMap getRandomPosition()</p>.
     * <p>Postcondition: generate a random position.</p>
     *
     * @return a random position.
     */
    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    /**
     * GameObjects is a list of {@link GameObject}. First create a filter in the stream from {@link Collection#stream()}.
     * Custom filter is defined as <p> <code>(other -> other.collidesWith(gameObject)) </code> </p>. Finally, use the
     * {@link java.util.stream.Stream#collect(Collector)} method to convert the collection to a {@link List}.
     * <p>Precondition: param game object must exist in ChoiceCraft.</p>
     * <p>Postcondition: return a list of game objects that are colliding with the param game object.</p>
     *
     * @param gameObject that is not null.
     * @return a list of game objects that are colliding with the param game object.
     */
    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream().filter(other -> other.collidesWith(gameObject)).collect(Collectors.toList());
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

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }
}
