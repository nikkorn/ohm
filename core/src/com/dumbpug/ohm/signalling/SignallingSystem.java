package com.dumbpug.ohm.signalling;

import com.dumbpug.ohm.signalling.panel.Panel;
import java.util.ArrayList;

/**
 * Handles processing for a signalling system.
 */
public class SignallingSystem {

    /** The panels in the system. */
    private ArrayList<Panel> panels;

    /**
     * Update the panels in the signalling system.
     */
    public void update() {
        // Update each panel in turn.
        for (Panel panel : this.panels) {
            panel.update();
        }
    }

    /**
     * Add a panel to this signalling system.
     * @param panel
     */
    public void addPanel(Panel panel) { panels.add(panel); }
}