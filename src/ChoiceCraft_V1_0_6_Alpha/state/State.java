/**
 * ========================================================================================================================
 * State class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state;

import ChoiceCraft_V1_0_6_Alpha.audio.AudioPlayer;
import ChoiceCraft_V1_0_6_Alpha.display.Camera;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.Time;
import ChoiceCraft_V1_0_6_Alpha.game.settings.GameSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.input.mouse.MouseHandler;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.map.MapIO;
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
    protected final MouseHandler mouseHandler;
    protected final GameSettings gameSettings;

    protected AudioPlayer audioPlayer;
    protected ChoiceCraftMap gameMap;
    protected Camera camera;
    protected Time time;
    protected Size windowSize;

    private State nextState;

    public State(Size windowSize, Input input, GameSettings gameSettings) {
        this.gameObjects = new ArrayList<>();
        this.uiContainers = new ArrayList<>();
        this.spriteLibrary = new SpriteLibrary();
        this.input = input;
        this.mouseHandler = new MouseHandler();
        this.gameSettings = gameSettings;
        this.audioPlayer = new AudioPlayer(this.gameSettings.getAudioSettings());
        this.windowSize = windowSize;
        this.camera = new Camera(windowSize);
        this.time = new Time();
    }

    /**
     * Update ChoiceCraft_V1_0_6_Alpha.state in ChoiceCraft multiple times per frame. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update State once.</p>
     *
     * @param game ChoiceCraft game reference.
     */
    public void update(ChoiceCraft game) {
        audioPlayer.update();
        time.update();
        sortObjectsByPosition();
        updateGameObjects();
        List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
        mouseHandler.update(this);

        if (nextState != null) {
            game.enterState(nextState);
        }
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
    @SuppressWarnings("unchecked")
    public final <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> filterClass) {
        return gameObjects.stream()
                .filter(filterClass::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public final void spawn(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void cleanUp() {
        audioPlayer.clear();
    }

    public void loadGameMap() {
        gameMap = MapIO.load(spriteLibrary);
    }

    // =============================================== [GETTERS & SETTERS] ===============================================

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

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }
}
