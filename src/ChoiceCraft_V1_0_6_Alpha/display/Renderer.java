/**
 * ========================================================================================================================
 * Rendering system of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.display;

import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.state.State;
import ChoiceCraft_V1_0_6_Alpha.map.Tile;

import java.awt.*;

/**
 * Rendering system of ChoiceCraft. Acts like a rendering pipeline of ChoiceCraft (Not actually rendering pipeline.)
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class Renderer {

    /**
     * Render all game objects' sprites in ChoiceCraft.
     * <p>Precondition: input ChoiceCraft is not null. Input graphics from BufferStrategy is not null</p>
     * <p>Postcondition: renders ChoiceCraft.</p>
     *
     * @param state that is not null.
     * @param g     that is not null.
     */
    public void render(State state, Graphics g) {
        renderMap(state, g);
        Camera camera = state.getCamera();
        for (GameObject gameObject : state.getGameObjects()) {
            g.drawImage(gameObject.getSprite(),
                    gameObject.getPosition().intX() - camera.getPosition().intX() - gameObject.getSize().getWidth() / 2,
                    gameObject.getPosition().intY() - camera.getPosition().intY() - gameObject.getSize().getHeight() / 2,
                    null
            );
        }
    }

    /**
     * Render the game map of ChoiceCraft. This method uses game state and rendering pipeline (kind of).
     * <p>Precondition: game state is not null, and rendering pipeline is not null.</p>
     * <p>Postcondition: render the game map of ChoiceCraft.</p>
     *
     * @param state that is not null.
     * @param g     rendering pipeline that is not null.
     */
    private void renderMap(State state, Graphics g) {
        Tile[][] tiles = state.getGameMap().getTiles();
        Camera camera = state.getCamera();
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[row].length; col++) {
                g.drawImage(
                        tiles[row][col].getSprite(),
                        row * ChoiceCraft.SPRITE_SIZE - camera.getPosition().intX(),
                        col * ChoiceCraft.SPRITE_SIZE - camera.getPosition().intY(),
                        null
                );
            }
        }
    }
}
