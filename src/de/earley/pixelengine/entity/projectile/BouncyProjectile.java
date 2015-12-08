package de.earley.pixelengine.entity.projectile;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;

/**
 * Created by timmy on 10/15/15.
 */
public abstract class BouncyProjectile extends Projectile {


	public BouncyProjectile(Vector2f pos, Vector2f dir, float speed, int damage, Entity sender) {
		super(pos, dir, speed, damage, sender);
	}


	@Override
	public void update(long delta, Keyboard keyboard, Mouse mouse) {
		timeout -= delta;
		bounce(dir, delta * speed);
		if (timeout < 0) {
			remove();
		}

	}

}
