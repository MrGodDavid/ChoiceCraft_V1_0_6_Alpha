/**
 * ========================================================================================================================
 * Superclass for all game objects of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.display.Display;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;

import java.awt.*;

/**
 * Superclass for all game objects of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public abstract class GameObject {

    protected Position position;
    protected Size size;

    /**
     * Default no-arg constructor.
     * <p>
     * Initializes position and size component of game object.
     * <li>Default <code>position</code> component: (x: 50, y: 50) </li>
     * <li>Default <code>size</code> component: (width: 50, height: 50) </li>
     * </p>
     */
    public GameObject() {
        position = new Position(50, 50);
        size = new Size(50, 50);
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     *
     * @param state current state that is not null.
     */
    public abstract void update(State state);

    /**
     * Game objects of ChoiceCraft each provide sprite (graphic) to rendering system
     * {@link Display Display class}
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the sprite of game object of ChoiceCraft</p>
     *
     * @return the sprite of game object of ChoiceCraft.
     */
    public abstract Image getSprite();

    /**
     * Game objects of ChoiceCraft each provide collision box to physics detecting methods.
     * {@link CollisionBox CollisionBox class}
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the calculated collision box of game object of ChoiceCraft</p>
     *
     * @return the calculated collision box of game object of ChoiceCraft.
     */
    public abstract CollisionBox getCollisionBox();

    /**
     * Wrapper method of {@link ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox#collidesWith(CollisionBox)}.
     * Check if two game objects collide each other.
     * <p>Precondition: other game object is not null.</p>
     * <p>Postcondition: return true based on subclass's implementation of this method.</p>
     *
     * @param other that is not null.
     * @return true based on subclass's implementation of this method, false in opposite conditions.
     */
    public abstract boolean collidesWith(GameObject other);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }
}
