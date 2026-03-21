/**
 * ========================================================================================================================
 * Spacing for UIComponent.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.auxiliary;

/**
 * Spacing of UIComponents. A spacing can be a margin or a padding of UIComponent.
 * <p> Spacing can be:
 * <li>top</li>
 * <li>right</li>
 * <li>bottom</li>
 * <li>left</li>
 * </p>
 *
 * @author David Liu
 * @since 3/21/2026
 */
public final class Spacing {

    private int top, right, bottom, left;

    public Spacing(int spacing) {
        this(spacing, spacing);
    }

    public Spacing(int horizontal, int vertical) {
        this(vertical, horizontal, vertical, horizontal);
    }

    public Spacing(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * Convenience getter of {@link Spacing}. Calculate the total vertical spacing of {@link Spacing}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Calculate the total vertical spacing of {@link Spacing}.</p>
     *
     * @return the total vertical spacing of {@link Spacing}.
     */
    public int getVertical() {
        return top + bottom;
    }

    /**
     * Convenience getter of {@link Spacing}. Calculate the total horizontal spacing of {@link Spacing}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Calculate the total horizontal spacing of {@link Spacing}.</p>
     *
     * @return the total horizontal spacing of {@link Spacing}.
     */
    public int getHorizontal() {
        return left + right;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }
}
