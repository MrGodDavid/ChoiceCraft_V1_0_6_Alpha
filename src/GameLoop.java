/**
 * ========================================================================================================================
 * ChoiceCraft game loop.
 * <p>
 * Author: David Liu.                                                                                   Date:3/15/2026
 * ========================================================================================================================
 */

/**
 * GameLoop of ChoiceCraft.
 *
 * @author David Liu
 * @since 3/15/2026
 */
public class GameLoop implements Runnable {

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

            while (accumulator >= updateRate) {
                update();
                accumulator -= updateRate;
            }
            render();
            printStat();
        }
    }

    /**
     * Update ChoiceCraft game once. Call this method multiple times in one second. (UPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: update ChoiceCraft game once.</p>
     */
    private void update() {
        choiceCraft.update();
        ups++;
    }

    /**
     * Render ChoiceCraft game once. Call this method multiple times in one second. (FPS)
     * <p>Precondition: none</p>
     * <p>Postcondition: render ChoiceCraft game once.</p>
     */
    private void render() {
        choiceCraft.render();
        fps++;
    }

    /**
     * Prints statistics of ChoiceCraft. Including UPS and FPS, free memory and used memory of game in runtime.
     * <p>Precondition: ChoiceCraft is running.</p>
     * <p>Postcondition: Prints UPS and FPS stat of ChoiceCraft.</p>
     */
    private void printStat() {
        if (System.currentTimeMillis() > nextStatTime) {
            System.out.print("[ChoiceCraft StatPrinter]: \t");
            System.out.printf("[FPS: %d, UPS: %d] \t||\t",  fps, ups);
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
