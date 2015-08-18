/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.level.Tile;
import de.earley.pixelengine.level.TileLayer;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author timmy
 */
public class GameTest extends Game {
    
    private static Level level;
    private static Vector2i offset;
        
    public static void test() {
        Screen screen = new Screen(200, 200, 1000, 1000, 0, 0, (viewport) -> { render((Screen) viewport); });
        Window window = new Window("Game", 1000, 1000);
        window.addViewport(screen);
        GameTest gameTest = new GameTest(window);
        gameTest.start();
    }

    private static void render(Screen screen) {
	screen.clear();
	level.render(screen, offset);
    }

    public GameTest(Window window) {
        super(window);
    }

    @Override
    protected void init() {
	offset = new Vector2i();
	
	HashMap<Integer, Tile> idHash = new HashMap<>();
	idHash.put(0xffff00ff, new Tile(new SolidColourSprite(0xff00ff00, 32, 32), true));
	
	int tileWidth = 32;
	int tileHeight = 32;
	TileLayer tileLayer = new TileLayer("/level.png", tileWidth, tileHeight, idHash, null);
	level = new Level(tileLayer);
    }
    
    

    @Override
    protected void update(int delta, Window window) {
	int speed = 5000000;
	if (window.getInput().keyboard.isKeyDown(KeyEvent.VK_W))
	    offset.y+=delta/speed;
	if (window.getInput().keyboard.isKeyDown(KeyEvent.VK_S))
	    offset.y-=delta/speed;
	if (window.getInput().keyboard.isKeyDown(KeyEvent.VK_A))
	    offset.x+=delta/speed;
	if (window.getInput().keyboard.isKeyDown(KeyEvent.VK_D))
	    offset.x-=delta/speed;
	
    }
    
}
