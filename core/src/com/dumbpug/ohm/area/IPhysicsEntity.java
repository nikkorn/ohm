package com.dumbpug.ohm.area;

import com.dumbpug.ohm.nbp.Box;

/**
 * Represents a en entity which has a physics box.
 */
public interface IPhysicsEntity {

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    Box getPhysicsBox();

    /**
     * Whether the entity is airborne.
     * @return Whether the entity is airborne.
     */
    boolean isAirborne();
}
