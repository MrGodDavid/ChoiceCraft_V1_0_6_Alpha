/**
 * ========================================================================================================================
 * Launcher of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft V1.0.6 Alpha.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha;
/**
 * Entry point of ChoiceCraft_V1_0_6_Alpha.ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/15/2026
 */
public final class ChoiceCraftLauncher {

    public static void main(String[] args) {
        new Thread(new GameLoop(new ChoiceCraft(800, 600))).start();
    }
}
