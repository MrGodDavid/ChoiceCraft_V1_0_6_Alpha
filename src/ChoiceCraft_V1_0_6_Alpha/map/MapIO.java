/**
 * ========================================================================================================================
 * Map input and output of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/29/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.map;

import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.io.*;
import java.net.URL;

/**
 * Map input and output of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/29/2026
 */
public final class MapIO {

    public static void save(ChoiceCraftMap map) {
        final URL urlToResourcesFolder = MapIO.class.getResource("/");
        File mapsFolder = new File(urlToResourcesFolder.getFile() + "/maps/");

        if (!mapsFolder.exists()) {
            mapsFolder.mkdir();
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(mapsFolder.toString() + "/map.ccm"))) {
            objectOutputStream.writeObject(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ChoiceCraftMap load(SpriteLibrary spriteLibrary) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MapIO.class.getResource("/maps/map.ccm").getFile()))) {
            ChoiceCraftMap map = (ChoiceCraftMap) objectInputStream.readObject();
            map.reloadGraphics(spriteLibrary);
            return map;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
