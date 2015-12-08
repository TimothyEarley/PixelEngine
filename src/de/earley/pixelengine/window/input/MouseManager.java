package de.earley.pixelengine.window.input;

import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Viewport;

/**
 * Created by timmy on 10/14/15.
 */
public class MouseManager extends Mouse {

	private Mouse mouse;
	private Vector2f mousePos;

	public MouseManager(Mouse mouse, Window window, Viewport viewport) {
		super();
		this.mouse = mouse;
		this.mousePos = window.transformMouse(viewport);
	}

	@Override
	public Vector2f getMouse() {
		return mousePos.copy();
	}

	@Override
	public boolean didButtonClick(int buttonNumber) {
		return mouse.didButtonClick(buttonNumber);
	}

	@Override
	public boolean isButtonDown(int buttonNumber) {
		return mouse.isButtonDown(buttonNumber);
	}

	public void addOffset(Vector2i offset) {
		mousePos.add(offset);
	}
}
