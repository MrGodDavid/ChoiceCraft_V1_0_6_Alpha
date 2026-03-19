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
public class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this.sprite = spriteLibrary.getTile("resized_grass_block_top");
    }

    public Image getSprite() {
        return sprite;
    }
}
