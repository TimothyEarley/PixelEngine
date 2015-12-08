package de.earley.pixelengine.sprite;


import java.util.ArrayList;

public class Animation implements Drawable {

	private final static ArrayList<Animation> animations = new ArrayList<>();

	public static void updateAll(long delta) {
		for (Animation anim : animations) {
			anim.update(delta);
		}
	}

	private final Sprite[] frameSprites;

	// times in ns
	private int frame, timeSinceLastFrame;
	private final int framesCount, deltaPerFrame;

	public Animation(Sprite[] frames) {
		this(frames, 200000000);
	}

	public Animation(Sprite[] frames, int deltaPerFrame) {
		this.frameSprites = frames;
		this.framesCount = frames.length;
		this.deltaPerFrame = deltaPerFrame;

		animations.add(this);
	}

	public Sprite getFrame() {
		return frameSprites[frame];
	}

	@Override
	public int getWidth() {
		return frameSprites[frame].getWidth();
	}

	@Override
	public int getHeight() {
		return frameSprites[frame].getHeight();
	}

	@Override
	public int getPixel(int x, int y) {
		return frameSprites[frame].getPixel(x, y);
	}

	public void update(long delta) {
		timeSinceLastFrame += delta;
		if (this.timeSinceLastFrame >= deltaPerFrame) {
			nextFrame();
		}
	}

	public void nextFrame() {
		timeSinceLastFrame = 0;
		frame = (frame + 1) % framesCount;
	}


}