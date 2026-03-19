/**
 * ========================================================================================================================
 * Game state class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.state;

import ChoiceCraft_V1_0_6_Alpha.controller.PlayerController;
import ChoiceCraft_V1_0_6_Alpha.entity.Player;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Size;
import ChoiceCraft_V1_0_6_Alpha.input.KeyboardInput;
import ChoiceCraft_V1_0_6_Alpha.map.ChoiceCraftMap;

/**
 * Playing state in ChoiceCraft. This state indicates that player is playing ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/18/2026
 */
public class GameState extends State {

    public GameState(Size windowSize, KeyboardInput keyboardInput) {
        super(windowSize, keyboardInput);
        Player player = new Player(new PlayerController(keyboardInput), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);
    }
}
