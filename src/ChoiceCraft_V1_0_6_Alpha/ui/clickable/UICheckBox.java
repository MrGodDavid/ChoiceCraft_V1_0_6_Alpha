/**
 * ========================================================================================================================
 * UIButton of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/24/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.game.settings.Setting;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.HorizontalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIComponent;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A subclass of {@link UIClickable}. UICheckbox graphics of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/24/2026
 */
public class UICheckBox extends UIComponent {

    private UIContainer container;

    public UICheckBox(String label, Setting<Boolean> setting) {
        this.container = new HorizontalContainer(new Size(0, 0));
        container.addUIComponent(new CheckBox(setting));
        container.addUIComponent(new UIText(label));
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
        return container.getSprite();
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
        container.update(state);
        size = container.getSize();
        container.setParent(parent);
        container.setAbsolutePosition(absolutePosition);
    }

    private static class CheckBox extends UIClickable {

        private final Setting<Boolean> setting;
        private Color color;

        private CheckBox(Setting<Boolean> setting) {
            this.setting = setting;
            this.size = new Size(20, 20);
            this.color = Color.GRAY;
            this.margin = new Spacing(0, 10, 0, 0);
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
            color = setting.getValue() ? Color.WHITE : Color.GRAY;
            if (hasFocus) {
                color = Color.LIGHT_GRAY;
                if (isPressed) {
                    color = Color.DARK_GRAY;
                }
            }
        }

        /**
         * Define the click function of clickable ui component of ChoiceCraft.
         * <p>Precondition: none.</p>
         * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onClick() that is
         * specified by user.</p>
         *
         * @param state that is not null.
         */
        @Override
        protected void onClick(State state) {
            setting.setValue(!setting.getValue());
        }

        /**
         * Define the drag function of clickable ui component of ChoiceCraft.
         * <p>Precondition: none.</p>
         * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onDrag() that is
         * specified by user.</p>
         *
         * @param state that is not null.
         */
        @Override
        protected void onDrag(State state) {
        }

        /**
         * Define the focusing function of clickable ui component of ChoiceCraft.
         * <p>Precondition: none.</p>
         * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onFocus() that is
         * specified by user.</p>
         *
         * @param state that is not null.
         */
        @Override
        protected void onFocus(State state) {
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
            BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
            Graphics2D g2d = sprite.createGraphics();

            g2d.setColor(color);
            if (setting.getValue()) {
                g2d.fillRect(0, 0, size.getWidth(), size.getHeight());
            } else {
                g2d.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);
            }
            g2d.dispose();
            return sprite;
        }
    }
}
