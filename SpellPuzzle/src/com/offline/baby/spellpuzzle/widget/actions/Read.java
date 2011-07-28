package com.offline.baby.spellpuzzle.widget.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ActionResetingPool;
import com.offline.baby.spellpuzzle.widget.IPlay;

public class Read extends Action {

	static final ActionResetingPool<Read> pool = new ActionResetingPool<Read>(
			4, 100) {
		@Override
		protected Read newObject() {
			return new Read();
		}
	};

	private float duration = 1f;
	boolean played = false;
	private float taken = 0f;
	private Actor target;

	public static Read $() {
		Read read = pool.obtain();
		return read;
	}

	public static Read $(float duration) {
		Read read = pool.obtain();
		read.duration = duration;
		return read;
	}

	@Override
	public void act(float delta) {
		if (!played && target instanceof IPlay) {
			((IPlay) target).play();
			played = true;
		}
		this.taken += delta;
	}

	@Override
	public boolean isDone() {
		if (this.taken > duration && played) {
			if (target instanceof IPlay) {
				((IPlay) target).setRead(true);
			}
			pool.free(this);
			return true;
		}

		return false;
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	public Action copy() {
		Read read = $(duration);
		return read;
	}

	@Override
	public void setTarget(Actor actor) {
		this.target = actor;
		Gdx.app.log("debug", "set target:" + target);
		played = false;
		taken = 0f;
	}

	public float getDuration() {
		return duration;
	}

	@Override
	public void reset() {
		super.reset();
		played = false;
		taken = 0f;
		target = null;
	}
}
