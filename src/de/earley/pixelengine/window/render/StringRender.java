package de.earley.pixelengine.window.render;

import java.awt.*;

/**
 * @author timmy
 */
public class StringRender {

	private static Font font = new Font("Verdana", Font.PLAIN, 50);

	public static void setFont(Font font) {
		StringRender.font = font;
	}


	public static void drawStringRight(Graphics g, String string, int x, int y, float height) {
		drawStringRight(g, string, x, y, getFontHeight(height));
	}

	/**
	 * Draws to the right, bottom of x
	 */
	public static void drawStringRight(Graphics g, String string, int x, int y, Font font) {
		g.setFont(font);
		int height = getRenderHeight(g, string);
		g.drawString(string, x, y + height * 3 / 4);
	}

	private static Font getFontHeight(float height) {
		return font.deriveFont(height);
	}

	public static int getRenderHeight(Graphics g, String string) {
		return (int) g.getFontMetrics().getStringBounds(string, g).getHeight();
	}

	public static void drawStringLeft(Graphics g, String string, int x, int y, float height) {
		drawStringLeft(g, string, x, y, getFontHeight(height));
	}

	/**
	 * Draws  to the left of x
	 */
	public static void drawStringLeft(Graphics g, String string, int x, int y, Font font) {
		g.setFont(font);
		int width = getRenderWidth(g, string);
		int height = getRenderHeight(g, string);
		g.drawString(string, x - width, y - height / 2);
	}

	public static int getRenderWidth(Graphics g, String string) {
		return (int) g.getFontMetrics().getStringBounds(string, g).getWidth();
	}

	public static void drawStringCentered(Graphics g, String string, int x, int y, float height) {
		drawStringCentered(g, string, x, y, getFontHeight(height));
	}

	/**
	 * Centers the string in x and y direction. Includes line breaks
	 */
	public static void drawStringCentered(Graphics g, String string, int x, int y, Font font) {
		if (string == null || font == null)
			return;
		g.setFont(font);
		int height = getRenderHeight(g, string);

		String[] lines = string.split("\n", -1);
		if (lines.length != 1) {
			for (int i = 0; i < lines.length; i++) {
				// convert empty lines to a line with space, so that double \n have an effect
				drawStringCentered(g, lines[i].isEmpty() ? " " : lines[i], x, y + (i - lines.length / 2) * height, font);
			}
			return;
		}

		int length = getRenderWidth(g, string);
		g.drawString(string, x - length / 2, y - height / 2);
	}

	public static int getRenderWidth(Graphics g, String string, Font font) {
		g.setFont(font);
		return getRenderWidth(g, string);
	}

	public static int getRenderHeight(Graphics g, String string, Font font) {
		g.setFont(font);
		return getRenderHeight(g, string);
	}

}
