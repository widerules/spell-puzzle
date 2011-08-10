package com.offline.baby.spellpuzzle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	private static final String SOUNDS_DIR = "data/sounds/";

	private static Texture letters;
	private static Texture backgrounds;
	private static Texture settings;

	private static Texture animalButtons;
	private static Texture colorButtons;
	private static Texture jobButtons;
	private static Texture vegetablesButtons;
	private static Texture vehicleButtons;

	public static List<TextureRegion> letterList;

	public static TextureRegion A = new TextureRegion();
	public static TextureRegion B = new TextureRegion();
	public static TextureRegion C = new TextureRegion();
	public static TextureRegion D = new TextureRegion();
	public static TextureRegion E = new TextureRegion();
	public static TextureRegion F = new TextureRegion();
	public static TextureRegion G = new TextureRegion();
	public static TextureRegion H = new TextureRegion();
	public static TextureRegion I = new TextureRegion();
	public static TextureRegion J = new TextureRegion();
	public static TextureRegion K = new TextureRegion();
	public static TextureRegion L = new TextureRegion();
	public static TextureRegion M = new TextureRegion();
	public static TextureRegion N = new TextureRegion();
	public static TextureRegion O = new TextureRegion();
	public static TextureRegion P = new TextureRegion();
	public static TextureRegion Q = new TextureRegion();
	public static TextureRegion R = new TextureRegion();
	public static TextureRegion S = new TextureRegion();
	public static TextureRegion T = new TextureRegion();
	public static TextureRegion U = new TextureRegion();
	public static TextureRegion V = new TextureRegion();
	public static TextureRegion W = new TextureRegion();
	public static TextureRegion X = new TextureRegion();
	public static TextureRegion Y = new TextureRegion();
	public static TextureRegion Z = new TextureRegion();

	public static TextureRegion A_EPT = new TextureRegion();
	public static TextureRegion B_EPT = new TextureRegion();
	public static TextureRegion C_EPT = new TextureRegion();
	public static TextureRegion D_EPT = new TextureRegion();
	public static TextureRegion E_EPT = new TextureRegion();
	public static TextureRegion F_EPT = new TextureRegion();
	public static TextureRegion G_EPT = new TextureRegion();
	public static TextureRegion H_EPT = new TextureRegion();
	public static TextureRegion I_EPT = new TextureRegion();
	public static TextureRegion J_EPT = new TextureRegion();
	public static TextureRegion K_EPT = new TextureRegion();
	public static TextureRegion L_EPT = new TextureRegion();
	public static TextureRegion M_EPT = new TextureRegion();
	public static TextureRegion N_EPT = new TextureRegion();
	public static TextureRegion O_EPT = new TextureRegion();
	public static TextureRegion P_EPT = new TextureRegion();
	public static TextureRegion Q_EPT = new TextureRegion();
	public static TextureRegion R_EPT = new TextureRegion();
	public static TextureRegion S_EPT = new TextureRegion();
	public static TextureRegion T_EPT = new TextureRegion();
	public static TextureRegion U_EPT = new TextureRegion();
	public static TextureRegion V_EPT = new TextureRegion();
	public static TextureRegion W_EPT = new TextureRegion();
	public static TextureRegion X_EPT = new TextureRegion();
	public static TextureRegion Y_EPT = new TextureRegion();
	public static TextureRegion Z_EPT = new TextureRegion();

	public static Sound CLICK;

	public static Sound SOUND_A;
	public static Sound SOUND_B;
	public static Sound SOUND_C;
	public static Sound SOUND_D;
	public static Sound SOUND_E;
	public static Sound SOUND_F;
	public static Sound SOUND_G;
	public static Sound SOUND_H;
	public static Sound SOUND_I;
	public static Sound SOUND_J;
	public static Sound SOUND_K;
	public static Sound SOUND_L;
	public static Sound SOUND_M;
	public static Sound SOUND_N;
	public static Sound SOUND_O;
	public static Sound SOUND_P;
	public static Sound SOUND_Q;
	public static Sound SOUND_R;
	public static Sound SOUND_S;
	public static Sound SOUND_T;
	public static Sound SOUND_U;
	public static Sound SOUND_V;
	public static Sound SOUND_W;
	public static Sound SOUND_X;
	public static Sound SOUND_Y;
	public static Sound SOUND_Z;

	public static TextureRegion EPT = new TextureRegion();

	public static TextureRegion MAIN_BG;
	public static TextureRegion GAME_BG;
	public static TextureRegion MAIN_GRASS;

	public static TextureRegion MENU_SWITCH;
	public static TextureRegion MENU_SWITCH_BG;
	public static TextureRegion MENU_BG;
	public static TextureRegion MENU_INFO;
	public static TextureRegion MENU_HELP;
	public static TextureRegion MENU_SETTING;

	public static TextureRegion BTN_ANIMAL_UNPRESSED;
	public static TextureRegion BTN_ANIMAL_PRESSED;
	public static TextureRegion BTN_COLOR_UNPRESSED;
	public static TextureRegion BTN_COLOR_PRESSED;
	public static TextureRegion BTN_JOB_UNPRESSED;
	public static TextureRegion BTN_JOB_PRESSED;
	public static TextureRegion BTN_VEGETABLES_UNPRESSED;
	public static TextureRegion BTN_VEGETABLES_PRESSED;
	public static TextureRegion BTN_VEHICLE_UNPRESSED;
	public static TextureRegion BTN_VEHICLE_PRESSED;

	public static final int LETTER_WIDTH = 92;
	public static final int LETTER_HEIGHT = 92;

	public static TextureRegion SETTING_BG;
	public static TextureRegion SETTING_TITLE;
	public static TextureRegion SETTING_TIPS;
	public static TextureRegion SETTING_SWITCH_ON;
	public static TextureRegion SETTING_SWITCH_OFF;
	public static TextureRegion SETTING_BUTTON_UNPRESSED;
	public static TextureRegion SETTING_BUTTON_PRESSED;
	public static TextureRegion SETTING_APP_TITLE;

	public static void load() {
		letters = loadTexture("data/words.png");
		backgrounds = loadTexture("data/background.png", true);
		settings = loadTexture("data/setting.png", true);

		letterList = new ArrayList<TextureRegion>();
		letterList.add(A);
		letterList.add(B);
		letterList.add(C);
		letterList.add(D);
		letterList.add(E);
		letterList.add(F);
		letterList.add(G);
		letterList.add(H);
		letterList.add(I);
		letterList.add(J);
		letterList.add(K);
		letterList.add(L);
		letterList.add(M);
		letterList.add(N);
		letterList.add(O);
		letterList.add(P);
		letterList.add(Q);
		letterList.add(R);
		letterList.add(S);
		letterList.add(T);
		letterList.add(U);
		letterList.add(V);
		letterList.add(W);
		letterList.add(X);
		letterList.add(Y);
		letterList.add(Z);
		letterList.add(A_EPT);
		letterList.add(B_EPT);
		letterList.add(C_EPT);
		letterList.add(D_EPT);
		letterList.add(E_EPT);
		letterList.add(F_EPT);
		letterList.add(G_EPT);
		letterList.add(H_EPT);
		letterList.add(I_EPT);
		letterList.add(J_EPT);
		letterList.add(K_EPT);
		letterList.add(L_EPT);
		letterList.add(M_EPT);
		letterList.add(N_EPT);
		letterList.add(O_EPT);
		letterList.add(P_EPT);
		letterList.add(Q_EPT);
		letterList.add(R_EPT);
		letterList.add(S_EPT);
		letterList.add(T_EPT);
		letterList.add(U_EPT);
		letterList.add(V_EPT);
		letterList.add(W_EPT);
		letterList.add(X_EPT);
		letterList.add(Y_EPT);
		letterList.add(Z_EPT);
		letterList.add(EPT);

		int offsetX = 0;
		int offsetY = 0;

		for (int i = 0; i < letterList.size(); i++) {
			TextureRegion ltr = letterList.get(i);
			if (i % 5 == 0) {
				offsetX = 0;
			}
			offsetY = i / 5 * LETTER_HEIGHT;
			ltr.setRegion(new TextureRegion(letters, offsetX, offsetY,
					LETTER_WIDTH, LETTER_HEIGHT));
			offsetX += LETTER_WIDTH;
		}

		MAIN_BG = new TextureRegion(backgrounds, 0, 0, 480, 800);
		GAME_BG = new TextureRegion(backgrounds, 480, 0, 480, 800);
		MAIN_GRASS = new TextureRegion(backgrounds, 0, 904, 480, 120);

		MENU_SWITCH = new TextureRegion(backgrounds, 623, 946, 34, 20);
		MENU_SWITCH_BG = new TextureRegion(backgrounds, 545, 946, 78, 78);
		MENU_BG = new TextureRegion(backgrounds, 964, 739, 60, 285);
		MENU_INFO = new TextureRegion(backgrounds, 760, 946, 51, 52);
		MENU_HELP = new TextureRegion(backgrounds, 709, 946, 51, 52);
		MENU_SETTING = new TextureRegion(backgrounds, 658, 946, 51, 52);

		SOUND_A = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "a.ogg"));
		SOUND_B = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "b.ogg"));
		SOUND_C = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "c.ogg"));
		SOUND_D = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "d.ogg"));
		SOUND_E = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "e.ogg"));
		SOUND_F = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "f.ogg"));
		SOUND_G = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "g.ogg"));
		SOUND_H = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "h.ogg"));
		SOUND_I = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "i.ogg"));
		SOUND_J = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "j.ogg"));
		SOUND_K = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "k.ogg"));
		SOUND_L = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "l.ogg"));
		SOUND_M = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "m.ogg"));
		SOUND_N = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "n.ogg"));
		SOUND_O = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "o.ogg"));
		SOUND_P = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "p.ogg"));
		SOUND_Q = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "q.ogg"));
		SOUND_R = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "r.ogg"));
		SOUND_S = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "s.ogg"));
		SOUND_T = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "t.ogg"));
		SOUND_U = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "u.ogg"));
		SOUND_V = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "v.ogg"));
		SOUND_W = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "w.ogg"));
		SOUND_X = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "x.ogg"));
		SOUND_Y = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "y.ogg"));
		SOUND_Z = Gdx.audio.newSound(Gdx.files.internal(SOUNDS_DIR + "z.ogg"));

		CLICK = Gdx.audio
				.newSound(Gdx.files.internal(SOUNDS_DIR + "click.ogg"));

		animalButtons = loadTexture("data/cardtype_logo_animal.png");
		BTN_ANIMAL_UNPRESSED = new TextureRegion(animalButtons, 0, 0, 225, 245);
		BTN_ANIMAL_PRESSED = new TextureRegion(animalButtons, 225, 0, 225, 245);

		colorButtons = loadTexture("data/cardtype_logo_color_shape.png");
		BTN_COLOR_UNPRESSED = new TextureRegion(colorButtons, 0, 0, 225, 245);
		BTN_COLOR_PRESSED = new TextureRegion(colorButtons, 225, 0, 225, 245);
		jobButtons = loadTexture("data/cardtype_logo_job.png");
		BTN_JOB_UNPRESSED = new TextureRegion(jobButtons, 0, 0, 225, 245);
		BTN_JOB_PRESSED = new TextureRegion(jobButtons, 225, 0, 225, 245);
		vegetablesButtons = loadTexture("data/cardtype_logo_vegetables.png");
		BTN_VEGETABLES_UNPRESSED = new TextureRegion(vegetablesButtons, 0, 0, 225, 245);
		BTN_VEGETABLES_PRESSED = new TextureRegion(vegetablesButtons, 225, 0, 225, 245);
		vehicleButtons = loadTexture("data/cardtype_logo_vehicle.png");
		BTN_VEHICLE_UNPRESSED = new TextureRegion(vehicleButtons, 0, 0, 225, 245);
		BTN_VEHICLE_PRESSED = new TextureRegion(vehicleButtons, 225, 0, 225, 245);

		SETTING_BG = new TextureRegion(settings, 0, 0, 438, 652);
		SETTING_TITLE = new TextureRegion(settings, 0, 652, 178, 54);
		SETTING_TIPS = new TextureRegion(settings, 0, 706, 140, 272);
		SETTING_APP_TITLE = new TextureRegion(settings, 178, 652, 110, 88);
		SETTING_BUTTON_UNPRESSED = new TextureRegion(settings, 178, 740, 176,
				64);
		SETTING_BUTTON_PRESSED = new TextureRegion(settings, 178, 804, 176, 64);
		SETTING_SWITCH_ON = new TextureRegion(settings, 178, 868, 120, 30);
		SETTING_SWITCH_OFF = new TextureRegion(settings, 178, 898, 120, 30);
	}

	public static void loadLogoData() {

	}

	public static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static Texture loadTexture(String file, boolean transparent) {
		return new Texture(Gdx.files.internal(file),
				transparent ? Format.RGBA8888 : Format.RGB888, false);
	}
}
