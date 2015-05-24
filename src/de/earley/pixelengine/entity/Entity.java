package de.earley.pixelengine.entity;

import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.render.Screen;

public abstract class Entity {

	private Drawable drawable;
	private Vector2f position;
	
	public abstract void update(int delta);

	public abstract void render(Screen screen, Vector2f offset);

	public Vector2f getPosition() {
		return position.copy();
	}
	
	
}
