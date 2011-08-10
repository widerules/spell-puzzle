package com.offline.baby.spellpuzzle.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Wheel extends Group {

	public Wheel(String name, TextureRegion bg, Overflow overflow, float spacing) {
		this(name, bg, overflow, bg
				.getRegionWidth(), bg.getRegionHeight(), spacing);
	}

	public Wheel(String name, Overflow overflow, int w, int h,
			float spacing) {
		this(name, null, overflow, w, h, spacing);
	}

	public Wheel(String name, TextureRegion bg, Overflow overflow, int w, int h, float spacing) {
		super(name);
		this.bg = bg;
		this.overflow = overflow;
		this.bg = bg;
		this.spacing = spacing;
		children = new ArrayList<Actor>();
		drawableWidth = w;
		drawableHeight = h;
		regionWidth = w;
		regionHeight = h;
		this.width = w;
		this.height = h;
		this.originX = width / 2f;
		this.originY = height / 2f;
		this.centerX = Gdx.graphics.getWidth() / 2;
		this.centerY = Gdx.graphics.getHeight() / 2;
//		this.drawableX = x;
//		this.drawableY = y;
	}

	public enum Overflow {
		X, Y;
	}

	private TextureRegion bg;
	private List<Actor> children;
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
	}

	protected void layout() {
		switch (overflow) {
		case X:
			float offsetX = drawableX;//  (int) (centerX - children.get(currentIndex).originX);
			int midY = (int) this.y + (int) originY;

			for (int i = currentIndex - 1; i >= 0; i--) {
				Actor act = children.get(i);
				offsetX -= act.width + spacing;
				act.x = offsetX;
				act.y = midY - act.originY;
			}

			offsetX = drawableX;
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

	public void addActor(Actor actor) {
		if (actor instanceof Group) {
			throw new GdxRuntimeException("Can not add Group (Actors)");
		}

		children.add(actor);
	}
}
