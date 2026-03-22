/**
 * ========================================================================================================================
 * Collision box component of game objects of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

import java.awt.*;

/**
 * Collision box component of game objects of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public final class CollisionBox {

    private Rectangle collisionBox;

    public CollisionBox(Rectangle collisionBox) {
        this.collisionBox = collisionBox;
    }

    public static CollisionBox of(Position position, Size size) {
        return new CollisionBox(new Rectangle(position.intX(), position.intY(), size.getWidth(), size.getHeight()));
    }

    /**
     * Check if two collision boxes are colliding with each other. This is a wrapper method of
     * {@link java.awt.Rectangle#intersects(Rectangle)}
     * <p>Precondition: other rectangle is not null.</p>
     * <p>Postcondition: return true if two collision boxes collide each other.</p>
     *
     * @param other that is not null.
     * @return true if two collision boxes collide each other, false otherwise.
     */
    public boolean collidesWith(CollisionBox other) {
        return this.collisionBox.intersects(other.getBound());
    }

    public Rectangle getBound() {
        return collisionBox;
    }

    @Override
    public String toString() {
        return "CollisionBox{" + "collisionBox=" + collisionBox + '}';
    }
}
