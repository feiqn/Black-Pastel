package com.feiqn.blackpastel.story;

import com.feiqn.blackpastel.characters.GameCharacter;

import java.util.HashMap;

public class StoryHandler {

    private GameCharacter Alex;
    private GameCharacter Moe;
    private GameCharacter Riley;
    private GameCharacter Jay;
    private GameCharacter Kai;

    private HashMap<DecisionFlag, Boolean> storyDecisions;

    public StoryHandler() {
        Alex = new GameCharacter("Alex");
        Moe = new GameCharacter("Moe");
        Riley = new GameCharacter("Riley");
        Jay = new GameCharacter("Jay");
        Kai = new GameCharacter("Kai");

        storyDecisions = new HashMap<>();

    }

    public void addStoryFlag(DecisionFlag flag, Boolean bool) {
        storyDecisions.put(flag, bool);
    }

    public Boolean decisionFlag(DecisionFlag flag) {
        if(storyDecisions.containsKey(flag)) {
            return storyDecisions.get(flag);
        } else {
            return false;
        }
    }
}
