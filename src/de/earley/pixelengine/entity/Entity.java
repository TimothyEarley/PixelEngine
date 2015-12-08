package de.earley.pixelengine.entity;

import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.sprite.Drawable;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;
import de.earley.pixelengine.window.render.Screen;

import java.awt.*;

public abstract class Entity {

	protected Level parent;
	protected Drawable drawable;
	protected Vector2f position;
	protected boolean collidableTile, collidableEntity, collidableProjectile;
	protected Rectangle collisionBox;
	private boolean removed;

	public abstract void update(long delta, Keyboard keyboard, Mouse mouse);

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

	public Vector2f getCenterPosition() {
		return position.copy().add(drawable.getWidth() / 2, drawable.getHeight() / 2);
	}

	public Rectangle getCollisionBox() {
		if (drawable != null && collisionBox == null) {
			collisionBox = new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight());
		}
		return collisionBox;
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

	protected void bounce(Vector2f dir) {
		bounce(dir, 1);
	}


	//TODO small steps instead of manhattan ?
	protected void bounce(Vector2f dir, float speed) {
		if (!move(new Vector2f(dir.x * speed, 0), false)) {
			dir.x *= -1;

		}
		if (!move(new Vector2f(0, dir.y * speed), false)) {
			dir.y *= -1;
		}
	}

	protected boolean move(Vector2f dir) {
		return move(dir, true);
	}

	/**
	 * Attempts to move in the direction.
	 *
	 * @param dir   the direction to move in
	 * @param slide if true, the entity can slide along the axis
	 * @return true if entity was moved
	 */
	protected boolean move(Vector2f dir, boolean slide) {

		// return value
		boolean moved = false;

		//Protection
		dir = dir.copy();

		if (!collidableTile && !collidableEntity && !collidableProjectile) {

			position.add(dir);
			moved = true;

		} else {

			float length = dir.lengthSquare();

			if (length == 0) {
				// 0 length is always successful
				moved = true;
			} else if (length > parent.stepSizeSquared) {
				dir.mult(0.5f);
				move(dir);
				moved = move(dir);
			} else {

				//allow sliding
				if (slide && dir.x != 0 && dir.y != 0) {
					boolean movedX = move(new Vector2f(dir.x, 0));
					boolean movedY = move(new Vector2f(0, dir.y));

					moved = movedX & movedY;

				} else {

					float newX = position.x + dir.x;
					float newY = position.y + dir.y;

					if (parent.canMove(this, newX, newY)) {
						position.x = newX;
						position.y = newY;
						moved = true;
					}
				}
			}
		}
		return moved;
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
	 *
	 * @param other the entity collided with
	 */
	public void collide(Entity other) {
	}

	public void projectileHit(Entity other) {

	}
}
