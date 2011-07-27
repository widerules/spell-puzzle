package com.offline.baby.spellpuzzle.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.offline.baby.spellpuzzle.Assets;
import com.offline.baby.spellpuzzle.widget.collision.CollisionUtils;
import com.offline.baby.spellpuzzle.widget.collision.RectCollider;

public class Letter extends MovableButton implements RectCollider, IPlay {

	public interface OnTouchDown {
		void touchDown(Letter letter);
	}

	public interface OnTouchUp {
		void touchUp(Letter letter);
	}

	public static final int LETTER_WIDTH = 92;
	public static final int LETTER_HEIGHT = 92;

	public Letter(String name, String letter, TextureRegion region) {
		super(name, region, region);
		this.letter = letter;
		rect = new Rectangle(0, 0, width, height);
	}

	private String letter;
	public OnTouchDown onTouchDown;
	public OnTouchUp onTouchUp;
	public boolean canScale = true;
	private boolean inCollision = false;
	private boolean isFilled = false;
	private boolean isRead = false;
	private boolean canPlay = true;

	private Rectangle rect;

	public String getLetter() {
		return letter;
	}

	@Override
	protected boolean touchDown(float x, float y, int pointer) {
		boolean flag = super.touchDown(x, y, pointer);

		if (touchable) {
			if (flag) {
				if (canPlay) {
					play();
				}

				if (onTouchDown != null) {
					onTouchDown.touchDown(this);
				}

				if (!locked && canScale) {
					scaleX = 1.1f;
					scaleY = 1.1f;
				}
			}
		}

		return flag;
	}

	@Override
	protected boolean touchUp(float x, float y, int pointer) {
		boolean flag = super.touchUp(x, y, pointer);
		if (touchable) {
			if (flag) {
				if (onTouchUp != null) {
					onTouchUp.touchUp(this);
				}

				if (!locked && canScale) {
					scaleX = 1.0f;
					scaleY = 1.0f;
				}
			}
		}
		return flag;
	}

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	public static List<Letter> from(String word) {
		List<Letter> letterList = new ArrayList<Letter>();
		if (word != null && word.length() > 0) {
			for (int i = 0; i < word.length(); i++) {
				letterList.add(valueOf(word.charAt(i), false));
			}
		}
		return letterList;
	}

	public static List<Letter> emptyWordFrom(String word) {
		List<Letter> letterList = new ArrayList<Letter>();
		if (word != null && word.length() > 0) {
			for (int i = 0; i < word.length(); i++) {
				letterList.add(valueOf(word.charAt(i), true));
			}
		}
		return letterList;
	}

	public static List<Letter> emptyFrom(String word) {
		List<Letter> letterList = new ArrayList<Letter>();
		if (word != null && word.length() > 0) {
			for (int i = 0; i < word.length(); i++) {
				letterList.add(new Letter("Letter_"
						+ String.valueOf(word.charAt(i)).toUpperCase(), String
						.valueOf(word.charAt(i)).toLowerCase(), Assets.EPT));
			}
		}
		return letterList;
	}

	public static Letter valueOf(char c, boolean empty) {

		switch (c) {
		case 65:
		case 97:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.A_EPT
							: Assets.A);
		case 66:
		case 98:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.B_EPT
							: Assets.B);
		case 67:
		case 99:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.C_EPT
							: Assets.C);
		case 68:
		case 100:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.D_EPT
							: Assets.D);
		case 69:
		case 101:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.E_EPT
							: Assets.E);
		case 70:
		case 102:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.F_EPT
							: Assets.F);
		case 71:
		case 103:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.G_EPT
							: Assets.G);
		case 72:
		case 104:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.H_EPT
							: Assets.H);
		case 73:
		case 105:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.I_EPT
							: Assets.I);
		case 74:
		case 106:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.J_EPT
							: Assets.J);
		case 75:
		case 107:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.K_EPT
							: Assets.K);
		case 76:
		case 108:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.L_EPT
							: Assets.L);
		case 77:
		case 109:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.M_EPT
							: Assets.M);
		case 78:
		case 110:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.N_EPT
							: Assets.N);
		case 79:
		case 111:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.O_EPT
							: Assets.O);
		case 80:
		case 112:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.P_EPT
							: Assets.P);
		case 81:
		case 113:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.Q_EPT
							: Assets.Q);
		case 82:
		case 114:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.R_EPT
							: Assets.R);
		case 83:
		case 115:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.S_EPT
							: Assets.S);
		case 84:
		case 116:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.T_EPT
							: Assets.T);
		case 85:
		case 117:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.U_EPT
							: Assets.U);
		case 86:
		case 118:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.V_EPT
							: Assets.V);
		case 87:
		case 119:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.W_EPT
							: Assets.W);
		case 88:
		case 120:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.X_EPT
							: Assets.X);
		case 89:
		case 121:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.Y_EPT
							: Assets.Y);
		case 90:
		case 122:
			return new Letter("Letter_" + String.valueOf(c).toUpperCase(),
					String.valueOf(c).toLowerCase(), empty ? Assets.Z_EPT
							: Assets.Z);
		default:
			throw new IllegalArgumentException("Invalid argument: " + c);
		}
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public boolean canPlay() {
		return canPlay;
	}

	public void setCanPlay(boolean canPlay) {
		this.canPlay = canPlay;
	}

	@Override
	public boolean isRead() {
		return isRead;
	}

	@Override
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public boolean isCollision() {
		return this.inCollision;
	}

	@Override
	public void setInCollision(boolean inCollision) {
		this.inCollision = inCollision;
	}

	@Override
	public boolean intersects(RectCollider target) {
		return CollisionUtils.intersects(getRectangle(), target.getRectangle());
	}

	@Override
	public Rectangle overlap(RectCollider target) {
		if (intersects(target)) {
			return CollisionUtils.getIntersection(getRectangle(),
					target.getRectangle());
		}
		return null;
	}

	@Override
	public Rectangle getRectangle() {
		rect.setX(x);
		rect.setY(y);
		return rect;
	}

	@Override
	public void play() {
		switch (this.letter.charAt(0)) {
		case 65:
		case 97:
			Assets.SOUND_A.play();
			break;
		case 66:
		case 98:
			Assets.SOUND_B.play();
			break;
		case 67:
		case 99:
			Assets.SOUND_C.play();
			break;
		case 68:
		case 100:
			Assets.SOUND_D.play();
			break;
		case 69:
		case 101:
			Assets.SOUND_E.play();
			break;
		case 70:
		case 102:
			Assets.SOUND_F.play();
			break;
		case 71:
		case 103:
			Assets.SOUND_G.play();
			break;
		case 72:
		case 104:
			Assets.SOUND_H.play();
			break;
		case 73:
		case 105:
			Assets.SOUND_I.play();
			break;
		case 74:
		case 106:
			Assets.SOUND_J.play();
			break;
		case 75:
		case 107:
			Assets.SOUND_K.play();
			break;
		case 76:
		case 108:
			Assets.SOUND_L.play();
			break;
		case 77:
		case 109:
			Assets.SOUND_M.play();
			break;
		case 78:
		case 110:
			Assets.SOUND_N.play();
			break;
		case 79:
		case 111:
			Assets.SOUND_O.play();
			break;
		case 80:
		case 112:
			Assets.SOUND_P.play();
			break;
		case 81:
		case 113:
			Assets.SOUND_A.play();
			break;
		case 82:
		case 114:
			Assets.SOUND_R.play();
			break;
		case 83:
		case 115:
			Assets.SOUND_S.play();
			break;
		case 84:
		case 116:
			Assets.SOUND_T.play();
			break;
		case 85:
		case 117:
			Assets.SOUND_U.play();
			break;
		case 86:
		case 118:
			Assets.SOUND_V.play();
			break;
		case 87:
		case 119:
			Assets.SOUND_W.play();
			break;
		case 88:
		case 120:
			Assets.SOUND_X.play();
			break;
		case 89:
		case 121:
			Assets.SOUND_Y.play();
			break;
		case 90:
		case 122:
			Assets.SOUND_Z.play();
			break;
		default:
			throw new IllegalArgumentException("Invalid argument: " + letter);
		}
	}

	@Override
	public String toString() {
		return super.toString() + " \n locked: " + locked + "\n touchable: "
				+ touchable;
	}
	
	public String printActions(){
		actions.iter();
		Action action;

		String str = "Letter :" + letter + "\t";
		while ((action = actions.next()) != null) {
			str += action.toString() + "\n";
		}
		return str;
	}

	@Override
	protected void act (float delta) {
		actions.iter();
		Action action;

		while ((action = actions.next()) != null) {
			action.act(delta);
			if (action.isDone()) {
				action.finish();
				actions.remove();
			}
		}
	}
}
