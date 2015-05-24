package de.earley.pixelengine.sprite;


public class Animation implements Drawable {

	private Sprite[] frameSprites;
	private int frame, framesCount, deltaPerFrame, timeSinceLastFrame;
	
	public Animation(Sprite[] frames, int deltaPerFrame) {
		this.frameSprites = frames;
		this.framesCount = frames.length;
		this.deltaPerFrame = deltaPerFrame;
	}
	
	public Animation(Sprite[] frames) {
		this(frames, 24);
		//TODO adjust 24
	}

	@Override
	public int getWidth() {	
		return frameSprites[frame].getWidth();
	}

	@Override
	public int getHeight() {
		return frameSprites[frame].getHeight();
	}
	
	public void update(int delta) {
		timeSinceLastFrame += delta;
		if (this.timeSinceLastFrame >= deltaPerFrame) {
			nextFrame();
		}
	}

	public void nextFrame() {
		timeSinceLastFrame = 0;
		frame = (frame + 1) % framesCount;
	}

	@Override
	public int getPixel(int x, int y) {
		return frameSprites[frame].getPixel(x, y);
	}
	
	
	
}