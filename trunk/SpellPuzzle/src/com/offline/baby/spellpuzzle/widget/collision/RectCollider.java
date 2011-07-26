package com.offline.baby.spellpuzzle.widget.collision;

import com.badlogic.gdx.math.Rectangle;

public interface RectCollider {

	boolean isCollision();

	void setInCollision(boolean inCollision);

	<T extends RectCollider> boolean intersects(T target);

	<T extends RectCollider> Rectangle overlap(T target);

	Rectangle getRectangle();
}
