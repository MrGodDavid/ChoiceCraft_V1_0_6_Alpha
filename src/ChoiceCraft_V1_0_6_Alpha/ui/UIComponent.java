/**
 * ========================================================================================================================
 * UIComponent class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/21/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui;

import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

import java.awt.*;

/**
 * Superclass for ChoiceCraft ui classes. (Buttons, panels, etc.)
 * <p>Each UIComponent has padding and margin. See the image below.</p>
 * <img src=https://media.geeksforgeeks.org/wp-content/uploads/20210317151556/marginpadding.png></img>
 * <p>Image from <a href=https://www.geeksforgeeks.org/css/css-padding-vs-margin/>GeeksforGeeks</a></p>
 *
 * @author David Liu
 * @since 3/21/2026
 */
public abstract class UIComponent {

    protected Position position;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    /**
     * Default no-arg constructor.
     * <p>
     * Constructs a default UIComponent that has the following properties:
     * <li>Position - default position is (0, 0).</li>
     * <li>Size - default size is 1px by 1px.</li>
     * <li>Margin - default margin is 0px.</li>
     * <li>Padding - default padding is 0px.</li>
     * </p>
     * <p>For margin and padding, see {@link Spacing}</p>
     */
    public UIComponent() {
        position = new Position(0, 0);
        size = new Size(1, 1);
        margin = new Spacing(0);
        padding = new Spacing(0);
    }

    /**
     * Get the sprite from UIComponents. {@link ChoiceCraft_V1_0_6_Alpha.display.Renderer} will render the sprites via
     * {@link ChoiceCraft_V1_0_6_Alpha.display.Renderer#render(State, Graphics)}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the sprite from any subclasses of UIComponent. Any subclasses of {@link UIComponent} should
     * implement this method.</p>
     *
     * @return the sprite from any subclasses of UIComponent.
     */
    public abstract Image getSprite();

    /**
     * Update the UIComponents in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: UIComponent subclasses each behaves the way defined by the implementation of this method.</p>
     *
     * @param state that is the current game state.
     */
    public abstract void update(State state);

    public Spacing getMargin() {
        return margin;
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    public Spacing getPadding() {
        return padding;
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
