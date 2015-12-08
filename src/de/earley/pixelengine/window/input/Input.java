package de.earley.pixelengine.window.input;


public class Input {

	public Keyboard keyboard;
	public Mouse mouse;
	public ComponentSizeListener component;
	public FocusListenerImpl focus;

	public Input() {
		keyboard = new Keyboard();
		mouse = new Mouse();
		component = new ComponentSizeListener();
		focus = new FocusListenerImpl();
	}

	/**
	 * Handles the click events and typed
	 */
	public void poll() {
		keyboard.poll();
		mouse.poll();
	}
}
