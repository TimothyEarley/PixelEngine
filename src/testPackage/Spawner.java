package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.projectile.Projectile;
import de.earley.pixelengine.util.StaticRandom;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;

/**
 * @author timmy
 */
class Spawner extends Entity {

	private long cooldown;

	public Spawner(Vector2f pos) {
		this.position = pos;
	}


	@Override
	public void update(long delta, Keyboard keyboard, Mouse mouse) {
		if (cooldown > 0) {
			cooldown -= delta;
		} else {
//	    Vector2f dir = new Vector2f(StaticRandom.rand.nextFloat() - 0.5f, StaticRandom.rand.nextFloat() - 0.5f);
			Vector2f dir = new Vector2f(0.4f, 0.6f);
			Projectile p = new TestProjectile(position.copy().add(dir), dir, 0.0000001f, 16, (StaticRandom.rand.nextInt(0xffffff) & 0x00f0a0) | 0xff000000, this);
			parent.add(p);
			cooldown = 1000000000;
		}
	}

}
