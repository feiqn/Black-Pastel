package com.feiqn.blackpastel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.feiqn.blackpastel.BlackPastelGame;
import com.feiqn.blackpastel.dialog.CharacterExpression;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;
import com.feiqn.blackpastel.screens.roomstates.RoomObject;
import com.feiqn.blackpastel.screens.roomstates.RoomState;
import com.feiqn.blackpastel.story.StoryHandler;

import java.util.HashMap;

public class VNScreen extends ScreenAdapter {

    public enum InputMode {
        FREE_EXPLORE,
        LINEAR_DIALOG,
        DIALOG_CHOICE,
        MENU,
    }

    private final BlackPastelGame game;

    private Stage gameStage;

    private Label vnLabel;

    private Stack vnStack;
    private Stack nameStack;

    private Image backgroundImage;

    private DialogScript dialogScript;

    private StoryHandler storyHandler;

    private Array<Slot> slots;

    private InputMode inputMode;

    private int nextIndex;

    private DialogFrame currentlyHeldFrame;

    public VNScreen(BlackPastelGame game) {
        this.game = game;

        dialogScript = new DialogScript();

        vnLabel = new Label("", game.assetHandler().menuLabelStyle);
        vnLabel.setWrap(true);

        vnStack = new Stack();
        nameStack = new Stack();

        final Image labelShade = new Image(game.assetHandler().drummerWantedPosterTexture);
        labelShade.setColor(0,0,0,.7f);

        final Label nameLabel = new Label("", game.assetHandler().menuLabelStyle);

//        nameStack.add(labelShade);
//        nameStack.add(nameLabel);
//        nameStack.setSize(nameLabel.getPrefWidth(), nameLabel.getPrefHeight());

        storyHandler = new StoryHandler();

        slots = new Array<>();

        backgroundImage = new Image(game.assetHandler().drummerWantedPosterTexture);
        backgroundImage.setColor(1,1,1,0);

        nextIndex = 0;
    }

    @Override
    public void show() {
        super.show();

        gameStage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        gameStage.getCamera().update();

        gameStage.setDebugAll(true);

        gameStage.addActor(vnStack);
//        gameStage.addActor(nameStack);
        Gdx.input.setInputProcessor(gameStage);

//        vnStack.setFillParent(true); // TODO: don't do this and do aspect ration world size something something chatgpt

        backgroundImage.addAction(Actions.fadeIn(3));

        slots.add(new Slot(DialogFrame.SpeakerPosition.FAR_LEFT));
        slots.add(new Slot(DialogFrame.SpeakerPosition.LEFT));
        slots.add(new Slot(DialogFrame.SpeakerPosition.RIGHT));
        slots.add(new Slot(DialogFrame.SpeakerPosition.FAR_RIGHT));

        loadRoomState(storyHandler.currentRoomState());

//        buildConversationLayout();
    }

    private void buildConversationLayout() {
        if(this.inputMode != InputMode.LINEAR_DIALOG) inputMode = InputMode.LINEAR_DIALOG;

        vnStack.clear();
        vnStack.setSize(gameStage.getWidth(), gameStage.getHeight());

        final Container<Label> labelContainer = new Container<>(vnLabel)
                .top().left().pad(gameStage.getHeight() * .03f).width(gameStage.getWidth() * .75f);

        final Image labelShade = new Image(game.assetHandler().drummerWantedPosterTexture);
        labelShade.setColor(0,0,0,.7f);

        final Stack labelStack = new Stack();
        labelStack.add(labelShade);
        labelStack.add(labelContainer);

        labelStack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int point, int button) {
//                Gdx.app.log("container", "clicked");
                if(dialogScript.continues()) {
                    playNext();
                } else {
                    endConversation();
                }
            }
        });

        final Table vnTable = new Table();

        // character images here
        for(Slot slot : slots) {
            if(slot.speaker != CharacterExpression.NONE) {
                vnTable.add(new Image(drawableFromExpression(slot.speaker)));

            }
        }

        vnTable.row();
        vnTable.add(labelStack).width(gameStage.getWidth() * .8f).height(gameStage.getHeight() * .4f);
        vnTable.center();
        vnTable.padTop(gameStage.getHeight() * .4f);

        final Container<Table> tableContainer = new Container<>(vnTable);
        tableContainer.setFillParent(true);

        vnStack.add(backgroundImage);
        vnStack.add(tableContainer);
    }

    private void buildChoiceLayout() {
        if(this.inputMode != InputMode.DIALOG_CHOICE) inputMode = InputMode.DIALOG_CHOICE;

        vnStack.clear();
//        vnStack.setSize(gameStage.getWidth(), gameStage.getHeight());

        final Container<Label> labelContainer = new Container<>(vnLabel)
                .top().left()
                .pad(gameStage.getHeight() * .03f)
                .width(gameStage.getWidth() * .75f);

        final Image labelShade = new Image(game.assetHandler().drummerWantedPosterTexture);
        labelShade.setColor(0,0,0,.7f);

        final Stack labelStack = new Stack();
        labelStack.add(labelShade);
        labelStack.add(labelContainer);

        final Table vnTable = new Table();

        for(final DialogFrame.Choice choice : currentlyHeldFrame.getChoices()) {
            final Label labeler = new Label(choice.getText(), game.assetHandler().menuLabelStyle);
            labeler.setWrap(true);

            labeler.setColor(Color.CYAN);

            final Image labelShader = new Image(game.assetHandler().drummerWantedPosterTexture);
            labelShader.setColor(0,0,0,.7f);

            final Container<Label> labelContainerer = new Container<>(labeler)
                    .top().left()
                    .pad(gameStage.getHeight() * .03f)
                    .width(gameStage.getWidth() * .75f);

            final Stack labelStacker = new Stack();
            labelStacker.add(labelShader);
            labelStacker.add(labelContainerer);

            labeler.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int point, int button) {
//                Gdx.app.log("container", "clicked");
                    if(dialogScript.continues()) {
                        nextIndex = choice.getLeadsToIndex();
                        playNext();
                    } else {
                        endConversation();
                    }
                }
            });

            vnTable.add(labelStacker);
            vnTable.row();
        }

        vnTable.add(labelStack).width(gameStage.getWidth() * .8f).height(gameStage.getHeight() * .4f);
        vnTable.center();
        vnTable.pad(gameStage.getHeight() * .01f);

        final Container<Table> tableContainer = new Container<>(vnTable);
        tableContainer.setFillParent(true);

        vnStack.add(backgroundImage);
        vnStack.add(tableContainer);
    }

    private void playNext() {
        if(currentlyHeldFrame != null && currentlyHeldFrame.isTerminal()) {
            Gdx.app.log("TERMINAL", "TERMINAL FRAME PASSED");
            endConversation();
        } else {
            currentlyHeldFrame = dialogScript.frameAtIndex(nextIndex);
            nextIndex = currentlyHeldFrame.followingFrameIndex();

            displayBackground(currentlyHeldFrame.getBackgroundID());

            if(currentlyHeldFrame.setsFlags()) {
                // set flags
//            currentlyHeldFrame.getFlags().get(currentlyHeldFrame.getFlags()
//            storyHandler.addStoryFlag();
            }

            if(currentlyHeldFrame.hasChoices()) {
                buildChoiceLayout();

            } else {
                if(inputMode != InputMode.LINEAR_DIALOG) buildConversationLayout();

                checkIfSpeakerAlreadyExistsInOtherSlot(currentlyHeldFrame.getName(), currentlyHeldFrame.getFocusedPosition());
                dimPortraitsExcept(currentlyHeldFrame.getFocusedPosition());
                moveNameLabel(currentlyHeldFrame.getFocusedPosition());

                if(currentlyHeldFrame.isComplex()) {
                    layoutComplexFrame(currentlyHeldFrame);
                } else {
//                slot(currentlyHeldFrame.getFocusedPosition()).update(currentlyHeldFrame.getFocusedExpression(), currentlyHeldFrame.isFacingLeft());
//                final HashMap<DialogFrame.SpeakerPosition, CharacterExpression> h = currentlyHeldFrame.getPositionsMap();
//                for(DialogFrame.SpeakerPosition pos : h.keySet()) {
//                    slot(pos).speaker = h.get(pos);
//                }

                    slot(currentlyHeldFrame.getFocusedPosition()).speaker = currentlyHeldFrame.getPositionsMap().get(currentlyHeldFrame.getFocusedPosition());
                    // What an ugly and confusing line.
                    // It makes the slot know what to draw in the table cell.

                    buildConversationLayout();
                }

            }

//        if(nameStack.getChild(1) instanceof Label) ((Label) nameStack.getChild(1)).setText(currentlyHeldFrame.getName());
            // TODO: more name logic

            displayDialog(currentlyHeldFrame.getText());

//            if(currentlyHeldFrame.usesDialogActions()) {
//                parseActions(currentlyHeldFrame.getActions());
//            }

//        if(currentlyHeldFrame.autoAutoPlay()) {
//            // TODO: allow input no
//            game.activeGridScreen.setInputMode(GridScreen.InputMode.LOCKED);
//            Timer.schedule(new Timer.Task() {
//                @Override
//                public void run() {
//                    game.activeGridScreen.setInputMode(GridScreen.InputMode.CUTSCENE);
//                    playNext();
//                    // TODO: allow input yes
//                }
//            }, 1f); // TODO: dynamic wait time
//        }

        }

    }

    private void displayDialog(CharSequence sequence) {
        vnLabel.setText(sequence);
//        vnLabel.setWidth(backgroundImage.getWidth() * .6f);
    }

    private void fadeBackgroundToBlack() {
        vnStack.getChild(0).addAction(Actions.fadeOut(3));
    }

    private void fadeBackgroundInFromBlack() {
        vnStack.getChild(0).addAction(Actions.fadeOut(1));
    }

    private void displayBackground(DialogFrame.Background_ID backgroundID) {
//        if(this.storyHandler.currentRoomState().getBackgroundID() != backgroundID && backgroundID != DialogFrame.Background_ID.NONE) {
//            boolean fadeIn = false;
//            boolean fadeFromBlack = false;
//
//            if(this.storyHandler.currentRoomState().getBackgroundID() == DialogFrame.Background_ID.NONE) {
//                fadeIn = true;
//            } else if(this.storyHandler.currentRoomState().getBackgroundID() == DialogFrame.Background_ID.BLACK) {
//                fadeFromBlack = true;
//            }
//
////            if(backgroundID == DialogFrame.Background_ID.REMOVE) {
////                this.roomState.getBackgroundID() = DialogFrame.Background_ID.NONE;
////            } else {
////                this.roomState.getBackgroundID() = backgroundID;
////            }
//
//
//            switch(backgroundID) {
//                case BLACK:
//                    fadeBackgroundToBlack();
//                    break;
//
//                case REMOVE:
////                    hideBackground();
////                    fadeBackgroundInFromBlack();
//                    break;
//
//                default:
////                    final Texture t = new Texture(Gdx.files.internal("test/stage.jpg"));
////                    final TextureRegion r = new TextureRegion(t, 625, 450, 550,500);
////                    backgroundImage.setDrawable(new TextureRegionDrawable(r));
//                    break;

//            }

//            if(fadeIn) this.backgroundImage.addAction(Actions.fadeIn(1));
//            if(fadeFromBlack) fadeBackgroundInFromBlack();
//        }
    }

    protected void layoutComplexFrame(DialogFrame frame) {
//        for(SpeakerSlot slot : slots) {
//            slot.clearSlot();
//            slot.update(frame.getExpressionAtPosition(slot.speakerPosition), frame.isFacingLeft());
//        }
    }

    protected void dimPortraitsExcept(DialogFrame.SpeakerPosition focusedPosition) {
        // set all character portraits to dim,
        // then brighten up the focus again
//        for(SpeakerSlot slot : slots) {
//            if(slot.used) {
//                if(slot.speakerPosition == focusedPosition) {
//                    slot.brighten();
//                } else {
//                    slot.dim();
//                }
//            }
//        }
    }

    protected void checkIfSpeakerAlreadyExistsInOtherSlot(String speakerName, DialogFrame.SpeakerPosition skippedPosition) {
//        for(SpeakerSlot slot : slots) {
//            if(slot.speakerRoster == speaker && slot.speakerPosition != skippedPosition) {
//                slot.clearSlot();
//            }
//        }
    }

    protected void moveNameLabel(DialogFrame.SpeakerPosition position) {
//        nameTable.clearChildren();
//        nameTable.center();
//
//        for(SpeakerPosition pos : SpeakerPosition.values()) {
//            if(position == pos) {
//                nameTable.add(nameLabel).width(layout.getWidth() / 8).uniform();
//            } else {
//                nameTable.add().uniform();
//            }
//        }

//        nameStack.addAction(Actions.moveTo(slot(position).));

    }



    public void startConversation(DialogScript script) {
        inputMode = InputMode.LINEAR_DIALOG;
        buildConversationLayout();
        this.dialogScript = script;
        playNext();
    }

    public void endConversation() {
        /* Don't become confused by the seeming continuity of the spaces which
         * RoomState(s) represent. Space with a same background are not the same space.
         * Entering a room with an onRails conversation, and then the conversation ending
         * with you in the same room but now explorable, is a completely new RoomState
         */
        vnStack.clear();
        vnStack.add(new Label("To be continued...", game.assetHandler().menuLabelStyle));
//        if roomState.onRails -> storyHandler.progressStory();
//        else roomState.layoutObjects();
    }

    private Slot slot(DialogFrame.SpeakerPosition position) {
    switch(position) {
        case FAR_LEFT:
            return slots.get(0);
        case LEFT:
            return slots.get(1);
        case RIGHT:
            return slots.get(5);
        case FAR_RIGHT:
            return slots.get(6);
    }
    return slots.get(1);
}

    protected void storyMode() {

    }

    protected void transitionToRoomState(RoomState roomState) {
        // transroomanism.
        vnStack.addAction(Actions.sequence(Actions.fadeOut(6), Actions.run(new Runnable() {
            @Override
            public void run() {
                // transition code
                // same
            }
        })));
    }

    protected void loadRoomState(RoomState roomState) {
//        this.roomState = roomState;
        switch(roomState.getBackgroundID()) {
            // background = #image
        }
        if(roomState.isOnRails()) {
            startConversation(roomState.getRailScript());
        } else {
            roomState.layoutObjects(this);
        }
    }

    private Drawable drawableFromExpression(CharacterExpression characterExpression) {
        switch(characterExpression) {
            case RILEY_SMILING:
            case ALEX_SMILING:
            case MOE_SMILING:
            case KAI_SMILING:
            case JAY_SMILING:
            case NONE:
            default:
                return null;
        }
    }


    // --------------- TEST
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    final RoomObject door = new RoomObject(5,5, 50,50);
    // --------------- TEST

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.5f, 0, .5f, 1);

        gameStage.act();
        gameStage.draw();

        // --------------- TEST
        // Draw the clickable area
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(door.clickable.x, door.clickable.y, door.clickable.width, door.clickable.height);
        shapeRenderer.end();
        // --------------- TEST

    }

    @Override
    public void resize(int width, int height) {

        gameStage.getViewport().update(width, height, true);
        gameStage.getCamera().update();

        switch (inputMode) {
            case FREE_EXPLORE:
                storyHandler.currentRoomState().layoutObjects(this);
                break;
            case LINEAR_DIALOG:
                buildConversationLayout();
                break;
            case DIALOG_CHOICE:
                // do stuff
                break;
            case MENU:
                // do menu stuff
                break;
        }
    }

    public Stage stage() {
        return gameStage;
    }

    public InputMode getInputMode() {
        return inputMode;
    }

    public void setInputMode(InputMode inputMode) {
        this.inputMode = inputMode;
    }

    /**
     * Helper class
     */
    private static class Slot {
        private final DialogFrame.SpeakerPosition position;
        private CharacterExpression speaker;

        public Slot(DialogFrame.SpeakerPosition pos) {
            this.position = pos;
            speaker = CharacterExpression.NONE;
        }

        public String speakerName() {
            switch(speaker) {
                case JAY_SMILING:
                    return "Jay";
                case KAI_SMILING:
                    return "Kai";
                case MOE_SMILING:
                    return "Moe";
                case ALEX_SMILING:
                    return "Alex";
                case RILEY_SMILING:
                    return "Riley";
                case NONE:
                default:
                    return "";
            }
        }
    }
}
