package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.util.Action;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.StringRender;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author timmy
 */
public class UILabelButton extends UIButton {

    Action action;
    UILabel label;
    Color bg;
    int width, height, padding;
    Font font;
    
    public UILabelButton(Vector2i position, String text, Font font, Color fontColor, Color bg, int padding, Action action) {
	this.position = position;
	this.action = action;
	this.bg = bg;
	this.font = font;
	this.padding = padding;
	label = new UILabel(position.copy().add(padding), text, font, fontColor);
    }

    @Override
    public void doAction() {
	action.fire();
    }

    @Override
    public void render(Graphics g) {
	if (width == 0) {
	    width = StringRender.getRenderWidth(g, label.getText(), font);
	    width += 2 * padding;
	}
	if (height == 0) {
	    height = StringRender.getRenderHeight(g, label.getText(), font);
	    height += 2 * padding;
	}
	g.setColor(bg);
	g.fillRect(position.x, position.y, width, height);
	label.render(g);
    }

}
