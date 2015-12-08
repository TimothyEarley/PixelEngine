package de.earley.pixelengine.sprite;

/**
 * @author timmy
 */
public class SolidColourSprite implements Drawable {

	private int width, height, colour;

	public SolidColourSprite(int colour, int width, int height) {
		this.colour = colour;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		return colour;
	}

}
