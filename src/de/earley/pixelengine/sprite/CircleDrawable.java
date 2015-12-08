package de.earley.pixelengine.sprite;

/**
 * Created by timmy on 25/10/15.
 */
public class CircleDrawable implements Drawable {


	private final int radius, radiusSQR, diameter, colour;

	public CircleDrawable(int radius, int colour) {
		this.radius = radius;
		this.radiusSQR = radius * radius;
		this.diameter = radius * 2;
		this.colour = colour;
	}

	@Override
	public int getWidth() {
		return diameter;
	}

	@Override
	public int getHeight() {
		return diameter;
	}

	@Override
	public int getPixel(int x, int y) {
		int col = 0;
		x -= radius;
		y -= radius;
		if (x * x + y * y <= radiusSQR) col = colour;
		return col;
	}
}
