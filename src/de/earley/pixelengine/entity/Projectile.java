package de.earley.pixelengine.entity;

import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.util.StaticRandom;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.Window;
import java.awt.Rectangle;

/**
 *
 * @author timmy
 */
public class Projectile extends Entity {

    private long timeout;
    private Vector2f dir;
    private int speed;
    
    public Projectile(Vector2f pos, Vector2f dir, int speed) {
	this.dir = dir.normalize();
	this.speed = speed;
	this.position = pos;
	int col = StaticRandom.rand.nextInt(0xffffff) & 0xff00f0a0;
	this.drawable = new SolidColourSprite(0xff000000 | col, 2, 2);
	this.collissionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
	
	this.timeout = 10 * 1000000000l;
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
