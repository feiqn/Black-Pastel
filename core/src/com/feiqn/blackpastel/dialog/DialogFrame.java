package com.feiqn.blackpastel.dialog;

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

    private SpeakerPosition focusedPosition;
    private SpeakerPosition doubleSpeakPosition;

    private boolean autoplayNext;
    private boolean complex;
    private boolean doubleSpeak;

    public DialogFrame() {
        for(SpeakerPosition pos : SpeakerPosition.values()) {
            positionsMap.put(pos, CharacterExpression.NONE);
        }

        text            = "";
        doubleSpeakText = "";
        name            = "";
        doubleSpeakName = "";

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

    public void setAutoplayNext(boolean autoplayNext) {
        this.autoplayNext = autoplayNext;
    }

    public void setBackgroundID(Background_ID backgroundID) {
        this.backgroundID = backgroundID;
    }

    public void setComplex(boolean complex) {
        this.complex = complex;
    }

    public void setName(String name) {
        this.name = name;
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

    public Background_ID getBackgroundID() {
        return backgroundID;
    }

    public boolean isAutoplayNext() {
        return autoplayNext;
    }

    public boolean isComplex() {
        return complex;
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
