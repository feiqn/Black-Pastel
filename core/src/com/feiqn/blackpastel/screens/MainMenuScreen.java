package com.feiqn.blackpastel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.feiqn.blackpastel.BlackPastelGame;

public class MainMenuScreen extends ScreenAdapter {

    private final BlackPastelGame game;

    private OrthographicCamera menuCamera;

    private Stage menuStage;

    private Group rootGroup;

    private Table menuTable;
    private Container<Table> menuTableContainer; // seems silly and redundant but chatgpt swears this is best practice

    public MainMenuScreen(BlackPastelGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Label titleLabel, newGameLabel;

        menuStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void render(float delta) {
        if(game.assetHandler().manager().update()) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glClearColor(0,0,0,1);

            menuStage.act();
            menuStage.draw();
        } else {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glClearColor(0,1,1,1);
        }
    }

    @Override
    public void resize(int width, int height) {

        menuStage.getViewport().update(width,height, true);
        menuStage.getCamera().update();
    }
}
