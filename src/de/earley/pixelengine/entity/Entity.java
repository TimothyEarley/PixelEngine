package de.earley.pixelengine.entity;

import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Rectangle;

public abstract class Entity {

    protected Level parent;
    protected Drawable drawable;
    protected Vector2f position;
    protected boolean collidableTile, collidableEntity, collidableProjectile;
    protected Rectangle collissionBox;
    private boolean removed;
    
    public abstract void update(long delta, Window window);

    public void render(Screen screen, Vector2i offset) {
	if (drawable != null) {
	    screen.renderDrawable((int) (position.x + offset.x), (int) (position.y + offset.y), drawable);
	}
    }
    
    public Drawable getDrawable() {
	return drawable;
    }

    public Vector2f getPosition() {
	return position.copy();
    }

    public Rectangle getCollissionBox() {
	return collissionBox;
    }

    public void remove() {
	removed = true;
    }
    
    public boolean removed() {
	return removed;
    }
    
    public void setParent(Level parent) {
	this.parent = parent;
    }
    
    protected Vector2f  bounce(Vector2f dir) {
	return bounce(dir, 1);
    }

    
    protected Vector2f  bounce(Vector2f dir, float speed) {
	if (!move(new Vector2f(dir.x * speed, 0), false)) {
	    dir.x *= -1;
	}
	if (!move(new Vector2f(0, dir.y * speed), false)) {
	    dir.y *= -1;
	}
	return dir;
    }
    
    protected boolean move(Vector2f dir) {
	return move(dir, true);
    }
    
    /**
     * Attempts to move in the direction.
     * @param dir
     * @param slide if true, the entity can slide along the axis
     * @return true if no obstacle encountered
    */
    protected boolean move(Vector2f dir, boolean slide) {
	
	if (!collidableTile && !collidableEntity && !collidableProjectile) {
	    position.add(dir);
	    return true;
	}
	
	float length = dir.lengthSquare();
	if (length == 0) {
	    return true;
	}
	else if (length > parent.stepSizeSquared) {
	    dir.mult(0.5f);
	    if (move(dir)) {
		move(dir);
		return true;
	    }
	   return false;
	} else {
	    //allow sliding
	    if (slide && dir.x != 0 && dir.y != 0) {
		boolean moved = move(new Vector2f(dir.x, 0));
		if (move(new Vector2f(0, dir.y))) {
		    moved = true;
		}
		return moved;
	    }
	    float newX = position.x + dir.x;
	    float newY = position.y + dir.y;

	    if (parent.canMove(this, newX, newY)) {
		position.x = newX;
		position.y = newY;
		return true;
	    } else {
		return false;
	    }
	}
    }

    public boolean collidesWithTiles() {
	return collidableTile;
    }

    public boolean collidesWithEntities() {
	return collidableEntity;
    }

    public boolean collidesWithProjectiles() {
	return collidableProjectile;
    }

    /**
     * Called, when entity - entity collision happens
     * @param other 
     */
    public void collide(Entity other) {
    }
}
