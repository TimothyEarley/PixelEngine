package testPackage;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.level.Tile;
import de.earley.pixelengine.level.TileLayer;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.GraphicsHelper;
import de.earley.pixelengine.window.render.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

/**
 *
 * @author timmy
 */
public class GameTest extends Game {
    
    private static Level level;
    public static Vector2i offset;
    private static TestMob player;
    private static Vector2f mouse, screenMouse;
    
    public static void test() {
        Screen screen = new Screen(200, 200, 1000, 1000, 0, 0, (viewport) -> { render((Screen) viewport); });
	GraphicsHelper gui = new GraphicsHelper(1000, 1000, 0, 0, (viewport) -> {renderGUI(((GraphicsHelper) viewport).getGraphics()); });
        Window window = new Window("Game", 1000, 1000);
        window.addViewport(screen);
	window.addViewport(gui);
	window.setBackground(Color.BLACK);
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
	idHash.put(0xffff00ff, new Tile(new SolidColourSprite(0xff2a2a2a, 32, 32), false, "gray"));
	idHash.put(0xffffffff, new Tile(new SolidColourSprite(0xff0000ff, 32, 32), true, "blue"));
	
	int tileWidth = 32;
	int tileHeight = 32;
	TileLayer tileLayer = new TileLayer("/level.png", tileWidth, tileHeight, idHash, null);
	level = new Level(tileLayer);
	
	level.stepSizeSquared = 256; // 16
	
	player = new TestMob(new Vector2f(32 + 32, 32 + 32));
	level.add(player);
	
	Spawner s = new Spawner(new Vector2f(32 + 16 + 10, 32 + 10));
	level.add(s);
	
	offset = new Vector2i();
			
    }
    
    private static void render(Screen screen) {
	screen.clear();
	level.render(screen, offset);

//	screen.setColour(Color.RED);
//	screen.fillRect((int) screenMouse.x - 2, (int) screenMouse.y - 2, 4, 4);
    }
    
    private static void renderGUI(Graphics g) {
//	g.fillOval((int) mouse.x - 8, (int) mouse.y - 8, 16, 16);
    }

    @Override
    protected void update(long delta, Window window) {
	level.update(delta, window);
	offset = new Vector2i().sub(player.getPosition().toVector2i());
	offset.add(100 - player.getDrawable().getWidth()/2, 100 - player.getDrawable().getHeight()/2);
	mouse = window.transformMouse(1);
	screenMouse = window.transformMouse(0);
    }
    
}
