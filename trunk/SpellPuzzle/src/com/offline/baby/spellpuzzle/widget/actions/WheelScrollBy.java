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

	protected float offset;
	protected float start;
	protected float destiny;

	public static WheelScrollBy $(float offset, float duration) {
		WheelScrollBy action = pool.obtain();

		action.offset = offset;
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

		Wheel w = (Wheel) actor;

		this.target = actor;

		this.taken = 0;
		this.done = false;

		switch (w.overflow) {
		case X:
			this.start = target.x;
			this.destiny = offset + target.x;
			break;
		case Y:
			this.start = target.y;
			this.destiny = offset + target.y;
			break;
		}

	}

	@Override
	public void act(float delta) {

		float alpha = createInterpolatedAlpha(delta);
		Wheel w = (Wheel) target;

		switch (w.overflow) {
		case X:
			if (done) {
				target.x = this.destiny;
			} else {
				target.x = start + offset * alpha;
			}
			break;
		case Y:
			if (done) {
				target.y = this.destiny;
			} else {
				target.y = start + offset * alpha;
			}
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
		WheelScrollBy action = $(offset, duration);
		if (interpolator != null)
			action.setInterpolator(interpolator.copy());
		return action;
	}

}
