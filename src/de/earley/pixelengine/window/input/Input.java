package de.earley.pixelengine.window.input;



public class Input {

	public Keyboard keyboard;
	public Mouse mouse;
	public Focus focus;
	
	/**
	 * Handles the click events and typed
	 */
	public void poll() {
		keyboard.poll();
		mouse.poll();
	}

}
