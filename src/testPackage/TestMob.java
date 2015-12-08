package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.Mob;
import de.earley.pixelengine.entity.projectile.Projectile;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.util.StaticRandom;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;
import de.earley.pixelengine.window.render.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author timmy
 */
public class TestMob extends Mob {

	Vector2f dir = new Vector2f();
	int size = 8;
	long cooldown;

	public TestMob(Vector2f pos) {
		drawable = new SolidColourSprite(0xffff0000, size, size);
		position = pos;
		collisionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
		speed = 0.0000002f;

		collidableProjectile = true;
		collidableTile = true;
	}

	@Override
	public void update(long delta, Keyboard keys, Mouse mouse) {

		if (cooldown >= 0) {
			cooldown -= delta;
		}
		dir.x = dir.y = 0;
		if (keys.isKeyDown(KeyEvent.VK_W))
			dir.y -= delta * speed;
		if (keys.isKeyDown(KeyEvent.VK_S))
			dir.y += delta * speed;
		if (keys.isKeyDown(KeyEvent.VK_A))
			dir.x -= delta * speed;
		if (keys.isKeyDown(KeyEvent.VK_D))
			dir.x += delta * speed;

		move(dir);

		if (mouse.isButtonDown(1) && cooldown <= 0) {
			cooldown = 50000000l;
			Vector2f projectileDir = mouse.getMouse();
//			projectileDir.sub(TestGameState.offset);
			projectileDir.sub(position);
			projectileDir.sub(size / 2); // center
			int col = (StaticRandom.rand.nextInt(0xffffff) & 0xf00050) | 0xff000000;
			Projectile p = new TestProjectile(position.copy().add(size / 2).add(projectileDir.normalize().mult(size)), projectileDir, 0.0000001f, 2, col, this);
			parent.add(p);
		}
	}

	@Override
	public void render(Screen screen, Vector2i offset) {
		super.render(screen, offset); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void collide(Entity other) {
	}


}
