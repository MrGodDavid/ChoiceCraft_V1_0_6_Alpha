/**
 * ========================================================================================================================
 * UITileToggle class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/29/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;
import ChoiceCraft_V1_0_6_Alpha.input.mouse.action.TilePlacer;
import ChoiceCraft_V1_0_6_Alpha.map.Tile;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.UIComponent;
import ChoiceCraft_V1_0_6_Alpha.ui.UIImage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the subclass of {@link UIClickable}. Tiles that can be toggled by user.
 *
 * @author David Liu.
 * @since 3/29/2026
 */
public final class UITileToggle extends UIClickable {

    private final TilePlacer tilePlacer;

    private UIImage image;
    private BufferedImage activeSprite;
    private boolean active;

    public UITileToggle(Tile tile) {
        image = new UIImage(tile.getSprite().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
        tilePlacer = new TilePlacer(tile);
        size = image.getSize();
        generateActiveSprites();
    }

    private void generateActiveSprites() {
        activeSprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D g2d = activeSprite.createGraphics();

        g2d.drawImage(image.getSprite(), 0, 0, null);

        g2d.setColor(new Color(255, 255, 255, 75));
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.fillRect(0, 0, size.getWidth(), size.getHeight());

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2f));
        g2d.drawRect(1, 1, size.getWidth() - 2, size.getHeight() - 2);

        g2d.dispose();

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
        active = state.getMouseHandler().getPrimaryButtonAction().equals(tilePlacer);
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
     * Define the click function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onClick() that is
     * specified by user.</p>
     *
     * @param state that is not null.
     */
    @Override
    public void onClick(State state) {
        state.getMouseHandler().setPrimaryButtonAction(tilePlacer);
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
    public void onDrag(State state) {

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
        return active ? activeSprite : image.getSprite();
    }
}
