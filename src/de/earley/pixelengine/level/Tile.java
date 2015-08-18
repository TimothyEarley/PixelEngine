package de.earley.pixelengine.level;

import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.Screen;

public class Tile {
    
	private Drawable drawable;
	private boolean solid;

	public Tile(Drawable drawable, boolean solid) {
	    this.drawable = drawable;
	    this.solid = solid;
	}
	
	public void render(Screen screen, Vector2i pos) {
	    screen.renderDrawable(pos.x, pos.y, drawable);
	}
}
