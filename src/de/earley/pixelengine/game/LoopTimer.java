package de.earley.pixelengine.game;

public class LoopTimer {

	private long totalTime;
	private long nowTime;
	private long lastTime;
	private int delta, deltaLastUpdate;
	
	public void loop() {
		lastTime = nowTime;
		nowTime = System.nanoTime();
		delta = (int) (nowTime - lastTime);
		totalTime += delta;
		deltaLastUpdate += delta;
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
	
	public int getDeltaLastUpdate() {
		return deltaLastUpdate;
	}

	public void didUpdate(int updateTime) {
		deltaLastUpdate -= updateTime;
	}
}

