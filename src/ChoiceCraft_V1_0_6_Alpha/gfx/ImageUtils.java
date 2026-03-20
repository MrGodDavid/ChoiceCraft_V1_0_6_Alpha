/**
 * ========================================================================================================================
 * Image utility class of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/17/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Image utility class of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public final class ImageUtils {

    public static final int ALPHA_OPAQUE = 1;
    public static final int ALPHA_BIT_MASKED = 2;
    public static final int ALPHA_BLEND = 3;

    /**
     * Load image from input file path.
     * <p>Precondition: input file path is not null.</p>
     * <p>Postcondition: load the image file from the file path, return a null Image if ChoiceCraft does not find image
     * through file path. ChoiceCraft would send a warning message if it fails to load image through file path.</p>
     *
     * @param filePath of the image that is not null.
     * @return an Image through file path. Null if ChoiceCraft cannot find the image through <code>filePath</code>.
     */
    public static Image loadImage(String filePath) {
        try {
            Image imageFromDisk = ImageIO.read(ImageUtils.class.getResource(filePath));
            BufferedImage compatibleImage = (BufferedImage) createCompatibleImage(
                    new Size(imageFromDisk.getWidth(null), imageFromDisk.getHeight(null)),
                    ALPHA_BIT_MASKED
            );

            Graphics2D g2d = compatibleImage.createGraphics();
            g2d.drawImage(imageFromDisk, 0, 0, null);
            g2d.dispose();
            return compatibleImage;
        } catch (IOException e) {
            System.out.println("Failed to load image from file: " + filePath);
        }
        return null;
    }

    public static Image createCompatibleImage(Size size, int transparency) {
        GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice().getDefaultConfiguration();
        return graphicsConfiguration.createCompatibleImage(size.getWidth(), size.getHeight(), transparency);
    }
}
