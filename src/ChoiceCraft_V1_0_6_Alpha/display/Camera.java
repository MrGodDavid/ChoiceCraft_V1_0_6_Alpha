/**
 * ========================================================================================================================
 * ChoiceCraft game camera.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;

import java.awt.*;
import java.util.Optional;

/**
 * ChoiceCraft game camera.
 *
 * @author David Liu
 * @since 3/18/2026
 */
public final class Camera {

    private static final int SAFETY_SPACE = ChoiceCraft.SPRITE_SIZE / 2;

    private Position position;
    private Size windowSize;
    private Rectangle viewBound;

    private Optional<GameObject> objectWithFocus; // we don't have to center camera on object.

    public Camera(Size windowSize) {
        this.position = new Position(0, 0);
        this.windowSize = windowSize;
        this.objectWithFocus = Optional.empty();

        calculateViewBound();
    }

    private void calculateViewBound() {
        viewBound = new Rectangle(
                position.intX(),
                position.intY(),
                windowSize.getWidth() + SAFETY_SPACE,
                windowSize.getHeight() + SAFETY_SPACE
        );
    }

    /**
     * Set the camera to focus on a specific {@link GameObject gameObject} of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: camera focuses on new game object</p>
     *
     * @param object new game object that the camera will focus on.
     */
    public void focusOn(GameObject object) {
        this.objectWithFocus = Optional.of(object);
    }

    /**
     * Update ChoiceCraft camera based on current game ChoiceCraft_V1_0_6_Alpha.state.
     * <p>Precondition: current ChoiceCraft_V1_0_6_Alpha.state is not null.</p>
     * <p>Postcondition: update ChoiceCraft camera based on current game ChoiceCraft_V1_0_6_Alpha.state.</p>
     *
     * @param state current ChoiceCraft game ChoiceCraft_V1_0_6_Alpha.state that is not null.
     */
    public void update(State state) {
        if (objectWithFocus.isPresent()) {
            Position objectPosition = objectWithFocus.get().getPosition();

            this.position.setX(objectPosition.getX() - (double) windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - (double) windowSize.getHeight() / 2);

            clampWithinBounds(state);
            calculateViewBound();
        }
    }

    private void clampWithinBounds(State state) {
        if (position.getX() < 0d) position.setX(0);
        if (position.getY() < 0d) position.setY(0);
        if ((position.getX() + windowSize.getWidth()) > state.getGameMap().getWidth())
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        if ((position.getY() + windowSize.getHeight()) > state.getGameMap().getHeight())
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
    }

    public boolean isInView(GameObject gameObject) {
        return viewBound.intersects(
                gameObject.getPosition().intX(),
                gameObject.getPosition().intY(),
                gameObject.getSize().getWidth(),
                gameObject.getSize().getHeight()
        );
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return windowSize;
    }

}
