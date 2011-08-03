package com.offline.baby.spellpuzzle.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class AccordionLayer extends Group {

	public enum Direction {
		TOP, BOTTOM, LEFT, RIGHT;
	}

	private TextureRegion bg;
	public Direction direction;
	public float drawableWidth;
	public float regionWidth;
	public float drawableHeight;
	public float regionHeight;
	public int drawableX;
	public int drawableY;
	private List<Actor> children;
	public float spacing = 0;

	public int textureX;
	public int textureY;

	public AccordionLayer(String name, Texture texture, Direction direction) {
		this(name, new TextureRegion(texture), direction, 0);
	}

	public AccordionLayer(String name, TextureRegion region,
			Direction direction, float spacing) {
		super(name);
		this.bg = region;
		this.direction = direction;
		this.spacing = spacing;
		children = new ArrayList<Actor>();
		drawableWidth = region.getRegionWidth();
		drawableHeight = region.getRegionHeight();
		regionHeight = region.getRegionHeight();
		regionWidth = region.getRegionWidth();
		this.height = region.getRegionHeight();
		this.width = region.getRegionWidth();
		this.originX = width / 2f;
		this.originY = height / 2f;

		textureX = region.getRegionX();
		textureY = region.getRegionY();
	}

	@Override
	protected void draw(SpriteBatch batch, float parentAlpha) {
		switch (direction) {
		case TOP:
			break;
		case BOTTOM:
			drawableX = (int) x;
			drawableY = (int) (y - drawableHeight);
			regionHeight = drawableHeight;
			bg.setRegion(textureX, (int) (textureY + height - drawableHeight),
					(int) drawableWidth, (int) regionHeight);
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}

		layout();
		batch.draw(bg, drawableX, drawableY);
	}

	public List<Actor> getChildren() {
		return children;
	}

	public void addActor(Actor actor) {
		if (actor instanceof Group) {
			throw new GdxRuntimeException("Can not add Group (Actors)");
		}
		children.add(actor);
	}

	protected void layout() {
		switch (direction) {
		case TOP:
			break;
		case BOTTOM:
			int offsetY = (int) spacing;

			for (Actor actor : children) {
				actor.x = this.originX - actor.width / 2 + this.x;
				if (offsetY + actor.height > this.regionHeight) {
					actor.y = Gdx.graphics.getHeight();
				} else {
					actor.y = offsetY + drawableY;
				}
				offsetY += actor.height + this.spacing;
			}

			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
	}
}
