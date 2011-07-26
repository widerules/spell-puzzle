package com.offline.baby.spellpuzzle.widget.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.AnimationAction;
import com.badlogic.gdx.scenes.scene2d.actions.ActionResetingPool;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.offline.baby.spellpuzzle.widget.AccordionLayer;

public class FoldOut extends AnimationAction {

	static final ActionResetingPool<FoldOut> pool = new ActionResetingPool<FoldOut>(
			4, 100) {
		@Override
		protected FoldOut newObject() {
			return new FoldOut();
		}
	};

	public static FoldOut $(float duration) {
		FoldOut action = pool.obtain();
		action.duration = duration;
		action.invDuration = 1 / duration;
		return action;
	}

	@Override
	public void setTarget(Actor actor) {
		if (!(actor instanceof AccordionLayer)) {
			throw new GdxRuntimeException(
					"Can only add AccordionLayer subclasses");
		}
		this.target = actor;
		this.taken = 0;
		this.done = false;
	}

	@Override
	public void act(float delta) {
		float alpha = createInterpolatedAlpha(delta);

		AccordionLayer actor = (AccordionLayer) target;

		switch (actor.direction) {
		case TOP:
			break;
		case BOTTOM:
			if (done) {
				actor.drawableHeight = 0;
			} else {
				actor.drawableHeight = actor.height - actor.height * alpha;
			}
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}

	}

	@Override
	public void finish() {
		pool.free(this);
		if (listener != null) {
			listener.completed(this);
		}
	}

	@Override
	public Action copy() {
		FoldOut action = $(duration);
		if (interpolator != null)
			action.setInterpolator(interpolator.copy());
		return action;
	}
}
