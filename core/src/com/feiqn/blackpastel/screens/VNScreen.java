package com.feiqn.blackpastel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.feiqn.blackpastel.BlackPastelGame;

public class VNScreen extends ScreenAdapter {

    private final BlackPastelGame game;

    private final Stage vnStage;

    private final Group rootGroup;

    private final Stack vnStack;

    public VNScreen(BlackPastelGame game) {
        this.game = game;

        vnStage = new Stage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        vnStage.setDebugAll(true);

        rootGroup = new Group();

        vnStage.addActor(rootGroup);

        vnStack = new Stack();
        vnStack.setFillParent(true);

        rootGroup.addActor(vnStack);

        final Image background = new Image(game.assetHandler().drummerWantedPosterTexture);
        vnStack.add(background);

        final Table vnTable = new Table();
        vnTable.setFillParent(true);

        final Container<Table> vnContainer = new Container<>(vnTable);
        vnContainer.setFillParent(true);

        vnStack.add(vnContainer);
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
