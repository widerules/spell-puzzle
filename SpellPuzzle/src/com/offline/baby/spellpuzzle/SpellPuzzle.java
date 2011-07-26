package com.offline.baby.spellpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.offline.baby.spellpuzzle.config.Settings;

public class SpellPuzzle extends Game {

	@Override
	public void create() {
		// Gdx.input.setCatchBackKey(true);

		// setScreen(new Logo(this, new TextureRegion(Assets.loadTexture(
		// "data/splash.png", true))));

		Assets.load();
		Settings.load();

		Settings.SHOW_FRAME = true;

		setScreen(new MainScreen(this));
	}

	@SuppressWarnings("unchecked")
	public void onBackPressed() {
		((BaseScreen<Game>) getScreen()).showBackConfirm();
	}

	@SuppressWarnings("unchecked")
	public void onMenuPressed() {
		((BaseScreen<Game>) getScreen()).showMenu();
	}

}
