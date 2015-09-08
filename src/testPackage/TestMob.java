package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.Mob;
import de.earley.pixelengine.entity.Projectile;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.util.StaticRandom;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.input.Input;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author timmy
 */
public class TestMob extends Mob {

    Vector2f dir = new Vector2f();
    int size = 8;
    long cooldown;

    public TestMob(Vector2f pos) {
	drawable = new SolidColourSprite(0xffff0000, size, size);
	position = pos;
	collissionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
	speed = 10000000;
	
	collidableProjectile = true;
	collidableTile = true;
    }
    
    @Override
    public void update(int delta, Window window) {
	if (cooldown >= 0) {
	    cooldown -= delta;
	}
	Input in = window.getInput();
	dir.x = dir.y = 0;
	if (in.keyboard.isKeyDown(KeyEvent.VK_W))
	    dir.y -= delta / speed;
	if (in.keyboard.isKeyDown(KeyEvent.VK_S))
	    dir.y += delta / speed;
	if (in.keyboard.isKeyDown(KeyEvent.VK_A))
	    dir.x -= delta / speed;
	if (in.keyboard.isKeyDown(KeyEvent.VK_D))
	    dir.x += delta / speed;
	
	move(dir);
	if (in.mouse.isButtonDown(1) && cooldown <= 0) {
		cooldown = 50000000l; 
		Vector2f projectileDir = window.transformMouse(0);
		projectileDir.sub(GameTest.offset);
		projectileDir.sub(position);
		projectileDir.sub(size/2); // center
		int col = (StaticRandom.rand.nextInt(0xffffff) & 0xf00050) | 0xff000000;
		Projectile p = new Projectile(position.copy().add(size/2).add(projectileDir.normalize().mult(size)), projectileDir, 10000000, 2, col);
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
