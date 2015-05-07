package de.earley.pixelengine.window.input;




public class Input {

	public Keyboard keyboard;
	public Mouse mouse;
	public Focus focus;
	public ComponentSizeListener component;
	
	public Input() {
		keyboard = new Keyboard();
		mouse = new Mouse();
		focus = new Focus();
		component = new ComponentSizeListener();
	}
	
	/**
	 * Handles the click events and typed
	 */
	public void poll() {
		keyboard.poll();
		mouse.poll();
	}

}
