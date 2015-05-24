package de.earley.pixelengine.window.render;

import de.earley.pixelengine.sprite.Drawable;

/**
 * In charge of rendering to the window
 */
public class Screen {

	public static int CLEAR_COLOR = 0xff000000; // BLACK

	private int colour;

	private int width, height;
	private int xOffset, yOffset;
	private int[] pixels;

	public int MODE = MODE_ALPHA_BLENDING;
	public static final byte MODE_NO_ALPHA_BLENDING = 0;
	public static final byte MODE_ALPHA_BLENDING = 1;

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

	public void renderDrawable(int x, int y, Drawable drawable) {
		render(x, y, drawable.getWidth(), drawable.getHeight(), (xi, yi) -> {
			return drawable.getPixel(xi, yi);
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
				pixels[xa + ya * width] = add(pixels[xa + ya * width], cf.get(xi, yi));
			}
		}
	}

	
	public int add(int bottom, int top) {
		switch (MODE) {
		case MODE_ALPHA_BLENDING:
			int aA = (top >> 24) & 0xff;
			int rA = (top >> 16) & 0xff;
			int gA = (top >> 8) & 0xff;
			int bA = (top >> 0) & 0xff;
			
			int aB = (bottom >> 24) & 0xff;
			int rB = (bottom >> 16) & 0xff;
			int gB = (bottom >> 8) & 0xff;
			int bB = (bottom >> 0) & 0xff;
			
			int rOut = (rA * aA >> 8) + (rB * aB * (255 - aA) >> 16);
			int gOut = (gA * aA >> 8) + (gB * aB * (255 - aA) >> 16);
			int bOut = (bA * aA >> 8) + (bB * aB * (255 - aA) >> 16);
			int aOut = aA + (aB * (255 - aA) >> 8);
			return aOut << 24 | rOut << 16 | gOut << 8 | bOut;

		case MODE_NO_ALPHA_BLENDING:
		default:
			return top;
		}
	}

	/**
	 * Adds alpha of 0xFF and sets as colour
	 * @param colour
	 * the base colour
	 */
	public void setColour(java.awt.Color colour) {
		this.colour = 0xff000000 | colour.getRGB();
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
