/**
 * ========================================================================================================================
 * Launcher of ChoiceCraft V1.0.6 Alpha.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

public class ChoiceCraftLauncher {

    public static void main(String[] args) {
        new Thread(new GameLoop()).start();
    }
}
