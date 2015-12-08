package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.input.Mouse;
import de.earley.pixelengine.window.render.GraphicsHelper;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author timmy
 */
public class UIMenu extends Layer {

	protected ArrayList<UIButton> buttons;
	protected ArrayList<UIElement> elements;
	private int width, height;

	public UIMenu(int width, int height) {
		this(new ArrayList<>(), new ArrayList<>(), width, height);
	}

	public UIMenu(ArrayList<UIButton> buttons, ArrayList<UIElement> elements, int width, int height) {
		this.buttons = buttons;
		this.elements = elements;
		this.width = width;
		this.height = height;
	}

	@Override
	public void init() {
		viewport = new GraphicsHelper(width, height, 0, 0, (v) -> render(((GraphicsHelper) v).getGraphics()));
	}

	protected void render(Graphics g) {
		for (UIButton button : buttons) {
			button.render(g);
		}
		for (UIElement element : elements) {
			element.render(g);
		}
	}

	@Override
	public boolean update(long delta, Window window) {
		Vector2f mousePos = window.transformMouse(viewport);
		Mouse mouse = window.getInput().mouse;
		for (UIButton button : buttons) {
			button.update(mousePos, mouse);
		}
		return false;
	}

	public void add(UIButton button) {
		button.setParent(this);
		buttons.add(button);
	}

	public void add(UIElement e) {
		elements.add(e);
	}
}
