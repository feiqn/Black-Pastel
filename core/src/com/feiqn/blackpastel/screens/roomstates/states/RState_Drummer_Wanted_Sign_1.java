package com.feiqn.blackpastel.screens.roomstates.states;

import com.feiqn.blackpastel.dialog.CharacterExpression;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;
import com.feiqn.blackpastel.screens.VNScreen;
import com.feiqn.blackpastel.screens.roomstates.RoomObject;
import com.feiqn.blackpastel.screens.roomstates.RoomState;
import com.feiqn.blackpastel.story.Decision;

public class RState_Drummer_Wanted_Sign_1 extends RoomState {

    public RState_Drummer_Wanted_Sign_1() {
        super();
    }

    @Override
    public void layoutObjects(VNScreen parent) {
        super.layoutObjects(parent);

        // new RoomObject . add click listener parent.startConversation(RoomObject.associatedConversation);
        final RoomObject door = new RoomObject(5,5, 50,50);
        door.setDebug(true);
        parent.stage().addActor(door);
    }

    @Override
    public DialogScript getRailScript() {
        return new DialogScript() {
          @Override protected void setSeries()  {
              // 0
              set(CharacterExpression.NONE, "Huh...", DialogFrame.SpeakerPosition.FIRST_PERSON);
              lastFrame().setBackgroundID(DialogFrame.Background_ID.WANTED_SIGN);

              // 1
              set("Drummer Wanted...");

              // 2
              set("I haven't played drums since high school...");

              // 3
              set("...but, man, it sure was fun.");

              // 4
              set("...Maybe I should take a number...");
              lastFrame().addChoice("I bet mom still has my old kit in the attic!", 6);
              lastFrame().addChoice("...honestly, drumming sounds kind of lame.", 7);

              // 5
              set("I'm not sure how you reached this frame.");
              lastFrame().leadsToFrame(5);

              // 6
              set("Yeah!! What could possibly go wrong!");
              lastFrame().setTerminal(true);
              lastFrame().setsFlag(Decision.JOINED_THE_BAND_IMMEDIATELY, true);

              // 7
              set("Yeah, drumming is kind of lame...");

              // 8
              set("...which is why I'm a drummer!");
              lastFrame().leadsToFrame(9);

              // 9
              set("Maybe I should take a number!");
              lastFrame().addChoice("I bet mom still has my old kit in the attic!", 6);
              lastFrame().addChoice("...honestly, drumming sounds really lame.", 10);

              // 10
              set("Yeah. I'm sure the last drummer left for a good reason");
              lastFrame().setsFlag(Decision.WAS_KIND_OF_A_DICK_ON_THE_MENU_SCREEN, true);

              // 11
              set("GAME OVER");
          }
        };
    }

}
