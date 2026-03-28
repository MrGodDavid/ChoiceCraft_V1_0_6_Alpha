/**
 * ========================================================================================================================
 * Minimap graphics of ChoiceCraft GUI.
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.ui.clickable;

import ChoiceCraft_V1_0_6_Alpha.display.Camera;
import ChoiceCraft_V1_0_6_Alpha.display.Renderer;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.ImageUtils;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.UIComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the subclass of {@link UIClickable}. Minimap graphics of ChoiceCraft GUI.
 *
 * @author David Liu.
 * @since 3/28/2026
 */
public final class UIMinimap extends UIClickable {

    private double ratio;
    private Rectangle cameraViewBounds;
    private BufferedImage mapImage;
    private Color color;

    public UIMinimap(ChoiceCraftMap gameMap) {
        size = new Size(128, 128);
        cameraViewBounds = new Rectangle();
        color = Color.GRAY;

        calculateRatio(gameMap);
        generateMap(gameMap);
    }

    private void generateMap(ChoiceCraftMap gameMap) {
        mapImage = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D g2d = mapImage.createGraphics();

        int pixelsPerGrid = (int) Math.round(ChoiceCraft.SPRITE_SIZE * ratio);
        for (int row = 0; row < gameMap.getTiles().length; row++) {
            for (int col = 0; col < gameMap.getTiles()[0].length; col++) {
                g2d.drawImage(
                        gameMap.getTiles()[row][col].getSprite().getScaledInstance(pixelsPerGrid, pixelsPerGrid, 0),
                        row * pixelsPerGrid + (size.getWidth() - gameMap.getTiles().length * pixelsPerGrid) / 2,
                        col * pixelsPerGrid + (size.getHeight() - gameMap.getTiles()[0].length * pixelsPerGrid) / 2,
                        null
                );
            }
        }
        g2d.dispose();
    }

    private void calculateRatio(ChoiceCraftMap gameMap) {
        ratio = Math.min(
                size.getWidth() / (double) gameMap.getWidth(),
                size.getHeight() / (double) gameMap.getHeight()
        );
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
        Camera camera = state.getCamera();
        cameraViewBounds = new Rectangle(
                (int) (camera.getPosition().getX() * ratio),
                (int) (camera.getPosition().getY() * ratio),
                (int) (camera.getSize().getWidth() * ratio),
                (int) (camera.getSize().getHeight() * ratio)
        );

        color = Color.GRAY;
        if (hasFocus) {
            color = Color.WHITE;
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
        Position cursorPosition = Position.copyOf(state.getInput().getCursorPosition());
        cursorPosition.subtract(absolutePosition);
        state.getCamera().setPosition(new Position(
                cursorPosition.getX() / ratio - cameraViewBounds.getSize().getWidth() / 2,
                cursorPosition.getY() / ratio - cameraViewBounds.getSize().getHeight() / 2
        ));
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
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D g2d = sprite.createGraphics();

        g2d.drawImage(mapImage, 0, 0, null);
        g2d.setColor(color);
        g2d.drawRect(0, 0, size.getWidth() - 1, size.getHeight() - 1);
        g2d.draw(cameraViewBounds);

        g2d.dispose();
        return sprite;
    }
}
