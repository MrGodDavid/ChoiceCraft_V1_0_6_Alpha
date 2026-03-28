/**
 * ========================================================================================================================
 * ChoiceCraft settings.
 * <p>
 * Author: David Liu.                                                                                   Date:3/27/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.game.settings;

/**
 * Generic setting object of ChoiceCraft. Wrapper class of boolean variables (shouldRenderGrid, shouldRenderCollisionBox,
 * etc.) Wrapper class of value.
 *
 * @param <T> setting type.
 * @author David Liu.
 * @since 3/27/2026
 */
public class Setting<T> {

    private T value;

    public Setting(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
