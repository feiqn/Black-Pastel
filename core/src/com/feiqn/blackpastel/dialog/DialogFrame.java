package com.feiqn.blackpastel.dialog;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

public class DialogFrame {

    public enum Background_ID {
        NONE,
        REMOVE,
        BLACK,

        STAGE_SMALL,
        STAGE_LARGE,
        GARAGE,
        CAFE,
    }

    public enum SpeakerPosition {
        FAR_LEFT,
        LEFT,
        RIGHT,
        FAR_RIGHT
    }

    private Background_ID backgroundID;

    private final HashMap<SpeakerPosition, CharacterExpression> positionsMap = new HashMap<>();

    private String text;
    private String doubleSpeakText;
    private String name;
    private String doubleSpeakName;
    private String leadsToBranch;

    private int leadsToIndex;

    private SpeakerPosition focusedPosition;
    private SpeakerPosition doubleSpeakPosition;

    private Array<String> choices;         // User-visible choices
    private Array<Integer> nextFrameIndices; // Corresponding next frame indices

    private boolean autoplayNext;
    private boolean complex;
    private boolean doubleSpeak;
    private boolean terminal;

    public DialogFrame() {
        for(SpeakerPosition pos : SpeakerPosition.values()) {
            positionsMap.put(pos, CharacterExpression.NONE);
        }

        text            = "";
        doubleSpeakText = "";
        name            = "";
        doubleSpeakName = "";
        leadsToBranch   = "main";

        leadsToIndex = 0;

        focusedPosition     = SpeakerPosition.LEFT;
        doubleSpeakPosition = SpeakerPosition.RIGHT;

        backgroundID = Background_ID.NONE;

        doubleSpeak  = false;
        autoplayNext = false;
        complex      = false;
    }

    /**
     * Getters and setters.
     */

    public void setFocusedExpression(CharacterExpression expression) {
        positionsMap.put(focusedPosition, expression);
    }

    public void setAutoplayNext(boolean autoplayNext) {
        this.autoplayNext = autoplayNext;
    }

    public void setBackgroundID(Background_ID backgroundID) {
        this.backgroundID = backgroundID;
    }

    public void setComplex(boolean complex) {
        this.complex = complex;
    }

    public void setLeadsToIndex(int leadsToIndex) {
        this.leadsToIndex = leadsToIndex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeadsToBranch(String leadsToBranch) {
        this.leadsToBranch = leadsToBranch;
    }

    public void setDoubleSpeak(boolean doubleSpeak) {
        this.doubleSpeak = doubleSpeak;
    }

    public void setDoubleSpeakName(String doubleSpeakName) {
        this.doubleSpeakName = doubleSpeakName;
    }

    public void setDoubleSpeakPosition(SpeakerPosition doubleSpeakPosition) {
        this.doubleSpeakPosition = doubleSpeakPosition;
    }

    public void setDoubleSpeakText(String doubleSpeakText) {
        this.doubleSpeakText = doubleSpeakText;
    }

    public void setFocusedPosition(SpeakerPosition focusedPosition) {
        this.focusedPosition = focusedPosition;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public void addChoice(String theThingYoullSay, int theFrameIndexYoullGoToNextIfYouSayThis) {
        if(choices == null) choices = new Array<>();
        if(nextFrameIndices == null) nextFrameIndices = new Array<>();

        choices.add(theThingYoullSay);
        nextFrameIndices.add(theFrameIndexYoullGoToNextIfYouSayThis);
    }

    public boolean hasChoices() {
        return choices != null && choices.size > 0;
    }

    public Array<String> getChoices() {
        return choices;
    }

    public int getNextFrameIndex(int choiceIndex) {
        //"on THIS frame, choice number THIS, takes you to THAT frame"
        return nextFrameIndices.get(choiceIndex);
    }

    public Background_ID getBackgroundID() {
        return backgroundID;
    }

    public String leadsToBranch() {
        return leadsToBranch;
    }

    public boolean isAutoplayNext() {
        return autoplayNext;
    }

    public boolean isComplex() {
        return complex;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public int getLeadsToIndex() {
        return leadsToIndex;
    }

    public boolean isDoubleSpeak() {
        return doubleSpeak;
    }

    public SpeakerPosition getDoubleSpeakPosition() {
        return doubleSpeakPosition;
    }

    public SpeakerPosition getFocusedPosition() {
        return focusedPosition;
    }

    public String getDoubleSpeakName() {
        return doubleSpeakName;
    }

    public String getDoubleSpeakText() {
        return doubleSpeakText;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public HashMap<SpeakerPosition, CharacterExpression> getPositionsMap() {
        return positionsMap;
    }
}
