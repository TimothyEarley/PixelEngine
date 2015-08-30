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
import de.earley.pixelengine.window.render.GraphicsHelper;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;

/**
 *
 * @author timmy
 */
public class GameTest extends Game {
    
    private static Level level;
    private static Vector2i offset;
    private static TestMob player;
    
    public static void test() {
        Screen screen = new Screen(200, 200, 1000, 1000, 0, 0, (viewport) -> { render((Screen) viewport); });
	GraphicsHelper gui = new GraphicsHelper(1000, 1000, 0, 0, (viewport) -> {renderGUI(((GraphicsHelper) viewport).getGraphics()); });
        Window window = new Window("Game", 1000, 1000);
        window.addViewport(screen);
	window.addViewport(gui);
        GameTest gameTest = new GameTest(window);
        gameTest.start();
    }

    public GameTest(Window window) {
        super(window);
    }

    @Override
    protected void init() {
	offset = new Vector2i();
	
	HashMap<Integer, Tile> idHash = new HashMap<>();
	idHash.put(0xffff00ff, new Tile(new SolidColourSprite(0xff00ff00, 32, 32), false, "green"));
	idHash.put(0xffffffff, new Tile(new SolidColourSprite(0xffffffff, 32, 32), true, "white"));
	
	int tileWidth = 32;
	int tileHeight = 32;
	TileLayer tileLayer = new TileLayer("/level.png", tileWidth, tileHeight, idHash, null);
	level = new Level(tileLayer);
	
	level.stepSizeSquared = 256; // 16
	
	player = new TestMob();
	level.add(player);
	
	offset = new Vector2i();
    }
    
    private static void render(Screen screen) {
	screen.clear();
	level.render(screen, offset);
    }
    
    private static void renderGUI(Graphics2D g) {
    }

    @Override
    protected void update(int delta, Window window) {
	level.update(delta, window.getInput());
	offset = new Vector2i().sub(player.getPosition().toVector2i());
	offset.add(100 - player.getDrawable().getWidth()/2, 100 - player.getDrawable().getHeight()/2);
    }
    
}
