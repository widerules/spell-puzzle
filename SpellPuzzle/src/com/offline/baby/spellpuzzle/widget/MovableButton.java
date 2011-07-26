package com.offline.baby.spellpuzzle.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actors.Button;

public class MovableButton extends Button {

	public interface DragListener {
		void dragged(MovableButton button);
	}

	public DragListener dragListener;
	public boolean dragged = false;
	public boolean locked = false;

	protected float offsetX;
	protected float offsetY;

	public MovableButton(String name, Texture texture) {
		super(name, texture);
	}

	public MovableButton(String name, TextureRegion region) {
		this(name, region, region);
	}

	public MovableButton(String name, TextureRegion unpressedRegion,
			TextureRegion pressedRegion) {
		super(name, unpressedRegion, pressedRegion);
	}

	@Override
	protected boolean touchDown(float x, float y, int pointer) {
		if (pressed)
			return false;

		boolean result = x > 0 && y > 0 && x < width && y < height;
		pressed = result;

		if (pressed) {
			offsetX = x;
			offsetY = y;
			parent.focus(this, pointer);
			this.pointer = pointer;
		}
		return result;
	}

	@Override
	protected boolean touchDragged(float x, float y, int pointer) {
		if (!pressed)
			return false;

		if (this.pointer == pointer && !locked) {
			float realX = x - offsetX;
			float realY = y - offsetY;
			if (realX >= 0 - this.x
					&& realX <= Gdx.graphics.getWidth() - (this.width + this.x)
					&& realY >= 0 - this.y
					&& realY <= Gdx.graphics.getHeight()
							- (this.y + this.height)) {
				// action(MoveBy.$(realX, realY, 0));
				action(MoveTo.$(this.x + realX, this.y + realY, 0));
				if (dragListener != null) {
					dragListener.dragged(this);
				}
			}
		}

		return true;
	}
}
