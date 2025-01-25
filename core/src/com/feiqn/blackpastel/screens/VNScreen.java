package com.feiqn.blackpastel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.feiqn.blackpastel.BlackPastelGame;
import com.feiqn.blackpastel.dialog.CharacterExpression;
import com.feiqn.blackpastel.dialog.DialogFrame;
import com.feiqn.blackpastel.dialog.DialogScript;
import com.feiqn.blackpastel.screens.roomstates.RoomState;
import com.feiqn.blackpastel.story.StoryHandler;

public class VNScreen extends ScreenAdapter {

    private final BlackPastelGame game;

    private Stage gameStage;

    private Label vnLabel;

    private Stack vnStack;

    private Image backgroundImage;

    private DialogScript dialogScript;

    private RoomState roomState;

    private StoryHandler storyHandler;

    public VNScreen(BlackPastelGame game) {
        this.game = game;

        roomState = new RoomState(this);
        dialogScript = new DialogScript();
        vnLabel = new Label("", game.assetHandler().menuLabelStyle);
        vnStack = new Stack();

        storyHandler = new StoryHandler();

        backgroundImage = new Image(game.assetHandler().drummerWantedPosterTexture);
        backgroundImage.setColor(1,1,1,0);
    }

    @Override
    public void show() {
        super.show();


        gameStage = new Stage(new ScalingViewport(Scaling.fit, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        gameStage.setDebugAll(true);

        gameStage.addActor(vnStack);
        vnStack.setFillParent(true); // TODO: don't do this and do aspect ration world size something something chatgpt

        backgroundImage.addAction(Actions.fadeIn(3));

        loadRoomState(roomState);

//        buildConversationLayout();
    }

    private void buildConversationLayout() {
        vnStack.clearChildren();

        final Container<Label> labelContainer = new Container<>(vnLabel).top().left().pad(Gdx.graphics.getHeight() * .03f);
        labelContainer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int point, int button) {
                playNext();
            }
        });

        final Image labelShade = new Image(game.assetHandler().drummerWantedPosterTexture);
        labelShade.setColor(0,0,0,.7f);

        final Stack labelStack = new Stack();
        labelStack.add(labelShade);
        labelStack.add(labelContainer);

        final Table vnTable = new Table();
        vnTable.add(labelStack).width(Gdx.graphics.getWidth() * .8f).height(Gdx.graphics.getHeight() * .4f);
        vnTable.center();
        vnTable.padTop(Gdx.graphics.getHeight() * .4f);

        final Container<Table> tableContainer = new Container<>(vnTable);
        tableContainer.setFillParent(true);

        vnStack.add(backgroundImage);
        vnStack.add(tableContainer);
    }

    private void playNext() {
        final DialogFrame nextFrame = dialogScript.nextFrame();

        if(nextFrame.hasChoices()) {
            displayChoices(nextFrame);
        } else {
//            checkIfSpeakerAlreadyExistsInOtherSlot(nextFrame.getSpeaker(), nextFrame.getFocusedPosition());
//
//            if(nextFrame.isComplex()) {
//                layoutComplexFrame(nextFrame);
//            } else {
//                slot(nextFrame.getFocusedPosition()).update(nextFrame.getFocusedExpression(), nextFrame.isFacingLeft());
//            }
//
//            displayBackground(nextFrame.getBackground());
//
//            nameLabel.setText(nextFrame.getFocusedName());
//            moveNameLabel(nextFrame.getFocusedPosition());
//
//            displayDialog(nextFrame.getText(), nextFrame.getProgressiveDisplaySpeed(), nextFrame.getSnapToIndex());
//
//            if(nextFrame.usesDialogActions()) {
//                parseActions(nextFrame.getActions());
//            }
//
//            dimPortraitsExcept(nextFrame.getFocusedPosition());

//
//        if(nextFrame.autoAutoPlay()) {
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

    // Explicit method for handling user choice selection
    private void displayChoices(DialogFrame frame) {
        // TODO: convert AI abstract to local logic
//        Table choiceTable = new Table();
//
//        // Add a button for each choice
//        for (int i = 0; i < frame.getChoices().size; i++) {
//            final int choiceIndex = i; // Needed to reference the correct index
//            TextButton choiceButton = new TextButton(frame.getChoices().get(i), game.assetHandler.buttonStyle);
//
//            choiceButton.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    handleUserChoice(frame, choiceIndex); // Call the explicit handler
//                }
//            });
//
//            choiceTable.add(choiceButton).row(); // Add button to the table
//        }
//
//        // Replace the current layout with the choice table
//        layout.clear();
//        layout.add(choiceTable);
    }

    // Method to handle the user's choice
    private void handleUserChoice(DialogFrame frame, int choiceIndex) {
//        int nextIndex = frame.getNextFrameIndex(choiceIndex);
//
//        // Check if the choice leads to a new script or continues in the same script
//        if (nextIndex == -1 && frame.getChoices().get(choiceIndex).equals("Load Positive Branch")) {
//            // Example of switching to a new script dynamically
//            dialogScript = treeHandler.getScript("branch1");
//            dialogScript.setFrameIndex(0); // Start at the beginning of the new branch
//        } else {
//            // Continue with the current script
//            dialogScript.setFrameIndex(nextIndex);
//        }
//
//        // Play the next frame after handling the choice
//        playNext();
    }

    public void startConversation(DialogScript script) {

    }

    public void endConversation() {
//        if roomState.onRails -> storyHandler.progressStory();
//        else roomState.layoutObjects();
    }

    public void loadRoomState(RoomState roomState) {
        switch(roomState.getBackgroundID()) {
            // background = #image
        }
        if(roomState.isOnRails()) {
            startConversation(roomState.getRailScript());
        } else {
            roomState.layoutObjects();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.5f, 0, .5f, 1);

        gameStage.act();
        gameStage.draw();
    }


    /**
     * Helper class
     */
    private static class Slot {
        private final DialogFrame.SpeakerPosition position;
        private CharacterExpression speaker;

        public Slot(DialogFrame.SpeakerPosition pos) {
            this.position = pos;
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
