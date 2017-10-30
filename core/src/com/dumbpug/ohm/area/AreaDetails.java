package com.dumbpug.ohm.area;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.JsonValue;
import com.dumbpug.ohm.area.block.BlockDetails;

/**
 * Wrapper for the JSON area details file.
 */
public class AreaDetails {

    /** The area name. */
    private String areaName;

    /** The JSON area details.*/
    private JsonValue areaDetails;

    /** Populate the physics world with static blocks based on the area block map image. */
    private Pixmap blockPixmap;

    /**
     * Create a new instance of the AreaDetails class.
     * @param areaName
     * @param details
     */
    public AreaDetails(String areaName, JsonValue details) {
        this.areaName    = areaName;
        this.areaDetails = details;
        this.blockPixmap = new Pixmap(Gdx.files.internal("areas/" + areaName + "/block_map.png"));
    }

    /**
     * Get the spawn for the area.
     * @return spawn
     */
    public Spawn getSpawn() { return new Spawn(areaDetails.get("spawn")); }

    /**
     * Get the width of the area.
     * @return area width
     */
    public int getWidth() { return this.blockPixmap.getWidth(); }

    /**
     * Get the height of the area.
     * @return area height
     */
    public int getHeight() { return this.blockPixmap.getHeight(); }

    /**
     * Get the next area name.
     * @return next area name
     */
    public String getNextAreaName() { return areaDetails.getString("nextArea"); }

    /**
     * Gets whther there is a block at the specified position.
     * @return is block at position.
     */
    public boolean isBlockAt(int x, int y) {
        if (x < 0 || x >= this.getWidth()) {
            // This position is outside the horizontal bounds of the area.
            return false;
        } else if (y < 0 || y >= this.getHeight()) {
            // This position is outside the vertical bounds of the area.
            return false;
        } else {
            // Get the pixel colour.
            int pixelColour = blockPixmap.getPixel(x, y);
            // Does the pixel colour match the block pixel colour?
            return pixelColour == 255;
        }
    }

    /**
     * Gets the block details for the specified position.
     */
    public BlockDetails getBlockDetails(int x, int y) {
        // We can only continue if there is a block at this position.
        if (isBlockAt(x, y)) {
            // Create our block details object.
            BlockDetails blockDetails = new BlockDetails();
            // Set whether this block has adjoining blocks.
            blockDetails.setHasBlockLeft(isBlockAt(x - 1, y));
            blockDetails.setHasBlockAbove(isBlockAt(x, y + 1));
            blockDetails.setHasBlockRight(isBlockAt(x + 1, y));
            blockDetails.setHasBlockBelow(isBlockAt(x, y - 1));
            // Set the wire details for this block (if there are any).
            JsonValue wireDetails = getWireBlockDetails(x, y);
            if (wireDetails != null) {
                blockDetails.setWireDetails(wireDetails);
            }
            // Return our block details.
            return blockDetails;
        }
        // We had no luck, no block at this position.
        return null;
    }

    /**
     * Gets the wire block details for the specified position.
     */
    public JsonValue getWireBlockDetails(int x, int y) {
        // We can only continue if there were actually any wire blocks defined for this area.
        if (areaDetails.get("wire-blocks") != null) {
            // Iterate over every wire block defined in our JSON.
            for (JsonValue wireBlock : areaDetails.get("wire-blocks").iterator()) {
                // Is this wire block at the correct position?
                if (wireBlock.getInt("x") == x && wireBlock.getInt("y") == y) {
                    // We found the wire block we were after!
                    return wireBlock;
                }
            }
        }
        // We had no luck.
        return null;
    }
}
