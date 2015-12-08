package de.earley.pixelengine.level;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.projectile.Projectile;
import de.earley.pixelengine.util.Range;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;
import de.earley.pixelengine.window.render.Screen;

import java.awt.*;
import java.util.ArrayList;

public class Level {

	private final TileLayer[] tileLayers;
	private final ArrayList<Entity> entities;
	private final ArrayList<Projectile> projectiles;

	/**
	 * The length of one mob step, the higher it is, the lower the precision
	 */
	public float stepSizeSquared = 1;

	public Level(TileLayer... tileLayers) {
		this.tileLayers = tileLayers;
		this.entities = new ArrayList<>();
		this.projectiles = new ArrayList<>();
	}

	public void update(long delta, Keyboard keyboard, Mouse mouse) {
		for (int i = entities.size() - 1; i >= 0; i--) {
			Entity e = entities.get(i);
			e.update(delta, keyboard, mouse);
			if (e.removed()) {
				entities.remove(i);
			}
		}

		for (int i = projectiles.size() - 1; i >= 0; i--) {
			Projectile p = projectiles.get(i);
			p.update(delta, keyboard, mouse);
			if (p.removed()) {
				projectiles.remove(i);
			}
		}
	}

	/**
	 * Renders all entities and visible tiles
	 *
	 * @param screen where to render to
	 * @param offset the amount added to each tile position. If positive, level moves right/down.
	 */
	public void render(Screen screen, Vector2i offset) {
		for (TileLayer tileLayer : tileLayers) {
			tileLayer.render(screen, offset);
		}
		//TODO still concurrentmodificationexception
		for (int i = entities.size() - 1; i >= 0; i--) {
			entities.get(i).render(screen, offset);
		}
		for (int i = projectiles.size() - 1; i >= 0 && projectiles.size() > i; i--) {
			projectiles.get(i).render(screen, offset);
		}
	}

	/**
	 * @param e the entity to inspect
	 * @param x the desired x position
	 * @param y the desired y position
	 */
	public boolean canMove(Entity e, float x, float y) {

		Rectangle newBox = new Rectangle(e.getCollisionBox());
		newBox.translate((int) x, (int) y);

		if (e.collidesWithEntities()) {
			entities.stream().filter(other -> e != other).forEach(other -> collideEntity(e, newBox, other));
		}

		if (e.collidesWithProjectiles()) {
			projectiles.stream().filter(projectile -> e != projectile).forEach(projectile -> collideEntity(e, newBox, projectile));
		}

		if (e.collidesWithTiles()) {
			for (TileLayer tileLayer : tileLayers) {
				int xTileLeft = (int) Math.floor(newBox.getX() / tileLayer.getTileWidth());
				int yTileTop = (int) Math.floor(newBox.getY() / tileLayer.getTileHeight());

				int xTileRight = (int) Math.floor((newBox.getX() + newBox.width) / tileLayer.getTileWidth());
				int yTileBottom = (int) Math.floor((newBox.getY() + newBox.height) / tileLayer.getTileHeight());

				for (int xTile = xTileLeft; xTile <= xTileRight; xTile++) {
					for (int yTile = yTileTop; yTile <= yTileBottom; yTile++) {
						if (!tileLayer.getTile(xTile, yTile).canMove(e)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private boolean collideEntity(Entity e, Rectangle newBox, Entity other) {
		// Only collide if it collides with entities, except if e is an Projectile, the only collide if collidesWithProjectile
		if ((e instanceof Projectile && other.collidesWithProjectiles()) || (!(e instanceof Projectile) && other.collidesWithEntities())) {
			Rectangle otherBox = new Rectangle(other.getCollisionBox());
			otherBox.translate((int) other.getPosition().x, (int) other.getPosition().y);
			if (otherBox.intersects(newBox)) {
				other.collide(e);
				e.collide(other);
				return true;
			}
		}
		return false;
	}

	public void add(Entity e) {
		e.setParent(this);
		entities.add(e);
	}

	public void add(Projectile p) {
		p.setParent(this);
		projectiles.add(p);
	}

	public TileLayer getTileLayer(int i) {
		if (Range.isInRangeExclusive(i, -1, tileLayers.length)) {
			return tileLayers[i];
		} else {
			return null;
		}
	}

	public Entity getFirstEntity(int x, int y) {
		for (Entity entity : entities) {
			if (entity.getCollisionBox().contains(x - entity.getPosition().x, y - entity.getPosition().y)) {
				return entity;
			}
		}
		return null;
	}
}
