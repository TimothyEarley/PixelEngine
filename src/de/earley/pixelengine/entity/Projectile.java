package de.earley.pixelengine.entity;

import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.Window;
import java.awt.Rectangle;

/**
 *
 * @author timmy
 */
public class Projectile extends Entity {

    protected long timeout;
    protected Vector2f dir;
    protected int speed;
    
    public Projectile(Vector2f pos, Vector2f dir, int speed, int size, int colour) {
	this.dir = dir.normalize();
	this.speed = speed;
	this.position = pos;
	this.drawable = new SolidColourSprite(colour, size, size);
	this.collissionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
	
	this.timeout = 120 * 1000000000l;
    }
    
    
    
    @Override
    public void update(int delta, Window window) {
	timeout-=delta;
	bounce(dir, delta/speed);
	if (timeout < 0) {
	    remove();
	}
    }    
    

}
