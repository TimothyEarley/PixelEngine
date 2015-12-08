package de.earley.pixelengine.window.render;

import java.awt.*;


public abstract class Viewport {

	protected int renderWidth, renderHeight, width, height;
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
	 * xOffset and yOffset are global; local offsets need to be calculated
	 */
	protected abstract void renderToScreen(Graphics g, float stretch, int xOffset, int yOffset);

	public int getRenderWidth() {
		return renderWidth;
	}

	public int getRenderHeight() {
		return renderHeight;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}


}
