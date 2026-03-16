/**
 * ========================================================================================================================
 * Player of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Player class.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class Player extends GameObject {

    private final Controller controller;

    public Player(Controller controller) {
        super();
        this.controller = controller;
    }

    /**
     * Update game object of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft every frame.
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     */
    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;

        if (controller.isRequestingUp()) {
            deltaY--;
        }
        if (controller.isRequestingDown()) {
            deltaY++;
        }
        if (controller.isRequestingLeft()) {
            deltaX--;
        }
        if (controller.isRequestingRight()) {
            deltaX++;
        }
        position = new Position(position.getX() + deltaX, position.getY() + deltaY);
    }

    /**
     * Game objects of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft each provide sprite (graphic) to rendering system
     * {@link Display ChoiceCraft_V1_0_6_Alpha.Display class}
     * <p>Precondition: game object exists in ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.</p>
     * <p>Postcondition: return the sprite of game object of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft</p>
     *
     * @return the sprite of game object of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.
     */
    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, size.getWidth(), size.getHeight());
        g.dispose();

        return image;
    }
}
