package com.feiqn.blackpastel.dialog;

import com.badlogic.gdx.utils.Array;

public class DialogScript {

    private int frameIndex;

//    private final Array<DialogFrame> framesToDisplay; // Add frames programmatically in order

    private DialogTree dialogTree;

    public DialogScript() {
//        framesToDisplay = new Array<>();
        frameIndex = 0;
        dialogTree = new DialogTree();
        setSeries();
    }

    protected void setSeries() {
        // script goes here

        // Main 1
//        set("One!");
//        lastFrame().addChoice("One", 2);
//        lastFrame().addChoice("One?", 3);

        // Main 2
//        set("1!")
//        lastFrame().leadsToIndex(4);

        // Main 3
//        set("0");

//        Main 4
//        set("Who?");
//        lastFrame().addChoice("me", 5);

        // Main 5


    }

    public DialogFrame nextFrame() {
        frameIndex++;
        return dialogTree.getBranch("main").get(frameIndex - 1);
    }

    public void setFrameIndex(int index) {
        this.frameIndex = index;
    }

    public boolean continues() {
        return dialogTree.getBranch("main").size > frameIndex;
    }

    private DialogFrame lastFrame() {
        return dialogTree.getBranch("main").get(dialogTree.getBranch("main").size-1);
    }

    /**
     * Builders
     */
    private void set(CharacterExpression expression, String txt, DialogFrame.SpeakerPosition position) {
        set("main", expression, txt, "", position, false, false);
    }
    private void set(String branch, CharacterExpression expression, String txt, DialogFrame.SpeakerPosition position) {
        set(branch, expression, txt, "", position, false, false);
    }
    private void set(CharacterExpression expression, String txt, DialogFrame.SpeakerPosition position, boolean flip) {
        set("main", expression, txt, "", position, flip, false);
    }
    private void set(CharacterExpression expression, String txt, DialogFrame.SpeakerPosition position, boolean flip, boolean autoNext) {
        set("main", expression, txt, "", position, flip, autoNext);
    }

    private void set(String branch, CharacterExpression expression, String txt, String name, DialogFrame.SpeakerPosition pos, boolean flip, boolean autoAutoPlay) {
        final DialogFrame frame = new DialogFrame();

        frame.setText(txt);
        frame.setFocusedPosition(pos);
        frame.setFocusedExpression(expression);
//        frame.setFacingLeft(flip);
//        frame.setAutoplayNext(autoAutoPlay);

        dialogTree.getBranch(branch).add(frame);
    }

    private static class Choice {

    }
}
