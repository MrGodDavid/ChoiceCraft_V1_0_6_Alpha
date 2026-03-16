/**
 * ========================================================================================================================
 * Display class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Display ChoiceCraft game window.
 *
 * @author David Liu.
 * @since 3/15/2026
 */
public class Display extends JFrame {

    private final Canvas canvas;

    /**
     * Initializes ChoiceCraft game window. Creates a JFrame as game window. Sets the title, dimension, closing operation,
     * resizeable, opening location, and visibility of game window. Construct a canva as paint component of ChoiceCraft
     * game.
     * <p>Precondition: window width and height are positive numbers.</p>
     * <p>Postcondition: creates ChoiceCraft game window with root paint component.</p>
     *
     * @param width  that is a positive integer.
     * @param height that is a positive integer.
     */
    public Display(int width, int height) {
        super.setTitle("ChoiceCraft V1.0.6 Alpha");
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

    public void render(ChoiceCraft choiceCraft) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.dispose();
        bufferStrategy.show();
    }
}
