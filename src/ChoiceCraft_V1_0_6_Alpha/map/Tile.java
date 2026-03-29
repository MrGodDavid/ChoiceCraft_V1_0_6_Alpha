/**
 * ========================================================================================================================
 * Tile class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.map;

import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.awt.*;

/**
 * Individual tile of ChoiceCraft game map.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public final class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "resized_grass_block_top");
    }

    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.sprite = spriteLibrary.getImage(tileName);
    }

    private Tile(Image sprite) {
        this.sprite = sprite;
    }

    public static Tile copyOf(Tile other) {
        return new Tile(other.getSprite());
    }

    public Image getSprite() {
        return sprite;
    }
}
