/**
 * ========================================================================================================================
 * Game map class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.map;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;

import java.util.Arrays;

/**
 * Basically the game map of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public final class ChoiceCraftMap {

    private Tile[][] tiles;

    public ChoiceCraftMap(Size size, SpriteLibrary spriteLibrary) {
        tiles = new Tile[size.getWidth()][size.getHeight()];

        initializeTiles(spriteLibrary);
    }

    private void initializeTiles(SpriteLibrary spriteLibrary) {
        for (Tile[] tile : tiles) {
            Arrays.fill(tile, new Tile(spriteLibrary));
        }
    }

    /**
     * Generate a random position in ChoiceCraft game map.
     * <p>Precondition: none.</p>.
     * <p>Postcondition: generate a random position.</p>
     *
     * @return a random position.
     */
    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * ChoiceCraft.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * ChoiceCraft.SPRITE_SIZE;
        return new Position(x, y);
    }

    public int getWidth() {
        return tiles.length * ChoiceCraft.SPRITE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length  * ChoiceCraft.SPRITE_SIZE;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
