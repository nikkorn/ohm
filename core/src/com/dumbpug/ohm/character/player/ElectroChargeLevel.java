package com.dumbpug.ohm.character.player;

import com.dumbpug.ohm.Constants;

/**
 * The players electro charge level.
 */
public class ElectroChargeLevel {

    /** The charge level. Depleted if 0. */
    private int level = Constants.PLAYER_ELECTRO_CHARGE_POINTS * Constants.PLAYER_ELECTRO_UPDATES_PER_POINT;

    /** Flag defining whether the charge is in use. */
    private boolean isInUse = false;

    /**
     * Set whether this charge level is enabled.
     * @param isEnabled
     */
    public void setEnabled(boolean isEnabled) { this.isInUse = isEnabled; }

    /**
     * Gets whether this charge level is enabled.
     * @return is enabled
     */
    public boolean isEnabled() { return this.isInUse; }

    /**
     * Update the charge level.
     */
    public void update() {
        if (isInUse && level > 0) {
            level-=1;
        }
    }

    /**
     * Returns whether there is charge.
     * @return has charge.
     */
    public boolean hasCharge() { return this.level > 0; }

    /**
     * Fill the charge to it's maximum.
     */
    public void fill() { this.level = Constants.PLAYER_ELECTRO_CHARGE_POINTS * Constants.PLAYER_ELECTRO_UPDATES_PER_POINT; }

    /**
     * Get available point of charge.
     * @return charge points.
     */
    public int getPoints() { return (level == 0) ? 0 : ((int)(level / Constants.PLAYER_ELECTRO_UPDATES_PER_POINT)) + 1; }
}