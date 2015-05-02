package de.earley.pixelengine.window;

import de.earley.pixelengine.window.input.Input;

/**
 * Superclass for all window types (Swing, Applet, etc)
 */
public abstract class Window {
	
	/**
	 * The dimensions of the window
	 */
	protected int width, height;
	
	/**
	 * Can draw on this window
	 */
	private Screen screen;
	
	/**
	 * Receives input from this window
	 */
	protected Input in;

	public Screen getScreen() {
		return screen;
	}
	
	public Input getInput() {
		return in;
	}
	
	public abstract void start();
	
	
}
