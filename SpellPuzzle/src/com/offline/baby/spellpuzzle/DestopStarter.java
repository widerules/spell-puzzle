package com.offline.baby.spellpuzzle;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DestopStarter {

	private final static int WINDOW_WIDTH = 480;
	private final static int WINDOW_HEIGHT = 800;

	public static void main(String[] args) {
		new JoglApplication(new SpellPuzzle(), "SpellPuzzle", WINDOW_WIDTH,
				WINDOW_HEIGHT, false);
	}
}
