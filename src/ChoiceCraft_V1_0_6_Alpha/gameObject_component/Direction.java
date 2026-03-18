/**
 * ========================================================================================================================
 * Direction component of game entity.
 * <p>
 * Author: David Liu.                                                                                   Date:3/18/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gameObject_component;

/**
 * Direction component of game entity.
 *
 * @author David Liu
 * @since 3/18/2026
 */
public enum Direction {

    SOUTH(0),
    SOUTH_WEST(1),
    WEST(2),
    NORTH_WEST(3),
    NORTH(4),
    NORTH_EAST(5),
    EAST(6),
    SOUTH_EAST(7);

    private final int animationRow;

    Direction(int animationRow) {
        this.animationRow = animationRow;
    }

    public static Direction fromMotion(Motion motion) {
        return motion.getVector().toDirection();
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
