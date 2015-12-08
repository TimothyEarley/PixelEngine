package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.vector.Vector2i;

import java.awt.*;

/**
 * @author timmy
 */
public abstract class UIElement extends Layer {

	protected Vector2i position;

	public abstract void render(Graphics g);
}
