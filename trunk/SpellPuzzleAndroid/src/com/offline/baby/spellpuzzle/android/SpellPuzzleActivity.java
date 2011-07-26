package com.offline.baby.spellpuzzle.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.offline.baby.spellpuzzle.SpellPuzzle;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class SpellPuzzleActivity extends AndroidApplication {
	/** Called when the activity is first created. */

	private SpellPuzzle spellPuzzle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spellPuzzle = new SpellPuzzle();
		initialize(spellPuzzle, false);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			spellPuzzle.onBackPressed();
			return true;
		case KeyEvent.KEYCODE_BACK:
			spellPuzzle.onBackPressed();
			return true;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}