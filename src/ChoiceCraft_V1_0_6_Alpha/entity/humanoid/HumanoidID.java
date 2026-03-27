/**
 * ========================================================================================================================
 * Humanoid ID class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/26/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity.humanoid;

/**
 * Humanoid ID class.
 * <p>
 * Pattern {@code name_of_humanoid: String, name_of_default_spritesheet: String}
 * </p>
 * Basically a string pair for identification of which humanoid default animation spritesheet should be used.
 * <p>
 * Example of using this class:
 * <pre><code>
 *     // Constructor of a Humanoid subclass.
 *     public example_of_humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
 *          // superclass call and other initializations are not shown... //
 *          HumanoidID id = new HumanoidID("enchanter", "enchanter_idle_8dir_spritesheet");
 *          example_of_humanoid.animationManager = new AnimationManager(id.defaultHumanoidSpritesheetName(),
 *              spriteLibrary.getSpriteSet(id.humanoidName()));
 *          // other initializations are not shown... //
 *     }
 * </code></pre>
 * </p>
 *
 * @author David Liu
 * @since 3/26/2026
 */
public record HumanoidID(String humanoidName, String defaultHumanoidSpritesheetName) {
}
