package com.offline.baby.spellpuzzle.widget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Button;

public class ButtonEx extends Button {

	public OnTouchDown onTouchDown;
	public OnTouchUp onTouchUp;

	public ButtonEx(String name, Texture texture) {
		super(name, texture);
	}

	public ButtonEx(String name, TextureRegion region) {
		super(name, region);
	}

	public ButtonEx(String name, TextureRegion unpressedRegion,
			TextureRegion pressedRegion) {
		super(name, unpressedRegion, pressedRegion);
	}

	@Override
	protected boolean touchDown(float x, float y, int pointer) {
		boolean result = super.touchDown(x, y, pointer);
		if (result && onTouchDown != null) {
			onTouchDown.pressed(this);
		}
		return result;
	}

	@Override
	protected boolean touchUp(float x, float y, int pointer) {
		boolean result = super.touchUp(x, y, pointer);
		
		if (result && onTouchUp != null) {
			onTouchUp.released(this);
		}
		return result;
	}

}
