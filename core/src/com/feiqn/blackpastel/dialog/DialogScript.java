package com.feiqn.blackpastel.dialog;

import com.badlogic.gdx.utils.Array;

public class DialogScript {

    private int frameIndex;

    private final Array<DialogFrame> framesToDisplay; // Add frames programmatically in order

    public DialogScript() {
        framesToDisplay = new Array<>();
        frameIndex = 0;
        setSeries();
    }

    protected void setSeries() {
        // script goes here
    }



    public boolean continues() {
        return framesToDisplay.size > frameIndex;
    }

    private DialogFrame lastFrame() {
        return framesToDisplay.get(framesToDisplay.size-1);
    }

}
