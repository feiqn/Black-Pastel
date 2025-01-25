package com.feiqn.blackpastel.screens.roomstates;

import com.badlogic.gdx.utils.Array;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;
import com.feiqn.blackpastel.screens.VNScreen;

public class RoomState {

    private boolean onRails;

    private DialogFrame.Background_ID defaultBG;

    private Array<RoomObject> roomObjects;

    public RoomState() {
        onRails = true;
        defaultBG = DialogFrame.Background_ID.NONE;
    }
    
    public void layoutObjects(VNScreen parent) {
        // layout clickable things in daddy's stage with click listeners to start convo in daddy
        parent.setInputMode(VNScreen.InputMode.FREE_EXPLORE);
    }

    public DialogFrame.Background_ID getBackgroundID() {
        return defaultBG;
    }

    public void setBackgroundID(DialogFrame.Background_ID backgroundID) {
        this.defaultBG = backgroundID;
    }

    public boolean isOnRails() {
        return onRails;
    }

    public void derail() {
        this.onRails = false;
    }

    public DialogScript getRailScript() {
        return new DialogScript();
    }
}
