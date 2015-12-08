package testPackage;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.game.states.LoadingLayer;
import de.earley.pixelengine.sprite.SpriteManager;
import de.earley.pixelengine.vector.Vector2i;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.StringRender;
import de.earley.pixelengine.window.ui.UILabelButton;
import de.earley.pixelengine.window.ui.UIMenu;

import java.awt.*;

/**
 * @author timmy
 */
public class GameTest extends Game {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;

	public GameTest(Window window, Layer root) {
		super(window, root);
	}

	public static void test() {
		Layer root = new Layer(WIDTH, HEIGHT);

		Window window = new Window("Game", WIDTH, HEIGHT);
		window.setBackground(Color.BLACK);

		GameTest gameTest = new GameTest(window, root);
		gameTest.start();
	}

	@Override
	protected void init() {

		SpriteManager.defer = true;

		gameSettings.setUPS(120);

//		for (int i = 0; i < 100; i++) {
//			new Sprite("/level.png");
//		}

		TestGameState game1 = new TestGameState(0, 0);
		TestGameState game2 = new TestGameState(WIDTH / 2, 0);
		TestGameState game3 = new TestGameState(0, HEIGHT / 2);
		TestGameState game4 = new TestGameState(WIDTH / 2, HEIGHT / 2);

		Layer games = new Layer(WIDTH, HEIGHT);
		games.addChildState(game1);
		games.addChildState(game2);
		games.addChildState(game3);
		games.addChildState(game4);

		SpeedTestState speed = new SpeedTestState();

		Font font = new Font("Verdana", Font.PLAIN, 50);
		UIMenu menu = new UIMenu(WIDTH, HEIGHT);
		menu.add(new UILabelButton(new Vector2i(100, 100), "test button", font, Color.RED, Color.GREEN, 10,
				() -> {
					menu.close();
					games.openAll();
				}));

		menu.add(new UILabelButton(new Vector2i(100, 250), "speed btn", font, Color.RED, Color.GREEN, 10,
				() -> {
					menu.close();
					speed.open();
				}));


		final int lineHeight = HEIGHT / 50;

		LoadingLayer loading = new LoadingLayer(menu) {
			@Override
			protected void renderProgress(Graphics g) {
				StringRender.drawStringCentered(g, "Loading " + path, WIDTH / 2, HEIGHT / 2 - lineHeight, lineHeight);
				StringRender.drawStringCentered(g, finished + "/" + total, WIDTH / 2, HEIGHT / 2 + lineHeight, lineHeight);
			}
		};
		loading.open();


		root.addChildState(loading);
		root.addChildState(menu);
		root.addChildState(games);
		root.addChildState(speed);
	}


}
