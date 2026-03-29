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
    public int getUpdatesFromSeconds(double seconds) {
        return (int) Math.round(GameLoop.UPDATES_PER_SECOND * seconds);
    }

    public void update() {
        updateSinceStart++;
    }

    public String getFormattedTime() {
        StringBuilder stringBuilder = new StringBuilder();
        int minutes = updateSinceStart / GameLoop.UPDATES_PER_SECOND / 60;
        int seconds = updateSinceStart / GameLoop.UPDATES_PER_SECOND % 60;

        if (minutes < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(minutes);
        stringBuilder.append(":");
        if (seconds < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(seconds);
        return stringBuilder.toString();
    }

    public boolean secondsDivisibleBy(double seconds) {
        return updateSinceStart % getUpdatesFromSeconds(seconds) == 0;
    }
}
