/**
 * ========================================================================================================================
 * Display class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Display ChoiceCraft game window.
 *
 * @author David Liu.
 * @since 3/15/2026
 */
public final class Display extends JFrame {

    private final Canvas canvas;
    private final Renderer renderer;

    /**
     * Initializes ChoiceCraft game window. Creates a JFrame as game window. Sets the title, dimension, closing operation,
     * resizeable, opening location, and visibility of game window.
     * <p>Construct a canva as paint component of game.ChoiceCraft game.</p>
     * <p>Add {@link KeyboardInput keyboardInput} to keyListener to game window.</p>
     * <p>Precondition: window width and height are positive numbers. The KeyboardInput is not null.</p>
     * <p>Postcondition: creates ChoiceCraft game window with root paint component.</p>
     *
     * @param width         that is a positive integer.
     * @param height        that is a positive integer.
     * @param keyboardInput that is not null.
     */
    public Display(int width, int height, KeyboardInput keyboardInput) {
        super.setTitle("ChoiceCraft V1.0.6 Alpha");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        this.renderer = new Renderer();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);
        super.add(canvas);
        super.addKeyListener(keyboardInput);
        super.pack();

        canvas.createBufferStrategy(3);

        super.setLocationRelativeTo(null);
        super.setVisible(true);
    }

    /**
     * Render ChoiceCraft game through Display class.
     * <p>Precondition: input ChoiceCraft game is not null.</p>
     * <p>Postcondition: renders ChoiceCraft game.</p>
     *
     * @param choiceCraft that is not null.
     */
    public void render(ChoiceCraft choiceCraft) {
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderer.render(choiceCraft, g);

        g.dispose();
        bufferStrategy.show();
    }
}
