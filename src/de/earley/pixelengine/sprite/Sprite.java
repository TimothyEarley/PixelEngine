package de.earley.pixelengine.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite implements Drawable {

	private int width, height;
	private int[] pixels;

	public Sprite(String path) {
		try {
			BufferedImage image = ImageIO.read(Sprite.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException | IllegalArgumentException e) {
			System.err.println("Failed to load sprite!");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public Sprite(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.height = height;
		this.width = width;
	}
	
	public Sprite(Sprite spritesheet, int x, int y, int width, int height) {
		this.height = height;
		this.width = width;
		this.pixels = new int[width * height];
		for (int xOff = 0; xOff < width; xOff++) {
			for (int yOff = 0; yOff < height; yOff++) {
				pixels[xOff + yOff * width] = spritesheet.pixels[xOff + x + (yOff + y) * spritesheet.width];
			}	
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
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getPixel(int x, int y) {
		return pixels[x + y * width];
	}
}
