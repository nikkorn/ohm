package com.dumbpug.ohm.player;

import com.dumbpug.ohm.input.IInputProvider;

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
