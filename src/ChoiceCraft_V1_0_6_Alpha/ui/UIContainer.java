/**
 * ========================================================================================================================
 * UIContainer class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains multiple {@link UIComponent} elements.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public class UIContainer extends UIComponent {

    private Color backgroundColor;

    public UIContainer() {
        super();
        backgroundColor = Color.RED;

        calculateSize();
        calculatePosition();
    }

    /**
     * Get the sprite from UIComponents. {@link Renderer} will render the sprites via
     * {@link Renderer#render(State, Graphics)}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the sprite from any subclasses of UIComponent. Any subclasses of {@link UIComponent} should
     * implement this method.</p>
     *
     * @return the sprite from any subclasses of UIComponent.
     */
    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, size.getWidth(), size.getHeight());
        g2d.dispose();
        return image;
    }

    /**
     * Update the UIComponents in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: UIComponent subclasses each behaves the way defined by the implementation of this method.</p>
     *
     * @param state that is the current game state.
     */
    @Override
    public void update(State state) {
        calculateSize();
        calculatePosition();
    }

    private void calculateSize() {
        size = new Size(padding.getHorizontal(), padding.getVertical());
    }

    private void calculatePosition() {
        position = new Position(margin.getLeft(), margin.getTop());
    }
}
