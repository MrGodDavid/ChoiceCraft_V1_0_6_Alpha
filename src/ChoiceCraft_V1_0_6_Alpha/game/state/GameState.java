/**
 * ========================================================================================================================
 * Game state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.state;

import ChoiceCraft_V1_0_6_Alpha.controller.EnemyController;
import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.GameObject;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.entity.SelectionCircle;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.effect.Happy;
import ChoiceCraft_V1_0_6_Alpha.entity.character.npc.Enchanter;
import ChoiceCraft_V1_0_6_Alpha.game.ui.UIGameTime;
import ChoiceCraft_V1_0_6_Alpha.game.ui.UIHappinessStats;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.HorizontalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Spacing;

import java.awt.*;

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
        initializeUI(windowSize);
    }

    private void initializeUI(Size windowSize) {
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UIHappinessStats(windowSize));
    }

    /**
     * Initialize ChoiceCraft characters.
     * <p>Precondition: none.</p>
     * <p>Postcondition: initializes all ChoiceCraft characters. Player, NPC, Enemy, etc.</p>
     */
    private void initializeCharacters() {
        SelectionCircle circle = new SelectionCircle();
        Player player = new Player(new PlayerController(keyboardInput), spriteLibrary, circle);
        gameObjects.add(player);
        camera.focusOn(player);

        gameObjects.add(circle);

        initializeAllNPCs(100);
        initializeAllEnemies(50);

        makeNumberOfNPCHappy(10);
    }

    @SuppressWarnings("SameParameterValue")
    private void makeNumberOfNPCHappy(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Happy()));
    }

    @SuppressWarnings("SameParameterValue")
    private void initializeAllEnemies(int numberOfEnemy) {
        for (int i = 0; i < numberOfEnemy; i++) {
            Enemy enemy = new Enemy(new EnemyController(), spriteLibrary);
            enemy.setPosition(gameMap.getRandomPosition());
            gameObjects.add(enemy);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void initializeAllNPCs(int numberOfNpc) {
        for (int i = 0; i < numberOfNpc; i++) {
            Enchanter enchanter = new Enchanter(new NPCController(), spriteLibrary);
            enchanter.setPosition(gameMap.getRandomPosition());
            gameObjects.add(enchanter);
        }
    }
}
