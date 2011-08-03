package com.offline.baby.spellpuzzle.widget;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Wheel extends Group {

	public Wheel(String name) {
		super(name);
	}

	public Wheel(String name, TextureRegion bg) {
		super(name);
		this.bg = bg;
	}

	public enum Overflow {
		X, Y;
	}

	private TextureRegion bg;
	public Overflow overflow;
	public float drawableWidth;
	public float regionWidth;
	public float drawableHeight;
	public float regionHeight;
	public int drawableX;
	public int drawableY;
	private List<Actor> children;
	public float spacing = 0;

}
