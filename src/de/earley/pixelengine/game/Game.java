package de.earley.pixelengine.game;

import de.earley.pixelengine.game.util.GameSettings;
import de.earley.pixelengine.util.StringUtil;
import de.earley.pixelengine.window.Window;

/**
 * Base class for every game
 */
public abstract class Game implements Runnable {

	/**
	 * The window this game is played in
	 */
	protected Window window;

	/**
	 * The thread in which the game runs
	 */
	private Thread gameThread;

	/**
	 * The settings for the game instance
	 */
	protected GameSettings gameSettings;

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

	public void start() {
		running = true;
		init();
		window.start();
		gameThread.start();
	}

	@Override
	public void run() {
		int updates = 0;
		LoopTimer loopTimer = new LoopTimer();
		loopTimer.start();
		while (running) {
			updates = 0;
			loopTimer.loop();
			while (loopTimer.getUpdateCounter() >= 0
				&& updates < gameSettings.maxUpdatesBeforeRender)
			{
				updates++;
				updateAll(loopTimer.getTimeSinceLastUpdate(), window);
				loopTimer.didUpdate(gameSettings.getDeltaUpdate());
				loopTimer.loop();
			}

			boolean rendered = false;
			if (!gameSettings.limitFPS || updates != 0) {
				window.repaint();
				 rendered = true;
			}

			// DEBUG
			 showUPSAndFPS(loopTimer, updates, rendered);
		}
		exit();
	}

	// DEBUG

	private int totalUpdates, totalFrames;

	private void showUPSAndFPS(LoopTimer loopTimer, int updates, boolean rendered) {
		totalUpdates += updates;
		totalFrames += (rendered) ? 1 : 0;
		if (loopTimer.getTotalTime() >= 1000000000L) {
			double mult = (1000000000 / (double) loopTimer.getTotalTime());
			String ups = StringUtil.toDecimal(totalUpdates * mult, 2, true);
			String fps = StringUtil.toDecimal(totalFrames * mult, 2, true);
			window.changeTitle("UPS: " + ups + "; FPS: " + fps);
			loopTimer.resetTotalTime();
			totalUpdates = 0;
			totalFrames = 0;
		}
	}


	private void updateAll(long delta, Window window) {
	    update(delta, window);
	    // reset input
	    window.getInput().poll();
	}

	protected abstract void update(long delta, Window window);

	protected void init() {
	}

	protected void exit() {
	}

}
