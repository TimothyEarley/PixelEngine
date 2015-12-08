package de.earley.pixelengine.sprite;

import de.earley.pixelengine.util.CrashHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite implements Drawable {

	private int width, height;
	private int[] pixels;
	private String path;
	private boolean loaded;

	public Sprite(String path) {
		this.path = path;
		SpriteManager.add(this);
	}

	public Sprite(int[] pixels, int width, int height) {
		loaded = true;
		this.pixels = pixels;
		this.height = height;
		this.width = width;
	}

	public Sprite(Sprite spritesheet, int x, int y, int width, int height) {
		loaded = true;
		this.height = height;
		this.width = width;
		this.pixels = new int[width * height];
		for (int xOff = 0; xOff < width; xOff++) {
			for (int yOff = 0; yOff < height; yOff++) {
				pixels[xOff + yOff * width] = spritesheet.getPixel(xOff + x, yOff + y);
			}
		}
	}

	public void load() {
		if (loaded) return;
		loaded = true;
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException | IllegalArgumentException e) {
			CrashHandler.crash(e, "Failed to load sprite '" + path + "'!");
		}
	}

	public Sprite[] slice(int width, int height) {
		int xAmount = this.width / width;
		int yAmount = this.height / height;
		Sprite[] slices = new Sprite[xAmount * yAmount];
		for (int y = 0; y < yAmount; y++) {
			for (int x = 0; x < xAmount; x++) {
				slices[x + y * yAmount] = new Sprite(this, x * width, y * height, width, height);
			}
		}
		return slices;
	}

	@Override
	public int getWidth() {
		if (!loaded)
			load();
		return width;
	}

	@Override
	public int getHeight() {
		if (!loaded)
			load();
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		if (!loaded)
			load();
		return pixels[x + y * width];
	}

	public String getPath() {
		return path;
	}
}
