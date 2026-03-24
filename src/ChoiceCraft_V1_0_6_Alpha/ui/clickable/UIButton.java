/**
 * ========================================================================================================================
 * UIButton of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.ui.UIComponent;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;

import java.awt.*;

/**
 * A subclass of {@link UIClickable}. Button graphics of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public class UIButton extends UIClickable {

    private UIContainer container;
    private UIText label;

    private Runnable clickEvent;

    public UIButton(String label, Runnable clickEvent) {
        this.clickEvent = clickEvent;
        this.label = new UIText(label);

        container = new VerticalContainer(new Size(0, 0));
        container.addUIComponent(this.label);
        container.setFixedSize(new Size(150, 36));
    }

    /**
     * Update the UIComponents in ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: UIComponent subclasses each behaves the way defined by the implementation of this method.</p>
     *
     * @param state that is the current game ChoiceCraft_V1_0_6_Alpha.state.
     */
    @Override
    public void update(State state) {
        super.update(state);
        container.update(state);
        size = container.getSize();

        Color color = Color.GRAY;
        if (hasFocus) color = Color.LIGHT_GRAY;
        if (isPressed) color = Color.DARK_GRAY;

        container.setBackground(color);
    }

    /**
     * Define the click function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onClick() that is
     * specified by user.</p>
     */
    @Override
    protected void onClick() {
        clickEvent.run();
    }

    /**
     * Get the sprite from UIComponents. {@link Renderer} will render the sprites via
     * {@link Renderer#render(State, Graphics)}
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the sprite from any subclasses of UIComponent. Any subclasses of {@link UIComponent} should
     * implement this method.</p>
     *
     * @return the sprite from any subclasses of UIComponent.
     */
    @Override
    public Image getSprite() {
        return container.getSprite();
    }
}
