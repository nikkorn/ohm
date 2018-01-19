package com.dumbpug.ohm.character.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.ohm.character.player.ElectroChargeLevel;
import com.dumbpug.ohm.character.player.PlayerPhysicsBox;

/**
 * An enemy.
 */
public abstract class Enemy extends com.dumbpug.ohm.character.Character {
    /** The enemy physics box. */
    private EnemyPhysicsBox physicsBox;

    /**
     * Initialise a new instance of the Enemy class.
     * @param enemyPhysicsBox The enemy physics box.
     */
    public Enemy(EnemyPhysicsBox enemyPhysicsBox) {
        super(enemyPhysicsBox);
        this.physicsBox = enemyPhysicsBox;
    }

    /**
     * Tick the player.
     */
    public void tick() {}

    @Override
    public void draw(SpriteBatch batch) {}
}