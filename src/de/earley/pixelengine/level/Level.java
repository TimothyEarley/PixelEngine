package de.earley.pixelengine.level;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.Projectile;
import de.earley.pixelengine.util.Range;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {

    private final TileLayer[] tileLayers;
    private final ArrayList<Entity> entities;
    private final ArrayList<Projectile> projectiles;
    
    /**
     * The length of one mob step, the higher it is, the lower the precision
     */
    public float stepSizeSquared;

    public Level(TileLayer ... tileLayers) {
	this.tileLayers = tileLayers;
	this.entities = new ArrayList<>();
	this.projectiles = new ArrayList<>();
    }

    public void update(long delta , Window window) {
	for (int i = entities.size() - 1; i >= 0; i--) {
	    Entity e = entities.get(i);
	    e.update(delta, window);
	    if (e.removed()) {
		entities.remove(i);
	    }
	}
	for (int i = projectiles.size() - 1; i >= 0; i--) {
	    Projectile p = projectiles.get(i);
	    p.update(delta, window);
	    if (p.removed()) {
		projectiles.remove(i);
	    }
	}
    }
    
    /**
     * Renders all entities and visible tiles
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
	for (int i = projectiles.size() - 1; i >= 0; i--) {
	    projectiles.get(i).render(screen, offset);
	}
    }

    /**
     * 
     * @param e
     * @param x
     * @param y 
     */
    public boolean canMove(Entity e, float x, float y) {
	
	Rectangle newBox = new Rectangle(e.getCollissionBox());
	newBox.translate((int) x, (int) y);
	
	if (e.collidesWithEntities()) {
	    for (Entity other : entities) {
		if (e != other)
		    collideEntity(e, newBox, other);
	    }
	}
	
	if (e.collidesWithProjectiles()) {
	    for (Projectile projectile : projectiles) {
		if (e != projectile)
		    collideEntity(e, newBox, projectile);
	    }
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

    public void add(Entity e) {
	e.setParent(this);
	entities.add(e);
    }
    
    public void add(Projectile p) {
	p.setParent(this);
	projectiles.add(p);
    }

    public TileLayer getTileLyer(int i) {
	if (Range.isInRangeExclusive(i, -1, tileLayers.length)) {
	    return tileLayers[i];
	} else {
	    return null;
	}
    }

    private boolean collideEntity(Entity e, Rectangle newBox, Entity other) {
	// Only collide if it collides with entites, except if e is an Projectile, the only collide if collidesWithProjectile
	if ((e instanceof Projectile && other.collidesWithProjectiles()) || (!(e instanceof Projectile) && other.collidesWithEntities())) {
	    Rectangle otherBox = new Rectangle(other.getCollissionBox());
	    otherBox.translate((int) other.getPosition().x, (int) other.getPosition().y);
	    if (otherBox.intersects(newBox) ) {
	        other.collide(e);
		e.collide(other);
		return true;
	    }
	}
	return false;
    }
}
