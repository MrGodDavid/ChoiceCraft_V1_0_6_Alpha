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
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;

import java.util.*;
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
    protected final Input input;

    protected ChoiceCraftMap gameMap;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, Input input) {
        this.gameObjects = new ArrayList<>();
        this.uiContainers = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.input = input;
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
        updateGameObjects();
        for (UIContainer uiContainer : uiContainers) {
            uiContainer.update(this);
        }
        camera.update(this);
        handleMouseInputs();
    }

    private void handleMouseInputs() {
        if (input.isMouseClicked()) {
            System.out.println("Mouse clicked at position: " + input.getCursorPosition());
        }
        input.clearMouseClick();
    }

    private void updateGameObjects() {
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).update(this);
        }
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
    }

    /**
     * Wrapper method of getRandomPosition() in {@link ChoiceCraftMap#getRandomPosition()}.
     * <p>Precondition: see ChoiceCraftMap getRandomPosition()</p>.
     * <p>Postcondition: generate a random position.</p>
     *
     * @return a random position.
     */
    public final Position getRandomPosition() {
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
    public final List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream().filter(other -> other.collidesWith(gameObject)).collect(Collectors.toList());
    }

    /**
     * Select only gameObjects that matched with the filterClass class type from a List of {@code GameObject}.
     * <p>Precondition: filterClass is not null.</p>
     * <p>Postcondition: select a list of gameObject of filterClass type.</p>
     *
     * @param filterClass that is not null.
     * @param <T>         subclass type of {@link GameObject}.
     * @return a list of gameObject of filterClass type.
     */
    public final <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> filterClass) {
        return gameObjects.stream()
                .filter(filterClass::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public final void spawn(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public final List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public final ChoiceCraftMap getGameMap() {
        return gameMap;
    }

    public final Camera getCamera() {
        return camera;
    }

    public final Time getTime() {
        return time;
    }

    public final List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public SpriteLibrary getSpriteLibrary() {
        return spriteLibrary;
    }

    public Input getInput() {
        return input;
    }
}
