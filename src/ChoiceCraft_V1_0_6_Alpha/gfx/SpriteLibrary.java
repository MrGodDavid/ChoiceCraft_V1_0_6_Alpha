/**
 * ========================================================================================================================
 * SpriteLibrary of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/17/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

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

    private Map<String, SpriteSet> units;

    /**
     * No-arg constructor of this class.
     * <p>Precondition: none.</p>
     * <p>Postcondition: create a new instance of unit Map, read all sprites from folders, and store them in sprite
     * Map.</p>
     */
    public SpriteLibrary() {
        units = new HashMap<String, SpriteSet>();
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
        String[] folderNames = getFolderNames(ENTITY_SPRITES_PATH);

        for (String folderName : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = ENTITY_SPRITES_PATH + "/" + folderName;
            String[] sheetsFolder = getSheetsInFolder(ENTITY_SPRITES_PATH + "/" + folderName);

            for (String sheetName : sheetsFolder) {
                spriteSet.addSheet(
                        sheetName.substring(sheetName.length() - 4),
                        ImageUtils.loadImage(pathToFolder + "/" + sheetName)
                );
            }

            units.put(folderName, spriteSet);
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
}
