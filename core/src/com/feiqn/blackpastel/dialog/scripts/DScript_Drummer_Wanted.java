package com.feiqn.blackpastel.dialog.scripts;

import com.feiqn.blackpastel.dialog.CharacterExpression;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;



public class DScript_Drummer_Wanted extends DialogScript {

    /**
     * Define DSripts in RoomState instead, unless doing something big.
     */

    public DScript_Drummer_Wanted() {
        super();
    }

    @Override protected void setSeries() {
        set(CharacterExpression.NONE, "Huh...", DialogFrame.SpeakerPosition.FIRST_PERSON);
        lastFrame().setBackgroundID(DialogFrame.Background_ID.WANTED_SIGN);

        set("Drummer Wanted...");

        set("I haven't played drums since high school...");

        set("...but, man, it sure was fun.");

        set("...Maybe I'll take a number...");
    }
}
