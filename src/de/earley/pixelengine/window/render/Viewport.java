package de.earley.pixelengine.window.render;

import java.awt.Graphics;

import de.earley.pixelengine.util.Action;

public abstract class Viewport {

	protected int renderWidth, renderHeight;
	protected int xPosition, yPosition;
	
	protected Action renderAction;

	
	public Viewport(int width, int height, int x, int y, Action renderAction) {
		this.renderWidth = width;
		this.renderHeight = height;
		this.xPosition = x;
		this.yPosition = y;
		this.renderAction = renderAction;
	}
	
	public void render(Graphics g, float stretch, int xOffset, int yOffset) {
		renderAction.fire();
		renderToScreen(g, stretch, xOffset, yOffset);
	}
	
	protected abstract void renderToScreen(Graphics g, float stretch, int xOffset, int yOffset);

	public int getRenderWidth() {
		return renderWidth;
	}

	public int getRenderHeight() {
		return renderHeight;
	}
}
