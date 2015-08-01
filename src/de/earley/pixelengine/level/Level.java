package de.earley.pixelengine.level;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.util.CrashHandler;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.render.Screen;

public class Level {

	private int[] tiles;
	private int width, height;
	private ArrayList<Entity> entities;

	public Level(String path) {
		try {
			BufferedImage input = ImageIO.read(Level.class.getResource(path));
		} catch (Exception e) {
			CrashHandler.crash(e);
		}
	}
	
	public void render(Screen screen, Vector2f offset) {
		for (Entity entity : entities) {
			entity.render(null, offset);
		}
	}

}
