/**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.Display class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * ChoiceCraft_V1_0_6_Alpha.Display ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game window.
 *
 * @author David Liu.
 * @since 3/15/2026
 */
public final class Display extends JFrame {

    private final Canvas canvas;

    /**
     * Initializes ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game window. Creates a JFrame as game window. Sets the title, dimension, closing operation,
     * resizeable, opening location, and visibility of game window. Construct a canva as paint component of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft
     * game.
     * <p>Precondition: window width and height are positive numbers.</p>
     * <p>Postcondition: creates ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game window with root paint component.</p>
     *
     * @param width  that is a positive integer.
     * @param height that is a positive integer.
     */
    public Display(int width, int height) {
        super.setTitle("ChoiceCraft_V1_0_6_Alpha.ChoiceCraft V1.0.6 Alpha");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        super.add(canvas);
        super.pack();

        canvas.createBufferStrategy(3);

        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    /**
     * Render ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game through ChoiceCraft_V1_0_6_Alpha.Display class.
     * <p>Precondition: input ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game is not null.</p>
     * <p>Postcondition: renders ChoiceCraft_V1_0_6_Alpha.ChoiceCraft game.</p>
     *
     * @param choiceCraft that is not null.
     */
    public void render(ChoiceCraft choiceCraft) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (GameObject gameObject : choiceCraft.getGameObjects()) {
            g.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().getX(),
                    gameObject.getPosition().getY(),
                    null
            );
        }

        g.dispose();
        bufferStrategy.show();
    }
}
