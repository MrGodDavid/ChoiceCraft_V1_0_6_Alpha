/**
 * ========================================================================================================================
 * Tile class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.map;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Individual tile of ChoiceCraft game map.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public final class Tile implements Serializable {

    private transient Image image; // used for the entire tileset image.
    private transient Image sprite; // used for a specific tile image in tileset.
    private int tileIndex;
    private String tileName;

    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "resized_grass_block_top");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.image = spriteLibrary.getImage(tileName);
        this.tileName = tileName;
        generateSprite();
    }

    private Tile(Image image, int tileIndex, String tileName) {
        this.image = image;
        this.tileIndex = tileIndex;
        this.tileName = tileName;
        generateSprite();
    }

    private void generateSprite() {
        sprite = ((BufferedImage) image).getSubimage(
                (tileIndex / (image.getWidth(null) / ChoiceCraft.SPRITE_SIZE) * ChoiceCraft.SPRITE_SIZE),
                (tileIndex % (image.getWidth(null) / ChoiceCraft.SPRITE_SIZE) * ChoiceCraft.SPRITE_SIZE),
                ChoiceCraft.SPRITE_SIZE,
                ChoiceCraft.SPRITE_SIZE
        );
    }

    public static Tile copyOf(Tile other) {
        return new Tile(other.getImage(), other.getTileIndex(), other.getTileName());
    }

    public void reloadGraphics(SpriteLibrary spriteLibrary) {
        image = spriteLibrary.getImage(tileName);
        generateSprite();
    }

    public Image getSprite() {
        return sprite;
    }

    public Image getImage() {
        return image;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
        generateSprite();
    }

    public String getTileName() {
        return tileName;
    }
}
