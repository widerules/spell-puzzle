package com.offline.baby.spellpuzzle.widget.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.AnimationAction;
import com.badlogic.gdx.scenes.scene2d.actions.ActionResetingPool;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.offline.baby.spellpuzzle.widget.Wheel;

public class WheelScrollBy extends AnimationAction {

	static final ActionResetingPool<WheelScrollBy> pool = new ActionResetingPool<WheelScrollBy>(
			4, 100) {
		@Override
		protected WheelScrollBy newObject() {
			return new WheelScrollBy();
		}
	};

	protected float initialX;
	protected float initialY;
	protected float x;
	protected float y;
	protected float startX;
	protected float startY;
	protected float deltaX;
	protected float deltaY;

	public static WheelScrollBy $(float x, float y, float duration) {
		WheelScrollBy action = pool.obtain();
		action.x = action.initialX = x;
		action.y = action.initialY = y;
		action.duration = duration;
		action.invDuration = 1 / duration;
		return action;
	}

	@Override
	public void setTarget(Actor actor) {
		if (!(actor instanceof Wheel)) {
			throw new GdxRuntimeException(
					"Can only add Wheel and Wheel subclasses");
		}
		this.target = actor;

		this.taken = 0;
		this.done = false;

		this.startX = target.x;
		this.startY = target.y;
		this.deltaX = x;
		this.deltaY = y;
		this.x = target.x + x;
		this.y = target.y + y;
	}

	@Override
	public void act(float delta) {

		float alpha = createInterpolatedAlpha(delta);
		if (done) {
			target.x = x;
			target.y = y;
		} else {
			target.x = startX + deltaX * alpha;
			target.y = startY + deltaY * alpha;
		}

		Wheel actor = (Wheel) target;

		switch (actor.overflow) {
		case X:
			if (done) {
				actor.drawableX = (int) (initialX + x);
			} else {
				actor.drawableHeight = actor.height * alpha;
			}
			break;
		case Y:
			break;
		}
	}

	@Override
	public void finish() {
		super.finish();
		pool.free(this);
	}

	@Override
	public Action copy() {
		WheelScrollBy action = $(initialX, initialY, duration);
		if (interpolator != null)
			action.setInterpolator(interpolator.copy());
		return action;
	}

}
