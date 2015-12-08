package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.projectile.BouncyProjectile;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.vector.Vector2f;

/**
 * Created by timmy on 10/15/15.
 */
public class TestProjectile extends BouncyProjectile {


	public TestProjectile(Vector2f pos, Vector2f dir, float speed, int size, int colour, Entity sender) {
		super(pos, dir, speed, 10, sender);
		this.drawable = new SolidColourSprite(colour, size, size);
	}
}
