package com.dumbpug.ohm.area.schedule;

/**
 * An action to perform as part of a Schedule.
 */
public abstract class Action {
    /* Whether this action has been completed. */
    private boolean isComplete = false;
    /* Whether this action has started. */
    private boolean isStarted = false;

    /**
     * Perform this action.
     */
    public abstract void perform();

    /**
     * Called whenever this action is started.
     */
    public abstract void onStart();

    /**
     * Calls through to perform but will call onStart() if needed.
     */
    public void doAction() {
        // If this is the first time performing this action since creating/reset then call onStart().
        if (!this.isStarted) {
            this.onStart();
            this.isStarted = true;
        }
        // Perform the actual action.
        this.perform();
    }

    /**
     * Reset this potentially completed action.
     */
    public void reset() {
        // This action is no longer complete.
        this.isComplete = false;
        // This action is no longer started.
        this.isStarted = false;
    }

    /**
     * Returns whether this action is completed.
     * @return has the action been completed
     */
    public boolean isComplete() { return this.isComplete; }

    /**
     * Called when an action is finished with.
     */
    protected void finish() { this.isComplete = true; }
}
