/**
 * ========================================================================================================================
 * Game ChoiceCraft_V1_0_6_Alpha.state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.state;

import ChoiceCraft_V1_0_6_Alpha.controller.EnemyController;
import ChoiceCraft_V1_0_6_Alpha.controller.NPCController;
import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.entity.Enemy;
import ChoiceCraft_V1_0_6_Alpha.entity.NPC;
import ChoiceCraft_V1_0_6_Alpha.entity.SelectionCircle;
import ChoiceCraft_V1_0_6_Alpha.entity.character.player.Player;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.Humanoid;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Happy;
import ChoiceCraft_V1_0_6_Alpha.entity.character.npc.Enchanter;
import ChoiceCraft_V1_0_6_Alpha.entity.humanoid.effect.Scared;
import ChoiceCraft_V1_0_6_Alpha.game.ui.UIGameTime;
import ChoiceCraft_V1_0_6_Alpha.game.ui.UIHappinessStats;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Condition;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.Input;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;
import ChoiceCraft_V1_0_6_Alpha.ui.UIText;
import ChoiceCraft_V1_0_6_Alpha.ui.VerticalContainer;
import ChoiceCraft_V1_0_6_Alpha.ui.auxiliary.Alignment;
import ChoiceCraft_V1_0_6_Alpha.ui.clickable.UIButton;

import java.awt.*;
import java.util.List;

/**
 * Playing ChoiceCraft_V1_0_6_Alpha.state in ChoiceCraft. This ChoiceCraft_V1_0_6_Alpha.state indicates that player is playing ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public final class GameState extends State {

    private List<Condition> victoryConditions;
    private List<Condition> defeatConditions;
    private boolean playing;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        this.gameMap = new ChoiceCraftMap(new Size(20, 20), spriteLibrary);
        playing = true;
        initializeCharacters();
        initializeUI(windowSize);
        initializeConditions();
    }

    private void initializeConditions() {
        victoryConditions = List.of(() -> getNumberOfHappy() == 0);
        defeatConditions = List.of(() -> (double) getNumberOfHappy() / getNumberOfNPCs() > 0.25);

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
        Player player = new Player(new PlayerController(input), spriteLibrary, circle);
        gameObjects.add(player);
        camera.focusOn(player);

        gameObjects.add(circle);

        initializeAllNPCs(100);
        initializeAllEnemies(50);

        makeNumberOfNPCHappy(0);
    }

    /**
     * Update ChoiceCraft_V1_0_6_Alpha.state in ChoiceCraft multiple times per frame. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update State once.</p>
     */
    @Override
    public void update() {
        super.update();

        if (playing) {
            if (victoryConditions.stream().allMatch(Condition::isMet)) {
                win();
            }
            if (defeatConditions.stream().allMatch(Condition::isMet)) {
                lose();
            }
        }
    }

    private void win() {
        playing = false;
        VerticalContainer winContainer = new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER,  Alignment.Position.CENTER));
        winContainer.setBackground(Color.DARK_GRAY);
        winContainer.addUIComponent(new UIText("VICTORY!"));
        winContainer.addUIComponent(new UIButton("MENU", () -> System.out.println("BUTTON 1 PRESSED!")));
        winContainer.addUIComponent(new UIButton("OPTIONS", () -> System.out.println("BUTTON 2 PRESSED!")));
        winContainer.addUIComponent(new UIButton("EXIT", () -> System.exit(0)));
        uiContainers.add(winContainer);
    }

    private void lose() {
        playing = false;
        VerticalContainer loseContainer = new VerticalContainer(camera.getSize());
        loseContainer.setAlignment(new Alignment(Alignment.Position.CENTER,  Alignment.Position.CENTER));
        loseContainer.addUIComponent(new UIText("DEFEAT..."));
        uiContainers.add(loseContainer);
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

    public long getNumberOfHappy() {
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Happy.class))
                .count();
    }

    public long getNumberOfNonchalant() {
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffectedBy(Happy.class))
                .count();
    }

    public long getNumberOfScared() {
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Happy.class) && humanoid.isAffectedBy(Scared.class))
                .count();
    }

    public long getNumberOfNPCs() {
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Happy.class))
                .count();
    }
}
