/**
 * ========================================================================================================================
 * UI Image.
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.state.State;

import java.awt.*;

/**
 * UI Image.
 *
 * @author David Liu.
 * @since 3/28/2026
 */
public class UIImage extends UIComponent{

    private Image image;

    public UIImage(Image image) {
        this.image = image;
        size = new Size(image.getWidth(null), image.getHeight(null));
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
        return image;
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

    }
}
