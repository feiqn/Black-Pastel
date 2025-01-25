package com.feiqn.blackpastel.screens.roomstates.states;

import com.feiqn.blackpastel.dialog.CharacterExpression;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;
import com.feiqn.blackpastel.screens.VNScreen;
import com.feiqn.blackpastel.screens.roomstates.RoomState;

public class RState_Drummer_Wanted_Sign_1 extends RoomState {

    public RState_Drummer_Wanted_Sign_1(VNScreen parent) {
        super(parent);
    }

    @Override
    public void layoutObjects() {
        // new RoomObject . add click listener parent.startConversation(RoomObject.associatedConversation);

    }

    @Override
    public DialogScript getRailScript() {
        return new DialogScript() {
          @Override protected void setSeries()  {
              set(CharacterExpression.NONE, "Huh...", DialogFrame.SpeakerPosition.FIRST_PERSON);
              lastFrame().setBackgroundID(DialogFrame.Background_ID.WANTED_SIGN);

              set("Drummer Wanted...");

              set("I haven't played drums since high school...");

              set("...but, man, it sure was fun.");

              set("...Maybe I'll take a number...");
          }
        };
    }

}
