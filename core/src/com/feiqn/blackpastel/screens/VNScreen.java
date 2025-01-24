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

public class VNScreen extends ScreenAdapter {

    private final BlackPastelGame game;

    private final Stage vnStage;

    private final Label vnLabel;

    public VNScreen(BlackPastelGame game) {
        this.game = game;

        vnStage = new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        vnStage.setDebugAll(true);

        Stack vnStack = new Stack();
        vnStack.setFillParent(true);

        vnStage.addActor(vnStack);

        final Image background = new Image(game.assetHandler().drummerWantedPosterTexture);
        vnStack.add(background);

        background.setColor(1,1,1,0);
        background.addAction(Actions.fadeIn(5));

        vnLabel = new Label("Drummer wanted...", game.assetHandler().menuLabelStyle);
        vnLabel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int point, int button) {
                playNext();
            }
        });

        final Container<Label> labelContainer = new Container<>(vnLabel).top().left().pad(Gdx.graphics.getHeight() * .03f);
//        labelContainer.setFillParent(true);

        final Image labelShade = new Image(game.assetHandler().drummerWantedPosterTexture);
        labelShade.setColor(0,0,0,.5f);

        final Stack labelStack = new Stack();
        labelStack.add(labelShade);
        labelStack.add(labelContainer);

        final Table vnTable = new Table();
        vnTable.add(labelStack).width(Gdx.graphics.getWidth() * .8f).height(Gdx.graphics.getHeight() * .4f);
        vnTable.center();
        vnTable.padTop(Gdx.graphics.getHeight() * .4f);

        final Container<Table> tableContainer = new Container<>(vnTable);
        tableContainer.setFillParent(true);

        vnStack.add(tableContainer);
    }

    private void playNext() {

    }

    @Override
    public void show() {
        super.show();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.5f, 0, .5f, 1);

        vnStage.act();
        vnStage.draw();
    }

}
