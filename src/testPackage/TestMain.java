package testPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;

import com.sun.glass.events.KeyEvent;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.sprite.Sprite;
import de.earley.pixelengine.window.JFrameWindow;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Screen;

public class TestMain extends Game {

	Sprite s;

	public static void main(String[] args) {
		JFrameWindow window = new JFrameWindow("Test", 1600, 900);
		new TestMain(window).start();
	}

	public TestMain(Window window) {
		super(window);
	}

	@Override
	protected void init() {
		s = new Sprite("/test.png");
	}

	@Override
	protected void update(int delta, Window window) {
		if (window.getInput().mouse.didButtonClick(MouseEvent.BUTTON3))
			((JFrameWindow) window).toggleFullscreen();
		if (window.getInput().keyboard.didKeyType(KeyEvent.VK_SPACE))
			try {
				com.apple.eio.FileManager.revealInFinder(new File("/Library/Application Support/"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
		gameSettings.setUPS(1);
	}

	@Override
	public void render(Screen screen) {
		int size = 50;
		for (int x = 0; x < screen.getWidth(); x += size) {
			for (int y = 0; y < screen.getHeight(); y += size) {
				if (Math.random() > 0.5) {
					screen.setColour(Color.GREEN);
				} else {
					screen.setColour(Color.BLUE);
				}
				screen.fillRect(x, y, size, size);
			}
		}
	}

}
