package com.dumbpug.ohm.signalling.panel;

import com.dumbpug.ohm.Constants;
import com.dumbpug.ohm.signalling.receivers.SignalReceiver;
import com.dumbpug.ohm.signalling.transmitters.SignalTransmitter;
import java.util.ArrayList;

/**
 * A panel on which signals are fed in via transmitters and fed out to receivers.
 */
public class Panel {

    /** The transmitters feeding signals into this panel. */
    private ArrayList<SignalTransmitter> transmitters;

    /** The receivers consuming signals from this panel. */
    private ArrayList<SignalReceiver> receivers;

    /** The panel squares which make up this panel. */
    private PanelSquare[][] panelSquares = new PanelSquare[Constants.SIGNALLING_PANEL_SIZE][Constants.SIGNALLING_PANEL_SIZE];

    /**
     * Update the panel.
     */
    public void update() {
        // TODO Request signals from attached transmitters if due.
        // TODO Update signal positions if due.
        // TODO Send signal to attached receivers if signal makes its way to output on panel.
    }
}