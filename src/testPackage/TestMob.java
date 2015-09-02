package testPackage;

import de.earley.pixelengine.entity.Mob;
import de.earley.pixelengine.entity.Projectile;
import de.earley.pixelengine.sprite.SolidColourSprite;
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

    public TestMob() {
	drawable = new SolidColourSprite(0xffff0000, 30, 30);
	position = new Vector2f(1, 1);
	collissionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
	speed = 10000000;
    }
    
    @Override
    public void update(int delta, Window window) {
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
	if (in.mouse.isButtonDown(1)) {
		Vector2f projectileDir = window.transformMouse();
		projectileDir.sub(485);
		parent.add(new Projectile(position.copy().add(15), projectileDir.normalize().mult(1)));
	}
    }

    @Override
    public void render(Screen screen, Vector2i offset) {
	super.render(screen, offset); //To change body of generated methods, choose Tools | Templates.
    }
       
    
}
