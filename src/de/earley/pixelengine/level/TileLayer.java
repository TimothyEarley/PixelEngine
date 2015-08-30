package de.earley.pixelengine.level;

import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.sprite.Sprite;
import de.earley.pixelengine.util.Range;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.Screen;
import java.util.HashMap;

/**
 *
 * @author timmy
 */
public class TileLayer {

    private HashMap<Integer, Tile> tileID;
    private int[] tiles;
    private Vector2i offset;
    private int width, height, tileWidth, tileHeight;
    
    //To be used as placeholder;
    private Tile voidTile;

    /**
     * 
     * @param tiles The IDs of all tiles going left to right, top to bottom
     * @param tileWidth the width in pixels of a single tile
     * @param tileHeight the height in pixels of a single tile
     * @param width the tile count horizontally
     * @param height the tile count vertically
     * @param offset the static render offset
     * @param tileID matches each id to a tile
     */
    TileLayer(int[] tiles, int tileWidth, int tileHeight, int width, int height, Vector2i offset, HashMap<Integer, Tile> tileID) {
	this.tiles = tiles;
	this.tileWidth = tileWidth;
	this.tileHeight = tileHeight;
	this.width = width;
	this.height = height;
	this.offset = offset;
	this.tileID = tileID;
    }

        public TileLayer(String path, int tileWidth, int tileheight, HashMap<Integer, Tile> tileID) {
	    this(path, tileWidth, tileheight, tileID, new Vector2i());
	}
    
    public TileLayer(String path, int tileWidth, int tileHeight, HashMap<Integer, Tile> tileID, Vector2i offset) {
	Sprite img = new Sprite(path);
	this.width = img.getWidth();
	this.height = img.getHeight();
	this.tileWidth = tileWidth;
	this.tileHeight = tileHeight;
	this.offset = offset;
	this.tileID = tileID;
	
	this.tiles = new int[width * height];
	
	for (int x = 0; x < width; x++) {
	    for (int y = 0; y < height; y++) {
		tiles[x + y * width] = img.getPixel(x, y);
	    }
	}
    }

    void render(Screen screen, Vector2i offset) {
	for (int x = 0; x < width; x++) {
	    for (int y = 0; y < height; y++) {
		getTile(x, y).render(screen, new Vector2i(x * tileWidth + offset.x, y * tileHeight + offset.y));
	    }
	}
    }

    private Tile getVoidTile() {
	if (voidTile == null) {
	    voidTile = new Tile(new SolidColourSprite(0xffff00ff, tileWidth, tileHeight), true, "VOID_TILE");
	}
	return voidTile;
    }
    
    public int getTileWidth() {
	return tileWidth;
    }
    
    public int getTileHeight() {
	return tileHeight;
    }

    public Tile getTile(int x, int y) {
	if (Range.isInRangeExclusive(x, -1, width) && Range.isInRangeExclusive(y, -1, height)) {
	    return tileID.getOrDefault(tiles[x + y * width], getVoidTile());
	} else {
	    return getVoidTile();
	}
    }
    
    
    
}
