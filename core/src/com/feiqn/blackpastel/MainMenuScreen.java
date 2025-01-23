package com.feiqn.blackpastel;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen extends ScreenAdapter {

    private final BlackPastelGame game;

    public OrthographicCamera menuCamera;

    public MainMenuScreen(BlackPastelGame game) {
        this.game = game;
    }
}
