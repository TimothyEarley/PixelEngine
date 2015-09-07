package de.earley.pixelengine.level;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.util.Range;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Level {

    private TileLayer[] tileLayers;
    private ArrayList<Entity> entities;
    
    /**
     * The length of one mob step, the higher it is, the lower the precision
     */
    public float stepSizeSquared;

    public Level(TileLayer ... tileLayers) {
	this.tileLayers = tileLayers;
	entities = new ArrayList<>();
    }

    public void update(int delta , Window window) {
	for (int i = entities.size() - 1; i >= 0; i--) {
	    Entity e = entities.get(i);
	    e.update(delta, window);
	    if (e.removed()) {
		entities.remove(i);
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
	    Entity e = entities.get(i);
	    e.render(screen, offset);
	}
    }

    /**
     * 
     * @param e
     * @param x
     * @param y 
     */
    public boolean canMove(Entity e, float x, float y) {
	for (TileLayer tileLayer : tileLayers) {
	    Rectangle2D box = e.getCollissionBox();
	    x += box.getX();
	    y += box.getY();
	    int xTileLeft = (int) Math.floor(x / tileLayer.getTileWidth());
	    int yTileTop = (int) Math.floor(y / tileLayer.getTileHeight());
	    
	    int xTileRight = (int) Math.floor((x + box.getWidth()) / tileLayer.getTileWidth());
	    int yTileBottom = (int) Math.floor((y + box.getHeight()) / tileLayer.getTileHeight());
	    	    
	    for (int xTile = xTileLeft; xTile <= xTileRight; xTile++) {
		for (int yTile = yTileTop; yTile <= yTileBottom; yTile++) {
		    if (!tileLayer.getTile(xTile, yTile).canMove(e)) {
			return false;
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

    public TileLayer getTileLyer(int i) {
	if (Range.isInRangeExclusive(i, -1, tileLayers.length)) {
	    return tileLayers[i];
	} else {
	    return null;
	}
    }
}
