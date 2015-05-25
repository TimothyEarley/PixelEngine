package testPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;

public class TestMain extends Game {

	private static Screen screen;
	
	public static void main(String[] args) {
		Window window = new Window("Test", 1600, 900);
		screen = new Screen(800, 450, 1600, 900, 0, 0, () -> {render(screen);});
		window.addViewport(screen);
		new TestMain(window).start();
	}

	public TestMain(Window window) {
		super(window);
	}

	@Override
	protected void init() {
	}

	@Override
	protected void update(int delta, Window window) {
		if (window.getInput().mouse.didButtonClick(MouseEvent.BUTTON3))
			((Window) window).toggleFullscreen();
	}

	private static void render(Screen screen) {
		screen.setColour(Color.red);
		screen.fillRect(10, 10, 50, 50);
	}
}
