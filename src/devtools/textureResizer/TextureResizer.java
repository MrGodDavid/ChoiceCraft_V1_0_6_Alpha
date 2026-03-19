package devtools.textureResizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Resize texture sprite
 *
 * @author Mr. GodDavid
 * @since 3/18/2026
 */
public class TextureResizer {

    private final String inputDir;
    private final String fileName;

    public TextureResizer(String inputDir, String fileName) {
        this.inputDir = inputDir;
        this.fileName = fileName;
    }

    public void resize(int newWidth, int newHeight) {
        File file = new File(inputDir + fileName);
        try {
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            BufferedImage originalImage = ImageIO.read(file);
            Graphics2D g2d = resizedImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            String outputFileName = "resized" + fileName;
            String outputDir = inputDir + outputFileName;
            try {
                File output = new File(outputDir);
                if (!ImageIO.write(resizedImage, "png", output)) {
                    throw new RuntimeException(outputDir + " could not be created");
                }
            }catch (IOException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() + " with path: " + inputDir + fileName);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello, ChoiceCraft's Texture Resizer");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please input file directory:");
        String inputDir = scanner.nextLine();
        System.out.print("Please input file name:");
        String fileName = scanner.nextLine();
        System.out.print("Please input target width: (Cannot be zero or negative!)");
        int width = scanner.nextInt();
        System.out.print("Please input target height: (Cannot be zero or negative!)");
        int height = scanner.nextInt();

        if (width <= 0) {
            System.out.println("[ERROR] width must be greater than zero!");
            System.exit(0);
        }
        if (height <= 0) {
            System.out.println("[ERROR] height must be greater than zero!");
            System.exit(0);
        }

        TextureResizer textureResizer = new TextureResizer(inputDir, fileName);
        textureResizer.resize(width, height);
    }
}
