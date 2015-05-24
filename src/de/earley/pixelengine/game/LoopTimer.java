package de.earley.pixelengine.game;

public class LoopTimer {
	/**
	 * The total time from the start of the game till now
	 */
	private long totalTime;
	/**
	 * The current loop time
	 */
	private long nowTime;
	/**
	 * The last loop time;
	 */
	private long lastTime;
	/**
	 * Differenz between nowTime and lastTime
	 */
	private int delta;
	/**
	 * Counter for next update
	 */
	private int updateCountdown;
	/**
	 * Time from last update
	 */
	private int timeSinceLastUpdate;
	
	public void loop() {
		lastTime = nowTime;
		nowTime = System.nanoTime();
		delta = (int) (nowTime - lastTime);
		totalTime += delta;
		updateCountdown += delta;
		timeSinceLastUpdate += delta;
	}

	public int getDelta() {
		return delta;
	}
	
	public void start() {
		nowTime = System.nanoTime();
	}
	
	public long getTotalTime() {
		return totalTime;
	}

	public void resetTotalTime() {
		totalTime = 0;
	}
	
	public int getUpdateCountdown() {
		return updateCountdown;
	}

	public void didUpdate(int updateTime) {
		updateCountdown -= updateTime;
		timeSinceLastUpdate = 0;
	}

	public int getTimeSinceLastUpdate() {
		return timeSinceLastUpdate;
	}
}

