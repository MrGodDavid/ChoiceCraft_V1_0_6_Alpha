/**
 * ========================================================================================================================
 * Launcher of ChoiceCraft V1.0.6 Alpha.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

/**
 * Entry point of ChoiceCraft.
 *
 * @author David Liu.
 * @since 3/15/2026
 */
public final class ChoiceCraftLauncher {

    public static void main(String[] args) {
        new Thread(new GameLoop(new ChoiceCraft(800, 600))).start();
    }
}
