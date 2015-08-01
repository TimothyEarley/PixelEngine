package de.earley.pixelengine.level;

import java.util.ArrayList;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.render.Screen;

public class Level {

	private int[] tiles;
	private int width, height;
	private ArrayList<Entity> entities;
        
        public void render(Screen screen, Vector2f offset) {   
	    for (Entity entity: entities) {
		entity.render(null, offset);
	    }
        }

}
