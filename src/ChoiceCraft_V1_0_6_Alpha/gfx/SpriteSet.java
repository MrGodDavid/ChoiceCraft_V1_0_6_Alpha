/**
 * ========================================================================================================================
 * Hold all sprites of ChoiceCraft.
 * <p>
 * Author: David Liu.                                                                                   Date:3/17/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Hold all sprites of ChoiceCraft. Using {@link java.util.Map Map} to store all game sprites.
 *
 * @author David Liu
 * @since 3/17/2026
 */
public final class SpriteSet {

    private final Map<String, Image> animationSheets;

    public SpriteSet() {
        this.animationSheets = new HashMap<>();
    }

    /**
     * Add a new animation sheet to animation sheets of ChoiceCraft.
     * <p>Precondition: name is not null, animation sheet is not null.</p>
     * <p>Postcondition: add the animationSheet to ChoiceCraft sheet library.</p>
     *
     * @param name           that is not null, and it indicates the name of animation sheet.
     * @param animationSheet that is not null.
     */
    public void addSheet(String name, Image animationSheet) {
        this.animationSheets.put(name, animationSheet);
    }

    /**
     * Get the animation sheet by its name.
     * <p>Precondition: name is not null.</p>
     * <p>Postcondition: get the animation sheet from ChoiceCraft animation sheet library by name.</p>
     *
     * @param name that is not null, and it indicates the name of animation sheet.
     */
    public Image getAnimationSheet(String name) {
        return this.animationSheets.get(name);
    }

    @Override
    public String toString() {
        return "SpriteSet: \n" + this.animationSheets.toString();
    }
}
