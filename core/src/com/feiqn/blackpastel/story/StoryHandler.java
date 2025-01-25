package com.feiqn.blackpastel.story;

import com.badlogic.gdx.utils.Array;
import com.feiqn.blackpastel.characters.GameCharacter;
import com.feiqn.blackpastel.screens.roomstates.RoomState;
import com.feiqn.blackpastel.screens.roomstates.states.RState_Drummer_Wanted_Sign_1;

public class StoryHandler {

    private GameCharacter Alex;
    private GameCharacter Moe;
    private GameCharacter Riley;
    private GameCharacter Jay;
    private GameCharacter Kai;

    private Array<StoryFlag> storyFlags;

    private RoomState currentPointInStory;

    public StoryHandler() {
        Alex = new GameCharacter("Alex");
        Moe = new GameCharacter("Moe");
        Riley = new GameCharacter("Riley");
        Jay = new GameCharacter("Jay");
        Kai = new GameCharacter("Kai");

        storyFlags = new Array<>();

        currentPointInStory = new RState_Drummer_Wanted_Sign_1();

    }

    public RoomState currentRoomState() {
        return currentPointInStory;
    }

    public void updateRoomState(RoomState state) {
        this.currentPointInStory = state;
    }

    public void addStoryFlag(StoryFlag flag) {
        storyFlags.add(flag);
    }

//    public Boolean decisionFlag(StoryFlag flag) {
//        if(storyFlags.contains(flag, true)) {
//            return storyFlags.get(flag);
//        } else {
//            return false;
//        }
//    }

    public static class StoryFlag {
        private final Decision flag;
        private boolean state;

        public StoryFlag(Decision flag) {
            this.flag = flag;
            state = false;
        }

        public Decision id() {
            return flag;
        }

        public void set(Boolean bool) {
            state = bool;
        }

        public Boolean state() {
            return state;
        }
    }
}
