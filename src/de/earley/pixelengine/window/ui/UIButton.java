package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Mouse;

import java.awt.*;
import java.util.Objects;

/**
 * @author timmy
 */
public abstract class UIButton extends UIElement {

	protected Rectangle box;
	private boolean in, down;

	public void update(Vector2f mousePos, Mouse mouse) {

		if (Objects.isNull(box)) {
			return;
		}

		if (box.contains(mousePos.toPoint())) {
			if (!in) {
				entered();
				in = true;
			}
		} else {
			if (in) {
				exited();
				in = false;
			}
		}

		if (in) {
			if (mouse.isButtonDown(1)) {
				if (!down) {
					pressed();
					down = true;
				}
			} else {
				if (down) {
					released();
					down = false;
				}
			}
		} else {
			down = false;
		}
	}

	public void entered() {
	}

	public void exited() {
	}

	public void pressed() {
	}

	public void released() {
		doAction();
	}

	public abstract void doAction();
}
