package com.offline.baby.spellpuzzle.widget;

import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class ListedGroup extends Group {

	public ListedGroup(String name) {
		super(name);
	}

	public void makeLast(final Actor actor) {
		Collections.sort(children, new Comparator<Actor>() {

			@Override
			public int compare(Actor o1, Actor o2) {
				if (o1 == actor)
					return 1;
				if (o2 == actor)
					return -1;
				return 0;
			}

		});

		// int index = children.indexOf(actor);
		// if (index != -1 && index != children.size() - 1) {
		// children.get(index);
		//
		// for (int i = 0; i < children.size() - 1; i++) {
		// if (children.get(i) == actor) {
		// Collections.swap(children, i, i + 1);
		// }
		// }
		// }
	}
}
