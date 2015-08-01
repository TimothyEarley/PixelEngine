package de.earley.pixelengine.window.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.earley.pixelengine.util.GraphicsUtil;

public class GraphicsHelper extends Viewport {
	
	private int width, height;
	private BufferedImage canvas;
	private Graphics2D g2d;

	public GraphicsHelper(int width, int height, int x, int y, RenderAction renderAction) {
		this(width, height, width, height, x, y, renderAction);
	}
	
	public GraphicsHelper(int width, int height, int renderWidth, int renderHeight, int x, int y, RenderAction renderAction) {
		super(renderWidth, renderHeight, x, y, renderAction);
		this.width = width;
		this.height = height;
		canvas = GraphicsUtil.create(width, height);
		g2d = canvas.createGraphics();
	}

	
	
	
	@Override
	protected void renderToScreen(Graphics g, float stretch, int xOffset, int yOffset) {
		g.drawImage(canvas, (int) ((xPosition + xOffset) * stretch), (int) ((yPosition + yOffset) * stretch), (int) (renderWidth * stretch), (int) (renderHeight * stretch), null);
		//reset canvas
		g2d.clearRect(0, 0, width, height);
	}




	public Graphics2D getGraphics() {
		return g2d;
	}

}
