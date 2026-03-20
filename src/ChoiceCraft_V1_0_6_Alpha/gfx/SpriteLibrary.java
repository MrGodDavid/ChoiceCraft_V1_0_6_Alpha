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
public final class SpriteLibrary {

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
        loadGameEntities("/sprites/entity");
        loadTiles("/sprites/tiles_resized");
    }

    /**
     * Load sprites of game entities from disk. This method looks all folders in a specific resource path. For each
     * folder in the resource folder, this method looks all the image resources and store each image into a SpriteSet,
     * and finally, store each SpriteSet into the sprite Map.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create the sprite Map with image resources from resource folder.</p>
     *
     * @param path that indicates the path of sprites.
     */
    private void loadGameEntities(String path) {
        String[] folderNames = getFolderNames(path);

        for (String folderName : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folderName;
            String[] sheetsFolder = getImagesInFolder(path + "/" + folderName);

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
     *
     * @param path that indicates the path of sprites.
     */
    private void loadTiles(String path) {
        String[] imagesInFolder = getImagesInFolder(path);
        for (String fileName : imagesInFolder) {
            tiles.put(
                    fileName.substring(0, fileName.length() - 4),
                    ImageUtils.loadImage(path + "/" + fileName)
            );
        }
    }

    /**
     * Helper method that get all animation sheets from a specific folder.
     * <p>Precondition: input file path is a String and is not null.</p>
     * <p>Postcondition: get all animation sheets from the folder that specified by the basepath.</p>
     *
     * @param basePath that is not null.
     * @return an array of names of all animation sheets in that folder.
     */
    private String[] getImagesInFolder(String basePath) {
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

    @Override
    public String toString() {
        return "GameEntities: \n" + gameEntities + "\nTiles: \n" + tiles;
    }

    public SpriteSet getEntitySprite(String name) {
        return gameEntities.get(name);
    }

    public Image getTile(String name) {
        return tiles.get(name);
    }
}
