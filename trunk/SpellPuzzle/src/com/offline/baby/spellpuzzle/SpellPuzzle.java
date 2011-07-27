package com.offline.baby.spellpuzzle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.offline.baby.spellpuzzle.config.Settings;
import com.offline.baby.spellpuzzle.data.DBManager;

public class SpellPuzzle extends Game {

	private DBManager db;

	public SpellPuzzle(DBManager db) {
		this.db = db;
	}

	@Override
	public void create() {
		// Gdx.input.setCatchBackKey(true);

		setScreen(new Logo(this, new TextureRegion(Assets.loadTexture(
				"data/splash.png", true))));

		Assets.load();
		Settings.load();

		Settings.SHOW_FRAME = true;

		// setScreen(new MainScreen(this));
	}

	@SuppressWarnings("unchecked")
	public void onBackPressed() {
		((BaseScreen<Game>) getScreen()).showBackConfirm();
	}

	@SuppressWarnings("unchecked")
	public void onMenuPressed() {
		((BaseScreen<Game>) getScreen()).showMenu();
	}

	public DBManager getDb() {
		return db;
	}
}
