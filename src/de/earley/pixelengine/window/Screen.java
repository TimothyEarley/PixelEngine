package de.earley.pixelengine.window;

/**
 * In charge of rendering to the window
 */
public class Screen {

	private static final int CLEAR_COLOR = 0x0; // BLACK
	
	
	private int width, height;
	private int[] pixels;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = CLEAR_COLOR;
		}
	}
	
}
