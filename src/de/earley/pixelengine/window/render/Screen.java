package de.earley.pixelengine.window.render;

import de.earley.pixelengine.sprite.Sprite;

/**
 * In charge of rendering to the window
 */
public class Screen {

	private static final int CLEAR_COLOR = 0xff000000; // BLACK

	private int colour;

	private int width, height;
	private int xOffset, yOffset;
	private int[] pixels;

	public Screen(int width, int height) {
		this(width, height, new int[width * height]);
	}

	public Screen(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = CLEAR_COLOR;
		}
	}

	// Adjusting
	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	// Basic rendering

	public void fillRect(int x, int y, int xSize, int ySize) {
		render(x, y, xSize, ySize, (xi, yi) -> {
			return colour;
		});
	}

	public void renderSprite(int x, int y, Sprite s) {
		render(x, y, s.getWidth(), s.getHeight(), (xi, yi) -> {
			return s.getPixel(xi, yi);
		});
	}

	public void render(int x, int y, int xSize, int ySize, ColourFunction cf) {
		x += xOffset;
		y += yOffset;
		for (int xi = 0; xi < xSize; xi++) {
			int xa = xi + x;
			if (xa < 0 || xa >= width)
				continue;
			for (int yi = 0; yi < ySize; yi++) {
				int ya = yi + y;
				if (ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = cf.get(xi, yi);
			}
		}
	}

	// Colours

	public void setColour(java.awt.Color colour) {
		this.colour = colour.getRGB();
	}

	public void setColour(int colour) {
		this.colour = colour;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

}
