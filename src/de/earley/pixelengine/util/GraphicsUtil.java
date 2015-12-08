package de.earley.pixelengine.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsUtil {

	/**
	 * Defaults to INT_ARGB
	 *
	 * @param width  of the image
	 * @param height of the image
	 * @return the newly created image
	 */
	public static BufferedImage create(int width, int height) {
		return create(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	/**
	 * Creates a BufferedImage based on the local graphics config
	 *
	 * @param width  of the image
	 * @param height of the image
	 * @param mode   the Image type
	 * @return the newly created image
	 */
	public static BufferedImage create(int width, int height, int mode) {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		return config.createCompatibleImage(width, height, mode);
	}

}
