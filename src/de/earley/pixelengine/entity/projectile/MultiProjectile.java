package de.earley.pixelengine.entity.projectile;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;
import de.earley.pixelengine.window.render.Screen;

import java.util.ArrayList;

/**
 * Created by timmy on 25/10/15.
 */
public abstract class MultiProjectile extends Projectile {

	protected ArrayList<Projectile> parts;

	public MultiProjectile(Vector2f pos, Vector2f dir, float speed, int damage, long timeout, Entity sender) {
		super(pos, dir, speed, damage, timeout, sender);
		parts = new ArrayList<>();
		addParts();
		addParents();
		collidableEntity = false;
		collidableTile = false;
		collidableProjectile = false;
	}

	private void addParents() {
		for (Projectile part : parts) {
			parts.forEach(part::addSender);
		}
	}

	@Override
	public void update(long delta, Keyboard keyboard, Mouse mouse) {
		for (int i = parts.size() - 1; i >= 0; i--) {
			parts.get(i).update(delta, keyboard, mouse);
			if (parts.get(i).removed()) {
				parts.remove(i);
			}
		}
		if (parts.size() <= 0) {
			remove();
		}
	}

	@Override
	public void render(Screen screen, Vector2i offset) {
		for (Projectile part : parts) {
			part.render(screen, offset);
		}
	}

	@Override
	public void setParent(Level parent) {
		for (Projectile part : parts) {
			part.setParent(parent);
		}
	}

	@Override
	public void addSender(Class clazz) {
		super.addSender(clazz);
		for (Projectile part : parts) {
			part.addSender(clazz);
		}
	}

	@Override
	public void addSender(Entity e) {
		super.addSender(e);
		for (Projectile part : parts) {
			part.addSender(e);
		}
	}

	protected abstract void addParts();
}
