package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.window.Window;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author timmy
 */
public class UIMenu {

    protected ArrayList<UIButton> buttons;
    protected ArrayList<UIElement> elements;

    public UIMenu() {
	buttons = new ArrayList<>();
	elements = new ArrayList<>();
    }
    
    public UIMenu(ArrayList<UIButton> buttons, ArrayList<UIElement> elements) {
	this.buttons = buttons;
	this.elements = elements;
    }
    
    public void update(long delta, Window window, int viewportID) {
	for (UIButton button : buttons) {
	    
	}
    }
    
    public void render (Graphics g) {
	for (UIButton button : buttons) {
	    button.render(g);
	}
	for (UIElement element : elements) {
	    element.render(g);
	}
    }

    public void add(UIButton button) {
	buttons.add(button);
    }
    
    public void add(UIElement e)
    {
	elements.add(e);
    }
}
