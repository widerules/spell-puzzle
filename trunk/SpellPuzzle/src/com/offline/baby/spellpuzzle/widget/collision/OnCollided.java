package com.offline.baby.spellpuzzle.widget.collision;

public interface OnCollided {
	<T extends RectCollider> void collided(T go, T target);
}
