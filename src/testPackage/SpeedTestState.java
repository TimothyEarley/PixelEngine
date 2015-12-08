package testPackage;

import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.level.Level;
import de.earley.pixelengine.level.Tile;
import de.earley.pixelengine.level.TileLayer;
import de.earley.pixelengine.sprite.SolidColourSprite;
import de.earley.pixelengine.util.StringUtil;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;
import de.earley.pixelengine.window.render.Viewport;

import java.util.HashMap;

/**
 * Created by timmy on 10/9/15.
 */
public class SpeedTestState extends Layer {

	private Level level;
	private Vector2i offset;
	private TestMob player;
	private long startTime;
	private boolean quit;

	private void render(Viewport viewport) {
		Screen screen = (Screen) viewport;
		screen.clear();
		level.render(screen, offset);
	}

	@Override
	public boolean update(long delta, Window window) {
		level.update(delta, window.getInput().keyboard, getNewMouse(window));
		offset = new Vector2i().sub(player.getPosition().toVector2i());
		offset.add(
				getWidth() / 2 - player.getDrawable().getWidth() / 2,
				getHeight() / 2 - player.getDrawable().getHeight() / 2
		);
		return quit;
	}

	public void init() {

		//dddTODO better performance with huge screens
		viewport = new Screen(parent.getWidth(), parent.getHeight(), 0, 0, this::render);

		HashMap<Integer, Tile> colourMap = new HashMap<>();

		int tileWidth = 50, tileHeight = 50;

		colourMap.put(0xffffffff, new Tile(new SolidColourSprite(0xffffffff, tileWidth, tileHeight), false, "path"));
		colourMap.put(0xff000000, new Tile(new SolidColourSprite(0xff000000, tileWidth, tileHeight), true, "wall"));
		colourMap.put(0xffff0000, new EndTile(new SolidColourSprite(0xffff0000, tileWidth, tileHeight), false, "end", this));
		colourMap.put(0xff00ff00, new StartTile(new SolidColourSprite(0xff00ff00, tileWidth, tileHeight), false, "start", this));


		TileLayer layer = new TileLayer("/speedMap.png", tileWidth, tileHeight, colourMap);

		level = new Level(layer);

		level.stepSizeSquared = 650;

		offset = new Vector2i();

		player = new TestMob(new Vector2f(tileWidth * 1.5f, tileHeight * 1.5f));

		level.add(player);

	}

	public void startTimer() {
		if (startTime == 0) {
			startTime = System.nanoTime();
		}
	}


	public void endTimer() {
		if (startTime != 0) {
			System.out.println("Time: " + StringUtil.toDecimal((System.nanoTime() - startTime) / 1000000000d, 2, true));
			startTime = 0;
			quit = true;
		}
	}
}
