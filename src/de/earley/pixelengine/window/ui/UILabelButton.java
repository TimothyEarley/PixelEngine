package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.util.Action;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.StringRender;

import java.awt.*;

/**
 * @author timmy
 */
public class UILabelButton extends UIButton {

	Action action;
	UILabel label;
	Color bg, fg, fgHighlight;
	int padding;
	Font font;

	public UILabelButton(Vector2i position, String text, Font font, Color fg, Color bg, int padding, Action action) {
		this(position, text, font, fg, fg, bg, padding, action);
	}

	public UILabelButton(Vector2i position, String text, Font font, Color fg, Color fgHighlight, Color bg, int padding, Action action) {
		this.position = position;
		this.action = action;
		this.bg = bg;
		this.font = font;
		this.padding = padding;
		label = new UILabel(position.copy().add(padding), text, font, fg);
		this.fg = fg;
		this.fgHighlight = fgHighlight;
	}

	@Override
	public void entered() {
		label.setColor(fgHighlight);
	}

	@Override
	public void exited() {
		label.setColor(fg);
	}

	@Override
	public void doAction() {
		action.fire();
	}

	@Override
	public void render(Graphics g) {
		if (box == null) {
			int width = StringRender.getRenderWidth(g, label.getText(), font);
			width += 2 * padding;
			int height = StringRender.getRenderHeight(g, label.getText(), font);
			height += 2 * padding;
			box = new Rectangle(position.x, position.y, width, height);
		}
		g.setColor(bg);
		g.fillRect(position.x, position.y, (int) box.getWidth(), (int) box.getHeight());
		label.render(g);
	}

}
