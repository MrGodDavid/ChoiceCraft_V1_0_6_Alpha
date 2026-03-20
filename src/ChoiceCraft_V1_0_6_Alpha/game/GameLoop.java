/**
 * ========================================================================================================================
 * ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game loop.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game;

/**
 * ChoiceCraft_V1_0_6_Alpha.game.GameLoop of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public final class GameLoop implements Runnable {

    public static final int  UPDATES_PER_SECOND = 60;

    private final ChoiceCraft choiceCraft;

    private boolean running;
    private final double updateRate = 1.0d / 60.0d;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(ChoiceCraft choiceCraft) {
        this.choiceCraft = choiceCraft;
    }

    /**
     * Runs this operation.
     * <p>Precondition: none</p>
     * <p>Postcondition: run ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game.</p>
     */
    @Override
    public void run() {
        running = true;
        double accumulator = 0.0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if (accumulator >= updateRate) {
                while (accumulator >= updateRate) {
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
//            render();
            printStat();
        }
    }

    /**
     * Update ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once.</p>
     */
    private void update() {
        choiceCraft.update();
        ups++;
    }

    /**
     * Render ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once. Call this method multiple times in one second. (FPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: render ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft game once.</p>
     */
    private void render() {
        choiceCraft.render();
        fps++;
    }

    /**
     * Prints statistics of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft. Including UPS and FPS, free memory and used memory of game in runtime.
     * <p>Precondition: ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft is running.</p>
     * <p>Postcondition: Prints UPS and FPS stat of ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft.</p>
     */
    private void printStat() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.print("[ChoiceCraft_V1_0_6_Alpha.game.ChoiceCraft StatPrinter]: \t");
            System.out.printf("[FPS: %d, UPS: %d] \t||\t", fps, ups);
            fps = ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;

            Runtime runtime = Runtime.getRuntime();
            System.gc();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            System.out.printf("[Used: %d MB | Free: %d MB | Total: %d MB]\n",
                    usedMemory / (1024 * 1024),
                    freeMemory / (1024 * 1024),
                    totalMemory / (1024 * 1024));
        }
    }
}
