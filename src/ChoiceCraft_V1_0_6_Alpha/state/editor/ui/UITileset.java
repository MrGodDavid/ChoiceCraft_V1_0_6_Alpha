/**
 * ========================================================================================================================
 * UI of tileset in map editor of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/29/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state.editor.ui;

import ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft;
import ChoiceCraft_V1_0_6_Alpha.game.settings.RenderSettings;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.gfx.SpriteLibrary;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.map.Tile;
import ChoiceCraft_V1_0_6_Alpha.ui.*;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UICheckBox;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIMinimap;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UITileToggle;

import java.awt.*;

/**
 * A subclass of {@link ChoiceCraft_V1_0_6_Alpha.ui.UIContainer}. UI of tileset in map editor of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/29/2026
 */
public final class UITileset extends HorizontalContainer {

    public UITileset(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackground(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        addUIComponent(new UITileToggle(new Tile(spriteLibrary, "resized_dirt_path_top")));
        addUIComponent(new UITileToggle(new Tile(spriteLibrary, "resized_grass_block_top")));
        addUIComponent(getTileSet(spriteLibrary, "grass_and_dirt_path_tileset"));
    }

    private UIComponent getTileSet(SpriteLibrary spriteLibrary, String tileset) {
        UIContainer main = new HorizontalContainer(new Size(0, 0));
        main.setMargin(UIComponent.ZERO_MARGIN);
        Tile tile = new Tile(spriteLibrary, tileset);

        int tileX = tile.getImage().getWidth(null) / ChoiceCraft.SPRITE_SIZE;
        int tileY = tile.getImage().getHeight(null) / ChoiceCraft.SPRITE_SIZE;

        for (int x = 0; x < tileX; x++) {
            UIContainer column = new VerticalContainer(new Size(0, 0));
            column.setPadding(UIComponent.ZERO_PADDING);
            column.setMargin(UIComponent.ZERO_MARGIN);

            for  (int y = 0; y < tileY; y++) {
                Tile indexedTile = Tile.copyOf(tile);
                indexedTile.setTileIndex(x * tileX + y);
                UITileToggle tileToggle = new UITileToggle(indexedTile);
                column.addUIComponent(tileToggle);
            }

            main.addUIComponent(column);
        }

        return main;
    }
}
