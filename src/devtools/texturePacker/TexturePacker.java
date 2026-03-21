package devtools.texturePacker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * TexturePacker.
 *
 * @author Mr. GodDavid
 * @since 3/14/2026
 */
public class TexturePacker {

    private final ArrayList<BufferedImage> sprites;
    private final String directory;

    private final ENTITY_NAME name;
    private final ANIMATION_STATE state;

    private File[] files;

    public TexturePacker(String directory, ENTITY_NAME entityName, ANIMATION_STATE animationState) {
        this.directory = directory;
        this.name = entityName;
        this.state = animationState;
        sprites = new ArrayList<>();
        files = null;
    }

    public void pack() {
        System.out.println(directory);
        File file = new File(directory);
        files = file.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files in directory: " + directory);
            return;
        }

        Arrays.sort(files, Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
        for (File f : files) {
            if (f.isFile() && isImageFile(f.getName().toLowerCase())) {
                try {
                    BufferedImage image = ImageIO.read(f);
                    if (image != null) {
                        sprites.add(image);
                        System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Loaded: " + f.getName() + "]");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(2, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 12));
        panel.setBackground(Color.BLACK);

        JLabel[] labels = new JLabel[sprites.size()];
        for (int i = 0; i < sprites.size(); i++) {
            labels[i] = new JLabel();
            ImageIcon icon = new ImageIcon(sprites.get(i));
            labels[i].setIcon(icon);
            panel.add(labels[i]);
        }
        window.add(panel);

        JButton packButton = new JButton("Pack");
        packButton.setBackground(Color.BLACK);
        packButton.setOpaque(true);
        packButton.addActionListener(this::processPackButtonAction);

        window.add(packButton);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        System.out.println("\n=============================[FINISHED READING]=============================");
        System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Loaded " + sprites.size() + " images]");
    }

    private void processPackButtonAction(ActionEvent e) {
        BufferedImage first = sprites.get(0);
        if (first == null) return;
        System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Packing all " + sprites.size() + " images]");

        int width = first.getWidth();
        int height = first.getHeight();
        BufferedImage packedImage = new BufferedImage(width * 12, height * 8, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = packedImage.createGraphics();

        int index = 0;
        int x = 0, y = 0;
        for (int row = 0; row < 8; row++) {
            x = 0;
            for (int col = 0; col < 12; col++) {
                g2d.drawImage(sprites.get(index), x, y, null);
                x += width;
                index++;
            }
            y += height;
        }
        String outputName = name.getKey() + "_" + state.getKey() + "_8dir_spritesheet.png";
        String outputDirectory = "resources/graphics_output/" + outputName;
        try {
            File output = new File(outputDirectory);
            if (!ImageIO.write(packedImage, "png", output)) {
                throw new RuntimeException(outputDirectory + " could not be created");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        g2d.dispose();
        System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Packed all " + sprites.size() + " images]");

        System.out.println("\n=============================[DELETING... ]=============================");
        for (File f : files) {
            if (f.delete()) {
                System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Deleted: " + f.getName() + "]");
            } else {
                System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Failed to delete: " + f.getName() + "]");
            }
        }
        System.out.println("[ChoiceCraft V1.0.5 Alpha TexturePacker]: [Deleted all " + sprites.size() + " images]");
        System.exit(0);
    }

    private boolean isImageFile(String fileName) {
        return fileName.endsWith(".png");
    }

    public enum ENTITY_NAME {
        PLAYER,
        ZOMBIE_BASIC,
        ENCHANTER;

        private final String key;

        ENTITY_NAME() {
            this.key = name().toLowerCase();
        }

        public String getKey() {
            return key;
        }
    }

    public enum ANIMATION_STATE {
        IDLE,
        WALKING,
        ATTACKING,
        ATTACKING_ARCHERY_OAK_BOW,
        ATTACKING_FIST,
        ATTACKING_SHORT_IRON_SWORD,
        GREETING,
        HURTING;

        private final String key;

        ANIMATION_STATE() {
            this.key = name().toLowerCase();
        }

        public String getKey() {
            return key;
        }
    }

    public static void main(String[] args){
        String directory = "src/asset_raw/";
            TexturePacker texturePacker = new TexturePacker(directory, ENTITY_NAME.ENCHANTER, ANIMATION_STATE.GREETING);
        texturePacker.pack();
    }
}