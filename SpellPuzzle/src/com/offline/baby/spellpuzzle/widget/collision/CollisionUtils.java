package com.offline.baby.spellpuzzle.widget.collision;

import com.badlogic.gdx.math.Rectangle;

public final class CollisionUtils {

	public static boolean intersects(Rectangle a, Rectangle b) {
		return (a.x + a.width > b.x) && (a.x < b.x + b.width)
				&& (a.y + a.height > b.y) && (a.y < b.y + b.height);
	}

	public static Rectangle getIntersection(Rectangle a, Rectangle b) {
		float a_x = a.getX();
		float a_r = a.getX() + a.getWidth();
		float a_y = a.getY();
		float a_t = a.getY() + a.getHeight();
		float b_x = b.getX();
		float b_r = b.getX() + b.getWidth();
		float b_y = b.getY();
		float b_t = b.getY() + b.getHeight();
		float i_x = Math.max(a_x, b_x);
		float i_r = Math.min(a_r, b_r);
		float i_y = Math.max(a_y, b_y);
		float i_t = Math.min(a_t, b_t);
		return i_x < i_r && i_y < i_t ? new Rectangle(i_x, i_y, i_r - i_x, i_t
				- i_y) : null;
	}
}
