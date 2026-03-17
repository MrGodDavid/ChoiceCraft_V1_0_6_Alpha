/**
 * ========================================================================================================================
 * Moving entity class.
 * <p>
 * Author: David Liu.                                                                                   Date:3/16/2026
 * ========================================================================================================================
 */
package ChoiceCraft_V1_0_6_Alpha.entity;

import ChoiceCraft_V1_0_6_Alpha.controller.Controller;
import ChoiceCraft_V1_0_6_Alpha.gameObject_component.Movement;

/**
 * Moveable entity in ChoiceCraft.
 *
 * @author David Liu
 * @since 3/16/2026
 */
public abstract class MovingEntity extends GameObject {

    protected Controller controller;
    private Movement movement;

    public MovingEntity(Controller controller) {
        super();
        this.controller = controller;
        this.movement = new Movement(2);
    }

    /**
     * Update game object of ChoiceCraft every frame.
     * <p>Update movement of Moving Entity</p>
     * <p>Precondition: none</p>
     * <p>Postcondition: game loop update all game objects that has own update() implementation
     * every frame ups times</p>
     */
    @Override
    public void update() {
        movement.update(controller);
        position.apply(movement);
    }
}
