package com.offline.baby.spellpuzzle.widget.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.ActionResetingPool;

public class ScollIn extends Action {
	
	static final ActionResetingPool<ScollIn> pool = new ActionResetingPool<ScollIn>(
			4, 100) {
		@Override
		protected ScollIn newObject() {
			return new ScollIn();
		}
	};
	
	public static ScollIn $() {
		ScollIn action = pool.obtain();
		return action;
	}

	@Override
	public void setTarget(Actor actor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Action copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
