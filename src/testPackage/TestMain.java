package testPackage;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.window.JFrameWindow;
import de.earley.pixelengine.window.Screen;
import de.earley.pixelengine.window.Window;

public class TestMain extends Game {

	public static void main(String[] args) {
		JFrameWindow window = new JFrameWindow("Test", 900, 600);
		new TestMain(window).start();
	}
	
	public TestMain(Window window) {
		super(window);
	}

	@Override
	protected void update(int delta, Window window) {
	}

	@Override
	protected void render(Screen screen) {
	}

}
