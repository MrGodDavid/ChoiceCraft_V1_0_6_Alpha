/**
 * ========================================================================================================================
 * Game state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.state;

import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.effect.Happy;
import ChoiceCraft_V1_0_6_Alpha.entity.character.npc.Enchanter;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.UIContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

/**
 * Playing state in ChoiceCraft. This state indicates that player is playing ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public final class GameState extends State {

    public GameState(Size windowSize, KeyboardInput keyboardInput) {
        super(windowSize, keyboardInput);
        this.gameMap = new ChoiceCraftMap(new Size(20, 20), spriteLibrary);
        initializeCharacters();
        initializeUI();
    }

    private void initializeUI() {
        UIContainer uiContainer = new UIContainer();
        uiContainer.setPadding(new Spacing(50));
        uiContainer.setMargin(new Spacing(100));
        uiContainers.add(uiContainer);
    }

    /**
     * Initialize ChoiceCraft characters.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initializes all ChoiceCraft characters. Player, NPC, Enemy, etc.</p>
     */
    private void initializeCharacters() {
        Player player = new Player(new PlayerController(keyboardInput), spriteLibrary);

        gameObjects.add(player);
        camera.focusOn(player);

        initializeAllNPCs(100);
    }

    private void initializeAllNPCs(int numberOfNpc) {
        for (int i = 0; i < numberOfNpc; i++) {
            Enchanter enchanter = new Enchanter(new NPCController(), spriteLibrary);
            enchanter.setPosition(gameMap.getRandomPosition());
            enchanter.addEffect(new Happy());
            gameObjects.add(enchanter);
        }
    }
}
