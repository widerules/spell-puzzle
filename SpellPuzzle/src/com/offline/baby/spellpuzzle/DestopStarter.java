package com.offline.baby.spellpuzzle;

import java.io.IOException;
import java.sql.SQLException;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.offline.baby.spellpuzzle.data.DBManagerDesctop;

public class DestopStarter {

	private final static int WINDOW_WIDTH = 480;
	private final static int WINDOW_HEIGHT = 800;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException, SQLException {
		new JoglApplication(new SpellPuzzle(new DBManagerDesctop()),
				"SpellPuzzle", WINDOW_WIDTH, WINDOW_HEIGHT, false);

	}
}
