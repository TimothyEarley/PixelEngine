package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.Projectile;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.Window;

/**
 *
 * @author timmy
 */
class Spawner extends Entity {

    public Spawner(Vector2f pos) {
	this.position = pos;
    }

    
    
    @Override
    public void update(int delta, Window window) {
//	Vector2f dir = new Vector2f(StaticRandom.rand.nextFloat() - 0.5f, StaticRandom.rand.nextFloat() - 0.5f);
	Vector2f dir = new Vector2f(0.4f, 0.6f);
	Projectile p = new Projectile(position.copy().add(dir), dir, 5000000);
	parent.add(p);
    }

}
