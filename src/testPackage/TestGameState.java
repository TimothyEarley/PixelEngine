package testPackage;

import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.level.Tile;
import de.earley.pixelengine.level.TileLayer;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.input.MouseManager;
import de.earley.pixelengine.window.render.Screen;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by timmy on 9/18/15.
 */
public class TestGameState extends Layer {

	public Vector2i offset;
	private Level level;
	private TestMob player;
	private Vector2f screenMouse;

	private int x, y;

	public TestGameState(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean update(long delta, Window window) {
		MouseManager m = getNewMouse(window);
		screenMouse = m.getMouse();
		m.addOffset(offset.copy().mult(-1));
		level.update(delta, window.getInput().keyboard, m);
		offset = new Vector2i().sub(player.getPosition().toVector2i());
		offset.add(100 - player.getDrawable().getWidth() / 2, 100 - player.getDrawable().getHeight() / 2);
		return false;
	}

	@Override
	public void init() {

		viewport = new Screen(200, 200, parent.getWidth() / 2, parent.getHeight() / 2, x, y, (viewport) -> render((Screen) viewport));


		offset = new Vector2i();

		HashMap<Integer, Tile> idHash = new HashMap<>();
		idHash.put(0xffff00ff, new Tile(new SolidColourSprite(0xff2a2a2a, 32, 32), false, "gray"));
		idHash.put(0xffffffff, new Tile(new SolidColourSprite(0xff0000ff, 32, 32), true, "blue"));

		int tileWidth = 32;
		int tileHeight = 32;
		TileLayer tileLayer = new TileLayer("/level.png", tileWidth, tileHeight, idHash, null);
		level = new Level(tileLayer);

		level.stepSizeSquared = 1;

		player = new TestMob(new Vector2f(32 + 32, 32 + 32));
		level.add(player);

		Spawner s = new Spawner(new Vector2f(32 + 16 + 10, 32 + 10));
		level.add(s);

		offset = new Vector2i();

		screenMouse = new Vector2f();

	}

	private void render(Screen screen) {
		screen.clear();
		level.render(screen, offset);

		screen.setColour(Color.RED);
		screen.fillRect((int) screenMouse.x - 2, (int) screenMouse.y - 2, 4, 4);
	}

}
