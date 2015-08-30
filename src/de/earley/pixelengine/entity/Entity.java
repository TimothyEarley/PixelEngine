package de.earley.pixelengine.entity;

import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.input.Input;
import de.earley.pixelengine.window.render.Screen;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected Level parent;
    protected Drawable drawable;
    protected Vector2f position;
    protected Rectangle2D collissionBox;
    protected boolean removed;
    
    public abstract void update(int delta, Input in);

    public void render(Screen screen, Vector2i offset) {
        screen.renderDrawable((int) (position.x + offset.x), (int) (position.y + offset.y), drawable);
    }
    
    public Drawable getDrawable() {
	return drawable;
    }

    public Vector2f getPosition() {
	return position.copy();
    }

    public Rectangle2D getCollissionBox() {
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
    
    /**
     * Attempts to move in the direction.
     * @param dir
     * @return true if no obstacle encountered
    */
    protected boolean move(Vector2f dir) {
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
	    if (dir.x != 0 && dir.y != 0) {
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
}
