package com.dumbpug.ohm.area.block;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Details of a block.
 */
public class BlockDetails {

    /** Flags defining whether there are blocks next to this one. */
    private boolean hasBlockAbove = false;
    private boolean hasBlockBelow = false;
    private boolean hasBlockLeft  = false;
    private boolean hasBlockRight = false;

    /** Flag defining whether this is a wire block. */
    private boolean isWireBlock = false;

    /** Flags defining the wire entry points for the block. */
    private boolean hasEntryLeft   = false;
    private boolean hasEntryTop    = false;
    private boolean hasEntryRight  = false;
    private boolean hasEntryBottom = false;

    /**
     * Set the wire details for this block.
     * @param wireDetails
     */
    public void setWireDetails (JsonValue wireDetails) {
        // Mark this block as a wire block.
        this.isWireBlock = true;
        // TODO Set entry flags.
    }

    /**
     * Gets whether this is a wire block.
     * @return is wire block
     */
    public boolean isWireBlock() { return isWireBlock; }

    public boolean hasEntryTop() { return hasEntryTop; }

    public boolean hasEntryRight() { return hasEntryRight; }

    public boolean hasEntryBottom() { return hasEntryBottom; }

    public boolean hasEntryLeft() { return hasEntryLeft; }

    public boolean hasBlockAbove() { return hasBlockAbove; }

    public void setHasBlockAbove(boolean hasBlockAbove) { this.hasBlockAbove = hasBlockAbove; }

    public boolean hasBlockBelow() { return hasBlockBelow; }

    public void setHasBlockBelow(boolean hasBlockBelow) { this.hasBlockBelow = hasBlockBelow; }

    public boolean hasBlockLeft() { return hasBlockLeft; }

    public void setHasBlockLeft(boolean hasBlockLeft) { this.hasBlockLeft = hasBlockLeft; }

    public boolean hasBlockRight() { return hasBlockRight; }

    public void setHasBlockRight(boolean hasBlockRight) { this.hasBlockRight = hasBlockRight; }
}
