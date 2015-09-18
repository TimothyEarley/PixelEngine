package de.earley.pixelengine.window.ui;

import de.earley.pixelengine.vector.Vector2i;
import java.awt.Graphics;

/**
 *
 * @author timmy
 */
public abstract class UIElement {

    Vector2i position;
    public abstract  void render(Graphics g);
    
}
