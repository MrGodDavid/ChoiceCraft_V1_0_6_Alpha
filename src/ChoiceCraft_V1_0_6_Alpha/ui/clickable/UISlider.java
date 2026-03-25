/**
 * ========================================================================================================================
 * UISlider of ChoiceCraft GUI.
 * <p>
 * Author: David Liu.                                                                                   Date:3/25/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;
import ChoiceCraft_V1_0_6_Alpha.state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the subclass of {@link UIClickable}.
 *
 * @author David Liu.
 * @since 3/25/2026
 */
public class UISlider extends UIClickable {

    private double value;
    private double min;
    private double max;

    public UISlider(double min, double max) {
        this.min = min;
        this.max = max;
        this.value = max;
        this.size = new Size(360, 10);
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
        this.value = getValueAt(state.getInput().getCursorPosition().getX());
    }

    /**
     * Get the sprite from UIComponents. {@link Renderer} will render the sprites via
     * {@link Renderer#render(State, Graphics)}.
     * <p>Precondition: none.</p>
     * <p>Postcondition: get the sprite from any subclasses of UIComponent. Any subclasses of {@link ChoiceCraft_V1_0_6_Alpha.ui.UIComponent} should
     * implement this method.</p>
     *
     * @return the sprite from any subclasses of UIComponent.
     */
    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D g2d = sprite.createGraphics();

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, size.getWidth(), size.getHeight());

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getPixelsOfCurrentValue(), size.getHeight());
        g2d.dispose();
        return sprite;
    }

    private int getPixelsOfCurrentValue() {
        double range = max - min;
        double currentValueAmount = value - min;
        return (int) ((currentValueAmount / range) * size.getWidth());
    }

    private double getValueAt(double xPosition) {
        double positionOnSlider = xPosition - absolutePosition.getX();
        double percentage = positionOnSlider / size.getWidth();
        double range = max - min;
        return min + range * percentage;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
