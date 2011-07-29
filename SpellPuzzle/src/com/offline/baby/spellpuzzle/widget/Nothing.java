package com.offline.baby.spellpuzzle.widget;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Nothing extends Action {

	private static Nothing nothing = new Nothing();

	private Nothing() {
	}

	public static Nothing $() {
		return nothing;
	}

	@Override
	public void setTarget(Actor actor) {

	}

	@Override
	public void act(float delta) {

	}

	@Override
	public boolean isDone() {
		return true;
	}

	@Override
	public Action copy() {
		return null;
	}

}
