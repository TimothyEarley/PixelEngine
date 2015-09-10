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
     * Time at last update
     */
    private long lastUpdate;

    /**
     * Grows over time, reduced every update
     */
    private long updateCounter;
    
    public void start() {
	nowTime = now();
    }

    public void loop() {
	    lastTime = nowTime;
	    nowTime = now();
	    long delta = nowTime - lastTime;
	    totalTime += delta;
	    updateCounter += delta;
    }

    public long getTotalTime() {
	    return totalTime;
    }

    public void resetTotalTime() {
	    totalTime = 0;
    }

    public void didUpdate(long updateTime) {
	    lastUpdate = now();
	    updateCounter -= updateTime;
    }

    public long getTimeSinceLastUpdate() {
	    return now() - lastUpdate;
    }

    public long getUpdateCounter() {
	return updateCounter;
    }
    
    private long now() {
	return System.nanoTime();
    }

}

