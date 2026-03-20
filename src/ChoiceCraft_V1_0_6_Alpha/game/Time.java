/**
 * ========================================================================================================================
 * ChoiceCraft timer.
 * <p>
 * Author: David Liu.                                                                                   Date:3/19/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game;

/**
 * ChoiceCraft timer.
 *
 * @author David Liu.
 * @since 3/19/2026
 */
public final class Time {

    private int updateSinceStart;

    public Time() {
        this.updateSinceStart = 0;
    }

    /**
     * Seconds times UPS (60).
     * <p>Precondition: seconds that is greater than 0.</p>
     * <p>Postcondition: return number of updates from given seconds.</p>
     *
     * @param seconds that is greater than 0.
     * @return number of updates from given seconds.
     */
    public int getUpdatesFromSeconds(int seconds) {
        return GameLoop.UPDATES_PER_SECOND * seconds;
    }
}
