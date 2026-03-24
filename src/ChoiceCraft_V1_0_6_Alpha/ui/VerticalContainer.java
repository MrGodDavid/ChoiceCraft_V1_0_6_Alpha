/**
 * ========================================================================================================================
 * Horizontal Container.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */

package ChoiceCraft_V1_0_6_Alpha.ui;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;

/**
 * This is a subclass of {@link UIContainer}.
 * <p>Contains (children) elements horizontally.
 * <a href=https://vaadin.com/docs/latest/components/horizontal-layout>Click here for more information.</a>
 * </p>
 * <p>Vertical Container and Horizontal Container Anatomy:
 * <p></p>
 * <img src=https://www.thedataschool.co.uk/content/images/wordpress/2019/10/PP1-1024x490.png></img>
 * </p>
 *
 * @author David Liu.
 * @since 3/21/2026
 */
public class VerticalContainer extends UIContainer {

    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    /**
     * Calculate the size of final parent container. Calculation method specified in subclasses' implementation
     * of this method.
     * <p>UIContainer has two varies. {@link VerticalContainer} and {@link VerticalContainer}</p>
     * <p>Precondition: none.</p>
     * <p>Postcondition: calculate the size of final parent container.</p>
     *
     * @return the size of final parent container.
     */
    @Override
    protected Size calculateContentSize() {
        int childrenHeight = 0;
        int widestChildWidth = 0;

        for (UIComponent child : children) {
            childrenHeight += child.getSize().getHeight() + child.getMargin().getVertical();

            if (child.getSize().getWidth() > widestChildWidth) {
                widestChildWidth = child.getSize().getWidth();
            }
        }
        return new Size(widestChildWidth, childrenHeight);
    }

    /**
     * Calculate each child's position in UIContainer. Calculation method specified by subclasses' implementation
     * of this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: calculate each child's position in its parent container.</p>
     */
    @Override
    protected void calculateChildrenPositions() {
        int currentY = padding.getTop();
        for (UIComponent child : children) {
            currentY += child.getMargin().getTop();
            child.setRelativePosition(new Position(padding.getLeft(), currentY));
            child.setAbsolutePosition(new Position(padding.getLeft() + absolutePosition.intX(), currentY + absolutePosition.intY()));
            currentY += child.getSize().getHeight();
            currentY += child.getMargin().getBottom();
        }
    }
}
