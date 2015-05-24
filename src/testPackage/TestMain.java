package testPackage;

import java.awt.event.MouseEvent;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;

public class TestMain extends Game {

	public static void main(String[] args) {
		Window window = new Window("Test", 1600, 900);
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

	@Override
	public void render(Screen screen) {
		
	}
}
