package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.StringRender;

import java.awt.*;

/**
 * @author timmy
 */
public class UILabel extends UIElement {

	private String text;
	private Font font;
	private Color color;

	public UILabel(Vector2i position, String text, Font font, Color color) {
		this.position = position;
		this.text = text;
		this.font = font;
		this.color = color;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		StringRender.drawStringRight(g, text, position.x, position.y, font);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
