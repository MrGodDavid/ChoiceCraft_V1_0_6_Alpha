/**
 * ========================================================================================================================
 * Image utility class of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/17/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Image utility class of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public final class ImageUtils {

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
            return ImageIO.read(ImageUtils.class.getResource(filePath));
        } catch (IOException e) {
            System.out.println("Failed to load image from file: " + filePath);
        }
        return null;
    }
}
