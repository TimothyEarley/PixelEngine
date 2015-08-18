package de.earley.pixelengine.level;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.util.CrashHandler;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.render.Screen;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Level {

    private TileLayer[] tileLayers;
    private ArrayList<Entity> entities;

    public Level(TileLayer ... tileLayers) {
	this.tileLayers = tileLayers;
	entities = new ArrayList<>();
    }
    
    public Level(String path) {
	try {
		BufferedImage input = ImageIO.read(Level.class.getResource(path));
	} catch (Exception e) {
		CrashHandler.crash(e);
	}
    }

    /**
     * Renders all entities and visible tiles
     * @param screen where to render to
     * @param offset the amount added to each tile position. If positive, level moves right/down.
     */
    public void render(Screen screen, Vector2i offset) {
	for (Entity entity : entities) {
		entity.render(null, offset);
	}
	for (TileLayer tileLayer : tileLayers) {
	    tileLayer.render(screen, offset);
	}
    }

}
