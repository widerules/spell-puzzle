package com.offline.baby.spellpuzzle.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.gdx.Gdx;

public final class Settings {

	public static boolean SHOW_FRAME = false;
	public static boolean WORD_SHAKE_ENABLED = true;
	public static boolean WORD_SOUNDS_ENABLED = true;
	public static boolean WORD_LETTER_ENABLED = true;
	public static boolean CUTLINE_SOUND_ENABLED = true;
	public static boolean CHINESE_DISPLAY_ENABLED = true;
	
	public static boolean CARD_RANDOM_ACCESS = true;
	
	public final static String file = ".spellpuzzle";

	public static void load() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(Gdx.files.external(
					file).read()));

			WORD_SHAKE_ENABLED = Boolean.parseBoolean(in.readLine());
			WORD_SOUNDS_ENABLED = Boolean.parseBoolean(in.readLine());
			WORD_LETTER_ENABLED = Boolean.parseBoolean(in.readLine());
			CUTLINE_SOUND_ENABLED = Boolean.parseBoolean(in.readLine());
			CHINESE_DISPLAY_ENABLED = Boolean.parseBoolean(in.readLine());

		} catch (Throwable e) {
			Gdx.app.log("ERROR", e.getMessage());
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
	}

	public static void save() {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(
					file).write(false)));
			out.write(Boolean.toString(WORD_SHAKE_ENABLED) + "\r");
			out.write(Boolean.toString(WORD_SOUNDS_ENABLED) + "\r");
			out.write(Boolean.toString(WORD_LETTER_ENABLED) + "\r");
			out.write(Boolean.toString(CUTLINE_SOUND_ENABLED) + "\r");
			out.write(Boolean.toString(CHINESE_DISPLAY_ENABLED) + "\r");

		} catch (Throwable e) {
			Gdx.app.log("ERROR", e.getMessage());
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
			}
		}
	}
}
