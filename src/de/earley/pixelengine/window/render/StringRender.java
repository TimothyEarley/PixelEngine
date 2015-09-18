package de.earley.pixelengine.window.render;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
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
		if (string == null || font == null)
			return;
		g.setFont(font);
		int height = getRenderHeight(g, string);
		g.drawString(string, x, y + height * 3 / 4);
		g.setColor(Color.CYAN);
		g.fillOval(x - 2, y - 2, 4, 4);
	}

	public void drawStringLeft(Graphics g, String string, int x, int y, float height) {
		drawStringLeft(g, string, x, y, getFontHeight(height));
	}

	/**
	 * Draws  to the left of x
	 */
	public void drawStringLeft(Graphics g, String string, int x, int y, Font font) {
		if (string == null || font == null)
			return;
		g.setFont(font);
		int width = getRenderWidth(g, string);
		int height = getRenderHeight(g, string);

		g.drawString(string, x - width, y - height / 2);
	}

	public void drawStringCentered(Graphics g, String string, int x, int y, float height) {
		drawStringCentered(g, string, x, y, getFontHeight(height));
	}


	/**
	 * Centers the string in x and y direction. Includes line breaks
	 */
	public void drawStringCentered(Graphics g, String string, int x, int y, Font font) {
		if (string == null || font == null)
			return;
		g.setFont(font);
		int height = getRenderHeight(g, string);
		
		String[] lines = string.split("\n", -1);
		if (lines.length != 1) {
			for (int i = 0; i < lines.length; i++) {
				// convert empty lines to a line with space, so that double \n have an effect
				drawStringCentered(g, lines[i].isEmpty() ? " " : lines[i], x, y + (i - lines.length/2) * height, font);
			}
			return;
		}

		int lenght = getRenderWidth(g, string);
		g.drawString(string, x - lenght / 2, y - height / 2);
	}
	
	public static int getRenderWidth(Graphics g, String string, Font font) {
	    g.setFont(font);
	    return getRenderWidth(g, string);
	}
	
	public static int getRenderWidth(Graphics g, String string) {
	   return (int) g.getFontMetrics().getStringBounds(string, g).getWidth();
	}
	
	public static int getRenderHeight(Graphics g, String string, Font font) {
	    g.setFont(font);
	    return getRenderHeight(g, string);
	}
	
	public static int getRenderHeight(Graphics g, String string) {
	   return (int) g.getFontMetrics().getStringBounds(string, g).getHeight();
	}
	
	private static Font getFontHeight(float height) {
		return font.deriveFont(height);
	}
    
}
