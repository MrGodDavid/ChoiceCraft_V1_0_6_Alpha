/**
 * ========================================================================================================================
 * Indicates that Player is selecting an NPC in ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/22/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.CollisionBox;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Indicates that Player is selecting an NPC in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/22/2026
 */
public class SelectionCircle extends GameObject{

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = Color.ORANGE;
        size = new Size(32, 16);
        renderOffset = new Position(size.getWidth() / 2, -6);
        renderOrder = 4;
        collisionBoxOffset = renderOffset;
        initializeSprite();
    }

    private void initializeSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g2d = sprite.createGraphics();

        g2d.setColor(color);
        g2d.fillOval(0, 0, size.getWidth(), size.getHeight());
        g2d.dispose();
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     *
     * @param state current state that is not null.
     */
    @Override
    public void update(State state) {

    }

    /**
     * Game objects of ChoiceCraft each provide sprite (graphic) to rendering system
     * {@link ChoiceCraft_V1_0_6_Alpha.display.Display Display class}
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the sprite of game object of ChoiceCraft</p>
     *
     * @return the sprite of game object of ChoiceCraft.
     */
    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }

    /**
     * Game objects of ChoiceCraft each provide collision box to physics detecting methods.
     * {@link CollisionBox CollisionBox class}
     * <p>Precondition: game object exists in ChoiceCraft.</p>
     * <p>Postcondition: return the calculated collision box of game object of ChoiceCraft</p>
     *
     * @return the calculated collision box of game object of ChoiceCraft.
     */
    @Override
    public CollisionBox getCollisionBox() {
        Position position = getPosition();
        position.subtract(collisionBoxOffset);
        return CollisionBox.of(position, getSize());
    }
}
