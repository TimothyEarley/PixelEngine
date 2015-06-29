package testPackage;

import java.awt.Color;
import java.awt.event.MouseEvent;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.GraphicsHelper;
import de.earley.pixelengine.window.render.Screen;

public class TestMain extends Game {

	private static Screen screen;
	private static GraphicsHelper gh1, gh2, gh3, gh4, gh5, gh6, gh7, gh8;
	
	
	public static void main(String[] args) {
		Window window = new Window("Test", 1600, 900);
//		screen = new Screen(400, 450, 800, 900, 0, 0, () -> {render(screen);});
		
		gh1 = new GraphicsHelper(400, 450, 0, 		0, 		() -> {renderGUI(gh1, Color.PINK);});
		gh2 = new GraphicsHelper(400, 450, 400, 	0, 		() -> {renderGUI(gh2, Color.CYAN);});
		gh3 = new GraphicsHelper(400, 450, 0,		450, 	() -> {renderGUI(gh3, Color.CYAN);});
		gh4 = new GraphicsHelper(400, 450, 400, 	450, 	() -> {renderGUI(gh4, Color.PINK);});
		gh5 = new GraphicsHelper(400, 450, 800, 	0, 		() -> {renderGUI(gh5, Color.PINK);});
		gh6 = new GraphicsHelper(400, 450, 1200, 	0, 		() -> {renderGUI(gh6, Color.CYAN);});
		gh7 = new GraphicsHelper(400, 450, 800, 	450, 	() -> {renderGUI(gh7, Color.CYAN);});
		gh8 = new GraphicsHelper(400, 450, 1200, 	450, 	() -> {renderGUI(gh8, Color.PINK);});
//		window.addViewport(screen);
		window.addViewport(gh1);
		window.addViewport(gh2);
		window.addViewport(gh3);
		window.addViewport(gh4);
		window.addViewport(gh5);
		window.addViewport(gh6);
		window.addViewport(gh7);
		window.addViewport(gh8);
		new TestMain(window).start();
	}

	private static void renderGUI(GraphicsHelper gh, Color col) {
		gh.getGraphics().setBackground(col);
		gh.getGraphics().setColor(Color.WHITE);
		gh.getGraphics().drawString("Hello World", 10, 10);
		gh.getGraphics().setColor(Color.BLACK);
                gh.getGraphics().drawLine(0, 0, gh.getRenderWidth(), gh.getRenderHeight());
	}
	
	public TestMain(Window window) {
		super(window);
		gameSettings.limitFPS = false;
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
