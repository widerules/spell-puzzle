package com.offline.baby.spellpuzzle.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Wheel extends Group {

	public Wheel(String name, TextureRegion bg, Overflow overflow, float spacing) {
		this(name, bg, overflow, bg.getRegionWidth(), bg.getRegionHeight(),
				spacing);
	}

	public Wheel(String name, Overflow overflow, int w, int h, float spacing) {
		this(name, null, overflow, w, h, spacing);
	}

	public Wheel(String name, TextureRegion bg, Overflow overflow, int w,
			int h, float spacing) {
		super(name);
		this.bg = bg;
		this.overflow = overflow;
		this.bg = bg;
		this.spacing = spacing;
		children = new ArrayList<ButtonEx>();
		drawableWidth = w;
		drawableHeight = h;
		regionWidth = w;
		regionHeight = h;
		switch (overflow) {
		case X:
			this.height = h;
			this.originY = height / 2f;
			this.centerY = Gdx.graphics.getHeight() / 2;
			this.drawableX = Gdx.graphics.getWidth() / 2;
			break;
		case Y:
			this.width = w;
			this.originX = width / 2f;
			this.centerX = Gdx.graphics.getWidth() / 2;
			this.drawableY = Gdx.graphics.getHeight() / 2;
			break;
		}
	}

	public enum Overflow {
		X, Y;
	}

	private TextureRegion bg;
	private List<ButtonEx> children;
	private int centerX = 0;
	private int centerY = 0;

	public Overflow overflow;
	public float drawableWidth;
	public float regionWidth;
	public float drawableHeight;
	public float regionHeight;
	public float drawableX;
	public float drawableY;
	public float spacing = 0;
	public int currentIndex = 0;
	
	private boolean isDrag = false;

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		layout();
		if (bg != null) {
			switch (overflow) {
			case X:
				batch.draw(bg, 0, drawableY);
				break;
			case Y:
				batch.draw(bg, drawableX, 0);
				break;
			}

		}

		for (ButtonEx child : children) {
			child.draw(batch, parentAlpha);
		}
	}

	protected void layout() {
		switch (overflow) {
		case X:
			float offsetX = drawableX ;// (int) (centerX -
										// children.get(currentIndex).originX);
			int midY = (int) this.y + (int) originY;

			for (int i = currentIndex - 1; i >= 0; i--) {
				Actor act = children.get(i);
				offsetX -= act.width + spacing;
				act.x = offsetX;
				act.y = midY - act.originY;
			}

			offsetX = drawableX - children.get(currentIndex).originX;
			for (int i = currentIndex; i < children.size(); i++) {
				Actor act = children.get(i);
				act.x = offsetX;
				act.y = midY - act.originY;
				offsetX += act.width + spacing;
			}
			break;
		case Y:
			int offsetY = (int) centerY
					- (int) children.get(currentIndex).originY;
			int midX = (int) this.x + (int) originX;

			for (int i = currentIndex - 1; i >= 0; i--) {
				Actor act = children.get(i);
				offsetY -= act.height + spacing;
				act.x = midX - act.originX;
				act.y = offsetY;
			}

			offsetY = (int) centerY - (int) children.get(currentIndex).originY;
			for (int i = currentIndex; i < children.size(); i++) {
				Actor act = children.get(i);
				act.x = midX - act.originX;
				act.y = offsetY;
				offsetY += act.height + spacing;
			}

			break;
		}
	}

	public void addActor(ButtonEx actor) {
		children.add(actor);
	}
	
	@Override
	protected boolean touchDown(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return super.touchDown(x, y, pointer);
	}
	
	@Override
	protected boolean touchDragged(float x, float y, int pointer) {
		isDrag = true;
		return super.touchDragged(x, y, pointer);
	}
	
	@Override
	protected boolean touchUp(float x, float y, int pointer) {
		// TODO Auto-generated method stub
		return super.touchUp(x, y, pointer);
	}
}
