/**
 * ========================================================================================================================
 * UIContainer class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
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

    protected Alignment alignment;
    protected Size windowSize;
    protected Color backgroundColor;

    protected Size fixedSize;

    public UIContainer(Size windowSize) {
        super();
        this.windowSize = windowSize;
        alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        backgroundColor = new Color(0, 0, 0, 0);
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
        size = (fixedSize != null)
                ? fixedSize
                : new Size(
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
        int x = padding.getLeft();
        if (alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
            x = (windowSize.getWidth() - size.getWidth()) / 2;
        }
        if (alignment.getHorizontal().equals(Alignment.Position.END)) {
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }

        int y = padding.getTop();
        if (alignment.getVertical().equals(Alignment.Position.CENTER)) {
            y = (windowSize.getHeight() - size.getHeight()) / 2;
        }
        if (alignment.getVertical().equals(Alignment.Position.END)) {
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        this.relativePosition = new Position(x, y);
        if (parent == null) {
            this.absolutePosition = new Position(x, y);
        }
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
                    child.getRelativePosition().intX(),
                    child.getRelativePosition().intY(),
                    null
            );
        }

        g2d.dispose();
        return image;
    }

    /**
     * Update the UIComponents in ChoiceCraft. Iterate and then update each child component in the list of
     * UIComponent children.
     * <p>Precondition: none.</p>
     * <p>Postcondition: UIComponent subclasses each behaves the way defined by the implementation of this method.</p>
     *
     * @param state that is the current game ChoiceCraft_V1_0_6_Alpha.state.
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
        child.setParent(this);
    }

    public void setBackground(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setFixedSize(Size fixedSize) {
        this.fixedSize = fixedSize;
    }
}
