package com.dumbpug.ohm.area;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Wrapper for the JSON area details file.
 */
public class AreaDetails {

    /** The JSON area details.*/
    private JsonValue areaDetails;

    /**
     * Create a new instance of the AreaDetails class.
     * @param details
     */
    public AreaDetails(JsonValue details) { this.areaDetails = details; }

    /**
     * Get the spawn for the area.
     * @return spawn
     */
    public Spawn getSpawn() { return new Spawn(areaDetails.get("spawn")); }

    /**
     * Get the next area name.
     * @return next area name
     */
    public String getNextAreaName() { return areaDetails.getString("nextArea"); }

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
