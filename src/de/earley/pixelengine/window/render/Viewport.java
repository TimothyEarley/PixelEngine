package de.earley.pixelengine.window.render;

import java.awt.Graphics;


public abstract class Viewport {

	protected int renderWidth, renderHeight;
	protected int xPosition, yPosition;
	
	protected RenderAction renderAction;

	
	public Viewport(int width, int height, int x, int y, RenderAction renderAction) {
		this.renderWidth = width;
		this.renderHeight = height;
		this.xPosition = x;
		this.yPosition = y;
		this.renderAction = renderAction;
	}
	
	public void render(Graphics g, float stretch, int xOffset, int yOffset) {
		renderAction.render(this);
		renderToScreen(g, stretch, xOffset, yOffset);
	}
	
	/*
	 * xOffset and yOffset are global; local offsets need to be calced
	 */
	protected abstract void renderToScreen(Graphics g, float stretch, int xOffset, int yOffset);

	public int getRenderWidth() {
		return renderWidth;
	}

	public int getRenderHeight() {
		return renderHeight;
	}
}
