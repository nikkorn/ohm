package com.dumbpug.ohm.projectiles;

import com.dumbpug.ohm.area.Helpers;
import com.dumbpug.ohm.nbp.Box;
import com.dumbpug.ohm.nbp.point.IntersectionPoint;

/**
 * A physics box for a thrown projectile.
 */
public class ThrownProjectilePhysicsBox extends ProjectilePhysicsBox {

    /**
     * Create a new instance of the ThrownProjectilePhysicsBox class.
     * @param size The size of the physics box.
     */
    public ThrownProjectilePhysicsBox(float size) {
        super(size);
    }

    @Override
    protected void onCollisonWithKineticBox(Box collidingBox, IntersectionPoint kinematicBoxOriginAtCollision) {
        // By default, nothing should happen if a thrown projectile hits anything.
    }

    @Override
    protected void onBeforeUpdate() {
        // Reduce the thrown projectile movement velocity over time so that it doesn't slide everywhere.
        this.setVelY(Helpers.isBoxIdle(this) ? 0 : this.getVelY() * 0.96f);
        this.setVelX(Helpers.isBoxIdle(this) ? 0 : this.getVelX() * 0.96f);
    }
}
