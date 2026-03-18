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

    private static final String ENTITY_SPRITES_PATH = "/resources/sprites/entity";

    private Map<String, SpriteSet> units;

    public SpriteLibrary() {
        units = new HashMap<String, SpriteSet>();
        loadSpritesFromDisk();
    }

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

    private String[] getSheetsInFolder(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
        File file = new File(resource.getFile());
        return file.list((current, name) -> new File(current, name).isDirectory());
    }
}
