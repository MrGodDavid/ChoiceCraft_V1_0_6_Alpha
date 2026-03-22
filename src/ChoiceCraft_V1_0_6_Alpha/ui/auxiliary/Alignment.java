/**
 * ========================================================================================================================
 * Alignment for UIComponent.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.auxiliary;

/**
 * Alignment of UIComponent in window.
 * <p> Alignment can be:
 * <li>START (alignmentX -> LEFT), (alignmentY -> TOP)</li>
 * <li>CENTER</li>
 * <li>END (alignmentX -> RIGHT), (alignmentY -> BOTTOM)</li>
 * </p>
 *
 * @author David Liu
 * @since 3/21/2026
 */
@SuppressWarnings("all")
public final class Alignment {

    public enum Position {
        START, CENTER, END;
    }

    private final Position horizontal;
    private final Position vertical;

    public Alignment(Position horizontal, Position vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Position getHorizontal() {
        return horizontal;
    }

    public Position getVertical() {
        return vertical;
    }
}
