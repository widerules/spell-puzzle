package com.offline.baby.spellpuzzle.widget;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Button;

public class SwitchButton extends Button {

	private boolean checked = false;

	public SwitchButton(String name, TextureRegion region) {
		this(name, region, region, false);
	}

	public SwitchButton(String name, Texture texture) {
		this(name, new TextureRegion(texture));
	}

	public SwitchButton(String name, TextureRegion uncheck,
			TextureRegion check, boolean checked) {
		super(name, uncheck, check);
		this.checked = checked;
	}

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		TextureRegion region = checked ? pressedRegion : unpressedRegion;
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		if (region.getTexture() != null) {
			if (scaleX == 0 && scaleY == 0 && rotation == 0)
				batch.draw(region, x, y, width, height);
			else
				batch.draw(region, x, y, originX, originY, width, height,
						scaleX, scaleY, rotation);
		}
	}

	@Override
	protected boolean touchUp(float x, float y, int pointer) {
		if (!pressed)
			return false;

		if (pointer == this.pointer) {
			parent.focus(null, pointer);
		}
		
		pressed = false;
		
		if (hit(x, y) == null){
			return false;
		}
		checked = !checked;

		if (clickListener != null)
			clickListener.clicked(this);
		return true;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
