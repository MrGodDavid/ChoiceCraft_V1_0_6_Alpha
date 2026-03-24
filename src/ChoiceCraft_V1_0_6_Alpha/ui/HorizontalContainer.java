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
public class HorizontalContainer extends UIContainer {

    public HorizontalContainer(Size windowSize) {
        super(windowSize);
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
    @Override
    protected Size calculateContentSize() {
        int childrenWidth = 0;
        int tallestChildHeight = 0;

        for (UIComponent child : children) {
            childrenWidth += child.getSize().getWidth() + child.getMargin().getHorizontal();

            if (child.getSize().getHeight() > tallestChildHeight) {
                tallestChildHeight = child.getSize().getHeight();
            }
        }
        return new Size(childrenWidth, tallestChildHeight);
    }

    /**
     * Calculate each child's position in UIContainer. Calculation method specified by subclasses' implementation
     * of this method.
     * <p>Precondition: none.</p>
     * <p>Postcondition: calculate each child's position in its parent container.</p>
     */
    @Override
    protected void calculateChildrenPositions() {
        int currentX = padding.getLeft();
        for (UIComponent child : children) {
            currentX += child.getMargin().getLeft();
            child.setRelativePosition(new Position(currentX, padding.getTop()));
            child.setAbsolutePosition(new Position(currentX + absolutePosition.intX(), padding.getTop() + absolutePosition.intY()));
            currentX += child.getSize().getWidth();
            currentX += child.getMargin().getRight();
        }
    }
}
