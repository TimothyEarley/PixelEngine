package de.earley.pixelengine.window;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.window.input.Input;
import de.earley.pixelengine.window.render.Screen;

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
	protected Screen screen;

	/**
	 * Receives input from this window
	 */
	protected Input in;

	public Screen getScreen() {
		if (screen == null) {
			screen = createScreen();
		}
		return screen;
	}

	protected Screen createScreen() {
		return new Screen(width, height);
	}

	public Input getInput() {
		return in;
	}

	public abstract void start();

	/**
	 * Display the actual screen
	 * @param game The game to render, must call {@link Game#render(Screen screen)}
	 */
	public abstract void render(Game game);

}
