package de.earley.pixelengine.level;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.Screen;

public class Tile {

	private Drawable drawable;
	private boolean solid;
	private String name;

	public Tile(Drawable drawable, boolean solid, String name) {
		this.drawable = drawable;
		this.solid = solid;
		this.name = name;
	}

	public void render(Screen screen, Vector2i pos) {
		screen.renderDrawable(pos.x, pos.y, drawable);
	}

	public boolean canMove(Entity e) {
		return !solid;
	}

	@Override
	public String toString() {
		return name;
	}

}
