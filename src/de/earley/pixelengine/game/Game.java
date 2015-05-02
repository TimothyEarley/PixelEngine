package de.earley.pixelengine.game;

import de.earley.pixelengine.game.util.GameSettings;
import de.earley.pixelengine.window.Screen;
import de.earley.pixelengine.window.Window;

/**
 * Base class for every game
 */
public abstract class Game implements Runnable {

	/**
	 * The window this game is played in
	 */
	private Window window;

	/**
	 * The thread in which the game runs
	 */
	private Thread gameThread;

	/**
	 * The settings for the game instance
	 */
	private GameSettings gameSettings;

	/**
	 * Indicates, wether the main loop is running
	 */
	private boolean running;

	public Game(Window window) {
		this(window, new GameSettings());
	}

	public Game(Window window, GameSettings gameSettings) {
		this.window = window;
		this.gameSettings = gameSettings;

		gameThread = new Thread(this, "GameThread");
	}

	public void setSettings(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public GameSettings getSettings() {
		return gameSettings;
	}

	public void start() {
		running = true;
		window.start();
		gameThread.start();
	}

	@Override
	public void run() {
		init();
		int updates = 0;
		LoopTimer loopTimer = new LoopTimer();
		while (running) {
			updates = 0;
			loopTimer.loop();
			while (loopTimer.getDeltaLastUpdate() >= 0 && updates < gameSettings.maxUpdatesBeforeRender) {
				updates++;
				updateAll(loopTimer.getDelta(), window);
				loopTimer.didUpdate(gameSettings.getDeltaUpdate());
			}

			// DEBUG
			// boolean rendered = false;
			if (updates != 0) {
				render(window.getScreen());
				// DEBUG
				// rendered = true;
			}

			// DEBUG
			// showUPSAndFPS(loopTimer, updates, rendered);
		}
		exit();
	}

	// DEBUG
	/*
	 * private int totalUpdates, totalFrames;
	 * private void showUPSAndFPS(LoopTimer loopTimer, int updates, boolean rendered) {
	 * totalUpdates += updates;
	 * totalFrames += (rendered) ? 1 : 0;
	 * if (loopTimer.getTotalTime() >= 1000000000L) {
	 * double mult = (1000000000 / (double) loopTimer.getTotalTime());
	 * System.out.println("UPS: " + totalUpdates * mult + "; FPS: " + totalFrames * mult);
	 * loopTimer.resetTotalTime();
	 * totalUpdates = 0;
	 * totalFrames = 0;
	 * }
	 * }
	 */

	private void updateAll(int delta, Window window) {
		window.getInput().poll();
		update(delta, window);
	}

	protected abstract void update(int delta, Window window);

	protected abstract void render(Screen screen);

	protected void init() {
	}

	protected void exit() {
	}

}
