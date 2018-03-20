package com.dumbpug.ohm.player;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.weapons.Secondary;
import com.dumbpug.ohm.weapons.Weapon;

/**
 * The in-game status of a player.
 */
public class Status {
    /**
     * The equipped weapon.
     */
    private Weapon equippedWeapon = null;
    /**
     * The equipped secondary.
     */
    private Secondary equippedSecondary = null;
    /**
     * Whether the player has fallen out.
     */
    private boolean hasFallenOut = false;
    /**
     * The player life.
     */
    private int life = Constants.PLAYER_LIFE;

    /**
     * Get the players life.
     * @return The players life.
     */
    public int getLife() {
        return life;
    }

    /**
     * Set the players life.
     * @param life The players life.
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Gets whether the player is dead.
     * @return Whether the player is dead.
     */
    public boolean isDead() {
        return this.life <= 0;
    }

    /**
     * Get whether the player has fallen out.
     * @return Whether the player has fallen out.
     */
    public boolean isHasFallenOut() {
        return hasFallenOut;
    }

    /**
     * Set whether the player has fallen out.
     * @param hasFallenOut Whether the player has fallen out.
     */
    public void setHasFallenOut(boolean hasFallenOut) {
        this.hasFallenOut = hasFallenOut;
    }

    /**
     * Get the equipped secondary.
     * @return The equipped secondary.
     */
    public Secondary getEquippedSecondary() {
        return equippedSecondary;
    }

    /**
     * Set the equipped secondary.
     * @param equippedSecondary The equipped secondary.
     */
    public void setEquippedSecondary(Secondary equippedSecondary) {
        this.equippedSecondary = equippedSecondary;
    }

    /**
     * Get the equipped weapon.
     * @return The equipped weapon.
     */
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    /**
     * Set the equipped weapon.
     * @param equippedWeapon The equipped weapon.
     */
    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }
}
