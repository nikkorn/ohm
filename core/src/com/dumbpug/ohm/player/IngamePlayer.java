package com.dumbpug.ohm.player;

import com.dumbpug.ohm.area.Area;
import com.dumbpug.ohm.input.Control;
import com.dumbpug.ohm.input.IInputProvider;
import com.dumbpug.ohm.weapons.Weapon;

/**
 * Represents an in-game player.
 */
public class IngamePlayer {
    /**
     * The player.
     */
    private Player player;
    /**
     * The player status.
     */
    private Status status = new Status();
    /**
     * The input provider for the player.
     */
    private IInputProvider inputProvider;
    /**
     * The player colour.
     */
    private PlayerColour playerColour;

    /**
     * Creates a new instance of the IngamePlayer class.
     * @param player        The player.
     * @param inputProvider The input provider for the player.
     * @param colour        The colour of the player.
     */
    public IngamePlayer(Player player, IInputProvider inputProvider, PlayerColour colour) {
        this.player        = player;
        this.inputProvider = inputProvider;
        this.playerColour  = colour;
        this.player.getPhysicsBox().setUserData(this);
    }

    /**
     * Process input gor this in-game player.
     * @param area The area the player is in.
     */
    public void processInput(Area area) {
        // Do nothing if this player has no input provider.
        if (inputProvider == null) {
            return;
        }
        // Get the actual Player instance.
        Player player = this.getPlayer();
        // Are we moving left/right?
        if (inputProvider.isControlPressed(Control.LEFT)) {
            player.moveLeft();
        } else if (inputProvider.isControlPressed(Control.RIGHT)) {
            player.moveRight();
        }
        // Are we moving up/down?
        if (inputProvider.isControlPressed(Control.UP)) {
            player.moveUp();
        } else if (inputProvider.isControlPressed(Control.DOWN)) {
            player.moveDown();
        }
        // Get our angle of aim for the player.
        float angleOfAim = inputProvider.getAngleOfAim();
        if (angleOfAim != -1) {
            player.setAngleOfAim(angleOfAim);
        }
        // Process any input relating to using an equipped weapon (if we have one).
        if (this.getStatus() != null && this.getStatus().getEquippedWeapon() != null) {
            ProcessWeaponUse();
        }
        // Has the player tried to drop/swap their current weapon?
        if (inputProvider.isControlJustPressed(Control.SECONDARY)) {
            // What we do here depends on whether the player is already holding a weapon.
            if (this.getStatus().getEquippedWeapon() != null) {
                // The player wants to swap their weapon for a weapon pickup
                // if they are standing over one. Or drop their weapon if
                // they are not standing over a weapon pickup.
                area.swapOrDropPlayerWeapon(this);
            } else {
                // The player has no weapon but is trying to pick one up.
                area.pickUpPlayerWeapon(this);
            }
        }
    }

    /**
     * Process any input relating to using the equipped weapon.
     */
    private void ProcessWeaponUse() {
        // Get the weapon.
        Weapon weapon = this.getStatus().getEquippedWeapon();
        // Get whether the fire button was JUST pressed.
        boolean fireJustPressed = inputProvider.isControlJustPressed(Control.ACCEPT);
        // Attempt to use the weapon if the fire button is pressed.
        if (inputProvider.isControlPressed(Control.ACCEPT)) {
            // Use our weapon!
            weapon.use(fireJustPressed);
        }
    }

    /**
     * Apply damage to the in-game player.
     * @param damage The damage to apply.
     */
    public void applyDamage(int damage) {
        // We can only apply damage if we have player status.
        if (this.status != null) {
            // Get the player life after damage is applied.
            int newLife = this.getStatus().getLife() - damage;
            // Set the players life, not going below zero.
            this.getStatus().setLife(newLife < 0 ? 0 : newLife);
        }
    }

    /**
     * Get the status of the in-game player.
     * @return The status of the in-game player.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the in-game player.
     * @param status The status of the in-game player.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get the input provider of this in-game player.
     * @return The input input provider of this in-game player.
     */
    public IInputProvider getInputProvider() {
        return inputProvider;
    }

    /**
     * Set the input provider of this in-game player.
     * @param inputProvider The input provider of this in-game player.
     */
    public void setInputProvider(IInputProvider inputProvider) {
        this.inputProvider = inputProvider;
    }

    /**
     * Get the player colour.
     * @return The player colour.
     */
    public PlayerColour getPlayerColour() {
        return playerColour;
    }

    /**
     * Get the actual Player instance.
     * @return The actual Player instance.
     */
    public Player getPlayer() {
        return player;
    }
}
