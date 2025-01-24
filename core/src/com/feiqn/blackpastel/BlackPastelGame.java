package com.feiqn.blackpastel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.feiqn.blackpastel.screens.MainMenuScreen;
import com.feiqn.blackpastel.screens.VNScreen;

public class BlackPastelGame extends Game {
	SpriteBatch batch;

	private AssetHandler assetHandler;
	private VNScreen activeVN;

	@Override
	public void create () {
		batch = new SpriteBatch();
		assetHandler = new AssetHandler();

		setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public AssetHandler assetHandler() {
		return assetHandler;
	}

	public VNScreen activeVN() {
		return activeVN;
	}

	public void setActiveVN(VNScreen activeVN) {
		this.activeVN = activeVN;
	}

}
