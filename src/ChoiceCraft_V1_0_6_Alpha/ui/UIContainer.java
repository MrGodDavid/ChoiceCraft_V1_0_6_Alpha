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
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains multiple {@link UIComponent} elements.
 *
 * @author David Liu
 * @since 3/21/2026
 */
public abstract class UIContainer extends UIComponent {

    protected final List<UIComponent> children;
    protected Color backgroundColor;

    public UIContainer() {
        super();
        backgroundColor = Color.RED;
        margin = new Spacing(5);
        padding = new Spacing(5);
        children = new ArrayList<>();

        calculateSize();
        calculatePosition();
    }

    /**
     * Calculate the size of final parent container. Calculation method specified in subclasses' implementation
     * of this method.
     * <p>UIContainer has two varies. {@link HorizontalContainer} and {@link VerticalContainer}</p>
     * <p>Precondition: none.</p>
     * <p>Postcondition: calculate the size of final parent container.</p>
     *
     * @return the size of final parent container.
     */
    protected abstract Size calculateContentSize();

    private void calculateSize() {
        calculateContentSize();
        size = new Size(
                padding.getHorizontal() + calculateContentSize().getWidth(),
                padding.getVertical() + calculateContentSize().getHeight()
        );
    }

    /**
     * Calculate each child's position in UIContainer. Calculation method specified by subclasses' implementation
     * of this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: calculate each child's position in its parent container.</p>
     */
    protected abstract void calculateChildrenPositions();

    private void calculatePosition() {
        position = new Position(margin.getLeft(), margin.getTop());
        calculateChildrenPositions();
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

        for (UIComponent child : children) {
            g2d.drawImage(
                    child.getSprite(),
                    child.getPosition().intX(),
                    child.getPosition().intY(),
                    null
            );
        }

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
        for (UIComponent child : children) {
            child.update(state);
        }
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent child) {
        children.add(child);
    }

    public void setBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
