package com.dumbpug.ohm.area.schedule;

import java.util.ArrayList;

/**
 * A schedule of actions for an entity to perform in order.
 */
public class Schedule {
    /** The actions to perform as part of this schedule. */
    private ArrayList<Action> actions = new ArrayList<Action>();
    /** The active action which is being processed. */
    private Action current;

    /**
     * Add an action to the schedule.
     * @param action The action to add.
     */
    public void addAction(Action action) { this.actions.add(action); }

    /**
     * Process the current action (if there are any).
     */
    public void process() {
        // Do we not already have a current action?
        if (this.current == null) {
            if (this.actions.size() > 0) {
                // We will be using the first action as the current one.
                Action initial = this.actions.get(0);
                // Reset this action just in case it was started before at some point.
                initial.reset();
                // Set the current action
                this.current = initial;

            } else {
                // We cannot perform an action as we do not have any!.
                return;
            }
        }
        // Determine whether the action is complete.
        if (this.current.isComplete()) {
            // Set the next available action as the current one.
            this.setNextActionAsCurrent();
        }
        // Perform the action.
        this.current.perform();
    }

    /**
     * Set the next available action as the current one.
     */
    private void setNextActionAsCurrent() {
        // Get the index of the current action in our actions list.
        int currentIndex = this.actions.indexOf(this.current);
        // Determine the index of the next available action. This could be
        // the same as the current one we have scheduled a single action.
        int nextActionIndex = currentIndex == (this.actions.size() - 1) ? 0 : currentIndex + 1;
        // Get the next action from our action list.
        Action next = this.actions.get(nextActionIndex);
        // Reset the next action.
        next.reset();
        // Set the next action as the current one.
        this.current = next;
    }
}
