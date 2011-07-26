package com.offline.baby.spellpuzzle.android;

import java.io.IOException;

import android.os.Bundle;
import android.view.KeyEvent;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.offline.baby.spellpuzzle.SpellPuzzle;
import com.offline.baby.spellpuzzle.android.data.DBManagerAndroid;

public class SpellPuzzleActivity extends AndroidApplication {
	/** Called when the activity is first created. */

	private SpellPuzzle spellPuzzle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			spellPuzzle = new SpellPuzzle(new DBManagerAndroid());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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