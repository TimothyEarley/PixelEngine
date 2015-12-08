package de.earley.pixelengine.game.util;

/**
 * Stores all the needed values for the game
 */
public class GameSettings {

	/**
	 * If game is running to slowly, make sure we actually render from time to time
	 */
	public int maxUpdatesBeforeRender;
	/**
	 * If true, should render when not focused
	 */
	public boolean alwaysRender;
	/**
	 * If true, only render after new update
	 */
	public boolean limitFPS;
	/**
	 * Nanoseconds between updates
	 */
	private int deltaUpdate;

	public GameSettings() {
		// defaults
		setUPS(60);
		alwaysRender = false;
		maxUpdatesBeforeRender = 60;
		limitFPS = true;
	}

	public void setUPS(int ups) {
		deltaUpdate = 1000000000 / ups;
	}

	public int getDeltaUpdate() {
		return deltaUpdate;
	}

}
