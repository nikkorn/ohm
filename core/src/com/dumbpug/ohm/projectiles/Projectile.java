package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.area.IPhysicsEntity;
import com.dumbpug.ohm.nbp.Box;

/**
 * Represents a projectile.
 */
public abstract class Projectile implements IPhysicsEntity {
    /**
     * The physics box for the projectile.
     */
    private ProjectilePhysicsBox physicsBox;

    /**
     * Create a new instance of the Projectile class.
     */
    public Projectile() {
        // Create the physics box for this projectile.
        physicsBox = new ProjectilePhysicsBox(this.getSize());
    }

    /**
     * Get the size of the projectile.
     * @return The projectile size.
     */
    public abstract float getSize();

    /**
     * Get the physics box of the entity.
     * @return The physics box of the entity.
     */
    @Override
    public Box getPhysicsBox() { return this.physicsBox; }
}
