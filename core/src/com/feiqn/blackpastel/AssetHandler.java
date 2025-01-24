package com.feiqn.blackpastel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class AssetHandler {

    private final AssetManager manager;

    public Label.LabelStyle menuLabelStyle;

    public Texture alexTexture;
    public Texture moeTexture;
    public Texture rileyTexture;
    public Texture smallStageBGTexture;
    public Texture largeStageBGTexture;

    public Texture drummerWantedPosterTexture;


    public AssetHandler() {
        this.manager = new AssetManager();

        load();
        manager.finishLoading();
        initializeFont();
        initializeTextures();
    }

    private void load() {
        manager.load("ui/menu.png", Texture.class);
        manager.load("ngmenu.jpg", Texture.class);
    }

    private void initializeTextures() {
        drummerWantedPosterTexture = manager.get("ngmenu.jpg", Texture.class);
//        drummerWantedPosterTexture = n
    }

    private void initializeFont() {
        final Texture fontTexture = new Texture(Gdx.files.internal("ui/font/tinyFont.png"), true);
        fontTexture.setFilter(Texture.TextureFilter.MipMapNearestNearest, Texture.TextureFilter.Linear);

        BitmapFont tinyFont = new BitmapFont(Gdx.files.internal("ui/font/tinyFont.fnt"), new TextureRegion(fontTexture), false);
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ui/font/COPPERPLATE.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter menuFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        menuFontParameter.color = Color.WHITE;
        menuFontParameter.borderWidth = 1.5f;
        menuFontParameter.borderColor = Color.BLACK;
        menuFontParameter.size = 18;
        menuFontParameter.incremental = true;
        menuFontParameter.spaceX = 1;
        menuFontParameter.spaceY = 15;

        tinyFont.getData().markupEnabled = true;

        tinyFont = fontGenerator.generateFont(menuFontParameter);

        menuLabelStyle = new Label.LabelStyle();
        menuLabelStyle.font = tinyFont;

        fontGenerator.dispose();
    }

    public AssetManager manager() { return manager; }
}
