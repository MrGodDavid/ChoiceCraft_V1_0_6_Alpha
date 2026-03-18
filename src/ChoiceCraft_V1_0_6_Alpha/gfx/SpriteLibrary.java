/**
 * ========================================================================================================================
 * SpriteLibrary of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/17/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This class looks all resource folders and load all sprites to game.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public class SpriteLibrary {

    private static final String ENTITY_SPRITES_PATH = "/sprites/entity";

    private final Map<String, SpriteSet> gameEntities;
    private final Map<String, Image> tiles;

    /**
     * No-arg constructor of this class.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create a new instance of unit Map, read all sprites from folders, and store them in sprite
     * Map.</p>
     */
    public SpriteLibrary() {
        this.gameEntities = new HashMap<String, SpriteSet>();
        this.tiles = new HashMap<>();

        loadSpritesFromDisk();
    }

    /**
     * Load sprites from disk. This method looks all folders in a specific resource path. For each folder in the resource
     * folder, this method looks all the image resources and store each image into a SpriteSet, and finally, store each
     * SpriteSet into the sprite Map.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create the sprite Map with image resources from resource folder.</p>
     */
    private void loadSpritesFromDisk() {
        loadGameEntities();
        loadTiles();
    }

    /**
     * Load sprites of game entities from disk. This method looks all folders in a specific resource path. For each
     * folder in the resource folder, this method looks all the image resources and store each image into a SpriteSet,
     * and finally, store each SpriteSet into the sprite Map.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create the sprite Map with image resources from resource folder.</p>
     */
    private void loadGameEntities() {
        String[] folderNames = getFolderNames(ENTITY_SPRITES_PATH);

        for (String folderName : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = ENTITY_SPRITES_PATH + "/" + folderName;
            String[] sheetsFolder = getSheetsInFolder(ENTITY_SPRITES_PATH + "/" + folderName);

            for (String sheetName : sheetsFolder) {
                spriteSet.addSheet(
                        sheetName.substring(0, sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }
            gameEntities.put(folderName, spriteSet);
        }
    }

    /**
     * Load sprites of game tiles from disk. This method looks all folders in a specific resource path. For each
     * folder in the resource folder, this method looks all the image resources and store each image into a SpriteSet,
     * and finally, store each SpriteSet into the sprite Map.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create the sprite Map with image resources from resource folder.</p>
     */
    private void loadTiles() {
        BufferedImage image = new BufferedImage(ChoiceCraft.SPRITE_SIZE, ChoiceCraft.SPRITE_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, ChoiceCraft.SPRITE_SIZE, ChoiceCraft.SPRITE_SIZE);
        g2d.dispose();

        tiles.put("default", image);
    }

    /**
     * Helper method that get all animation sheets from a specific folder.
     * <p>Precondition: input file path is a String and is not null.</p>
     * <p>Postcondition: get all animation sheets from the folder that specified by the basepath.</p>
     *
     * @param basePath that is not null.
     * @return an array of names of all animation sheets in that folder.
     */
    private String[] getSheetsInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    /**
     * Helper method that get all folders that contain animation sheets of ChoiceCraft from a specific folder.
     * <p>Precondition: input file path is a String and is not null.</p>
     * <p>Postcondition: get all folders that each contains animation sheets from that specified by the basepath.</p>
     *
     * @param basePath that is not null.
     * @return an array of names of all folders that each contains animation sheets in that folder.
     */
    private String[] getFolderNames(String basePath) {
        System.out.println(SpriteLibrary.class.getResource("/"));
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    public SpriteSet getEntitySprite(String name) {
        return gameEntities.get(name);
    }

    public Image getTile(String name) {
        return tiles.get(name);
    }
}
