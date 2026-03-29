/**
 * ========================================================================================================================
 * Game map class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.map;

import ChoiceCraft_V1_0_6_Alpha.display.Camera;
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

    private static final int SAFETY_SPACE = 2;

    private final Tile[][] tiles;

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
     * <p>Precondition: none.</p>
     * <p>Postcondition: generate a random position.</p>
     *
     * @return a random position.
     */
    public Position getRandomPosition() {
        double x = Math.random() * tiles.length * ChoiceCraft.SPRITE_SIZE;
        double y = Math.random() * tiles[0].length * ChoiceCraft.SPRITE_SIZE;
        return new Position(x, y);
    }

    public Position getViewableStartingGridPosition(Camera camera) {
        return new Position(
                Math.max(0, camera.getPosition().getX() / ChoiceCraft.SPRITE_SIZE - SAFETY_SPACE),
                Math.max(0, camera.getPosition().getY() / ChoiceCraft.SPRITE_SIZE - SAFETY_SPACE)
        );
    }

    public Position getViewableEndingGridPosition(Camera camera) {
        return new Position(
                Math.min(tiles.length, (camera.getPosition().getX() + camera.getSize().getWidth()) / ChoiceCraft.SPRITE_SIZE + SAFETY_SPACE),
                Math.min(tiles[0].length, (camera.getPosition().getY() + camera.getSize().getHeight()) / ChoiceCraft.SPRITE_SIZE + SAFETY_SPACE)
        );
    }

    public int getWidth() {
        return tiles.length * ChoiceCraft.SPRITE_SIZE;
    }

    public int getHeight() {
        return tiles[0].length  * ChoiceCraft.SPRITE_SIZE;
    }

    public boolean gridWithinBounds(int gridX, int gridY) {
        return gridX >= 0 && gridX < tiles.length && gridY >= 0 && gridY < tiles[0].length;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTile(int gridX, int gridY, Tile tile) {
        tiles[gridX][gridY] = tile;
    }
}
