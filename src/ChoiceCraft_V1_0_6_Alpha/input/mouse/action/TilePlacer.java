/**
 * ========================================================================================================================
 * Placing tiles when editing ChoiceCraft map.
 * <p>
 * Author: David Liu.                                                                                   Date:3/28/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.input.mouse.action;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Position;
import ChoiceCraft_V1_0_6_Alpha.map.Tile;
import ChoiceCraft_V1_0_6_Alpha.state.State;
import ChoiceCraft_V1_0_6_Alpha.ui.UIImage;

/**
 * Placing tiles when editing ChoiceCraft map.
 *
 * @author David Liu
 * @since 3/28/2026
 */
public class TilePlacer extends MouseAction {

    private Tile tile;
    private UIImage preview;
    private int gridX, gridY;

    public TilePlacer(Tile tile) {
        this.tile = tile;
        preview = new UIImage(tile.getSprite());
    }

    /**
     * Update ChoiceCraft mouse action based on current ChoiceCraft state.
     * <p>Precondition: current state is not null.</p>
     * <p>Postcondition: update ChoiceCraft mouse action based on current game state.</p>
     *
     * @param state current ChoiceCraft game state that is not null.
     */
    @Override
    public void update(State state) {
        Position position = Position.copyOf(state.getInput().getCursorPosition());
        position.add(state.getCamera().getPosition());

        gridX = position.intX() / ChoiceCraft.SPRITE_SIZE;
        gridY = position.intY() / ChoiceCraft.SPRITE_SIZE;

        position.subtract(new Position(position.intX() % ChoiceCraft.SPRITE_SIZE, position.intY() % ChoiceCraft.SPRITE_SIZE));
        position.subtract(state.getCamera().getPosition());

        preview.setAbsolutePosition(position);
    }

    @Override
    public UIImage getSprite() {
        return preview;
    }

    /**
     * Define the click function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onClick() that is
     * specified by user.</p>
     *
     * @param state that is not null.
     */
    @Override
    public void onClick(State state) {
    }

    /**
     * Define the drag function of clickable ui component of ChoiceCraft.
     * <p>Precondition: none.</p>
     * <p>Postcondition: Each non-abstract subclass of UIClickable has its own implementation of onDrag() that is
     * specified by user.</p>
     *
     * @param state that is not null.
     */
    @Override
    public void onDrag(State state) {
        if (state.getGameMap().gridWithinBounds(gridX, gridY)) {
            state.getGameMap().setTile(gridX, gridY, Tile.copyOf(tile));
        }
    }
}
