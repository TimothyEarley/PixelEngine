package de.earley.pixelengine.game.states;

import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.input.MouseManager;
import de.earley.pixelengine.window.render.BlankViewport;
import de.earley.pixelengine.window.render.Viewport;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by timmy on 10/14/15.
 */
public class Layer {

	protected Layer parent = null;
	protected ArrayList<Layer> children = new ArrayList<>();
	protected Viewport viewport;
	private boolean active = false;

	public Layer() {

	}

	public Layer(int width, int height) {
		viewport = new BlankViewport(width, height);
	}

	public final void addChildState(Layer layer) {
		layer.parent = this;
		layer.init();
		children.add(layer);
	}

	protected void init() {
	}

	/**
	 * @param delta  time since last update
	 * @param window the window in which this layer is
	 * @return if true then quit
	 */
	public boolean update(long delta, Window window) {
		boolean quit = false;
		for (Layer child : children) {
			if (child.isActive()) {
				if (child.update(delta, window)) {
					quit = true;
				}
			}
		}
		return quit;
	}

	public final void close() {
		active = false;
		onClose();
	}

	protected void onClose() {
	}

	public final void open() {
		active = true;
		onOpen();
	}

	public final void render(Graphics g, float stretch, int xOffset, int yOffset) {
		if (viewport != null) {
			viewport.render(g, stretch, xOffset, yOffset);
		}
		children.stream().filter(Layer::isActive).forEach(child -> child.render(g, stretch, xOffset, yOffset));
	}

	protected void onOpen() {
	}

	public void setParent(Layer parent) {
		this.parent = parent;
	}

	public int getWidth() {
		return viewport.getRenderWidth();
	}

	public int getHeight() {
		return viewport.getRenderHeight();
	}


	protected MouseManager getNewMouse(Window window) {
		return new MouseManager(window.getInput().mouse, window, viewport);
	}

	public Viewport getViewport() {
		return viewport;
	}

	protected boolean isActive() {
		return active;
	}

	public void openAll() {
		open();
		children.forEach(de.earley.pixelengine.game.states.Layer::openAll);
	}
}
