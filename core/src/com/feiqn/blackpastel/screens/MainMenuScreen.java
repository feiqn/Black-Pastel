package com.feiqn.blackpastel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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

    public MainMenuScreen(BlackPastelGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        menuStage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        final Label titleLabel = new Label("DRUMMER WANTED", game.assetHandler().menuLabelStyle);

        final Label newGameLabel = new Label("Join the Band!", game.assetHandler().menuLabelStyle);
        newGameLabel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int point, int button) {

                menuStage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        VNScreen screen = new VNScreen(game);
                        game.setActiveVN(screen);
                        game.setScreen(screen);
                    }
                })));


            }
        });


        final Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.add(titleLabel);
        menuTable.row();
        menuTable.add(newGameLabel);
        menuTable.center();

//        menuTable.setDebug(true);

//        final Container<Table> menuTableContainer = new Container<>(menuTable).center();
//        menuTableContainer.setFillParent(true);
//        menuTableContainer.center();
//        menuStage.addActor(menuTableContainer);

        menuStage.addActor(menuTable);

        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        if(game.assetHandler().manager().update()) {
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glClearColor(1,0,1,1);

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
