package de.earley.pixelengine.game;

import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.game.util.GameSettings;
import de.earley.pixelengine.util.StringUtil;
import de.earley.pixelengine.window.Window;

/**
 * Base class for every game
 */
public class Game implements Runnable {

	/**
	 * The window this game is played in
	 */
	protected Window window;
	/**
	 * The settings for the game instance
	 */
	protected GameSettings gameSettings;
	/**
	 * The root layer
	 */
	protected Layer root;
	/**
	 * The thread in which the game runs
	 */
	private Thread gameThread;
	/**
	 * Indicates, wether the main loop is running
	 */
	private boolean running;
	private int totalUpdates, totalFrames;

	public Game(Window window, Layer root) {
		this(window, new GameSettings(), root);
	}

	public Game(Window window, GameSettings gameSettings, Layer root) {
		this.window = window;
		this.gameSettings = gameSettings;
		this.root = root;
		window.setRoot(root);

		gameThread = new Thread(this, "GameThread");

		Runtime.getRuntime().addShutdownHook(new Thread(this::exit, "Exit Thread"));
	}

	protected void exit() {
	}

	@Override
	public void run() {
		int updates;
		LoopTimer loopTimer = new LoopTimer();
		loopTimer.start();
		while (running) {
			updates = 0;
			loopTimer.loop();
			while (loopTimer.getUpdateCounter() >= 0
					&& updates < gameSettings.maxUpdatesBeforeRender) {
				updates++;
				boolean shouldQuit = root.update(loopTimer.getTimeSinceLastUpdate(), window);
				if (shouldQuit) exitAfterUpdate();
				window.getInput().poll();
				loopTimer.didUpdate(gameSettings.getDeltaUpdate());
				loopTimer.loop();
			}

			boolean rendered = false;
			if (!gameSettings.limitFPS || updates != 0) {
				render();
				rendered = true;
			}

			// DEBUG
			showUPSAndFPS(loopTimer, updates, rendered);
		}
		System.exit(0);
	}

	private void render() {
		if (window.getInput().focus.getFocused() || gameSettings.alwaysRender) {
			window.repaint();
		}
	}

	// DEBUG

	public void start() {
		running = true;
		window.start();
		root.open();
		init();
		gameThread.start();
	}

	protected void init() {
	}

	private void showUPSAndFPS(LoopTimer loopTimer, int updates, boolean rendered) {
		totalUpdates += updates;
		totalFrames += (rendered) ? 1 : 0;
		if (loopTimer.getTotalTime() >= 1000000000L) {
			double multiplier = (1000000000 / (double) loopTimer.getTotalTime());
			String ups = StringUtil.toDecimal(totalUpdates * multiplier, 2, true);
			String fps = StringUtil.toDecimal(totalFrames * multiplier, 2, true);
			window.changeTitle("UPS: " + ups + "; FPS: " + fps);
			loopTimer.resetTotalTime();
			totalUpdates = 0;
			totalFrames = 0;
		}
	}

	public Window getWindow() {
		return window;
	}

	public void exitAfterUpdate() {
		running = false;
	}


}
