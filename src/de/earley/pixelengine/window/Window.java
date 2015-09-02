package de.earley.pixelengine.window;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.apple.eawt.Application;
import com.apple.eawt.FullScreenUtilities;

import de.earley.pixelengine.game.Game;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Input;
import de.earley.pixelengine.window.input.ResizedAction;
import de.earley.pixelengine.window.osx.AboutHelper;
import de.earley.pixelengine.window.render.Viewport;

public class Window implements ResizedAction {

	/**
	 * The dimensions of the window
	 */
	protected int width, height;
	
	/**
	 * Receives input from this window
	 */
	protected Input in;
	
	/**
	 * Indicates, wether we are running OS X 10.3 or higher
	 */
	private static boolean newOSX;

	/**
	 * The actual window
	 */
	private JFrame frame;
	
	/**
	 * List of all viewports to render to
	 */
	protected ArrayList<Viewport> viewports = new ArrayList<>(); 

	
	/**
	 * The amount to stretch the image by
	 */
	private float stretch;

	private int xOffset, yOffset;


	public Window(String title, int width, int height) {
		this.width = width;
		this.height = height;

		String[] version = System.getProperty("os.version").split("\\.");
		newOSX = System.getProperty("os.name").equals("Mac OS X") && version.length >= 2 && Integer.parseInt(version[0]) >= 10 && Integer.parseInt(version[1]) >= 3;

		setupFrame(title);
		setupInput();
	}

	private void setupFrame(String title) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (newOSX) {
			FullScreenUtilities.setWindowCanFullScreen(frame, true);
			Application.getApplication().setAboutHandler(new AboutHelper("Hello World!"));
		}
	}

	private void setupInput() {
		in = new Input();
		frame.addKeyListener(in.keyboard);
		frame.addMouseListener(in.mouse);
		frame.addMouseMotionListener(in.mouse);
		frame.addFocusListener(in.focus);
		frame.addComponentListener(in.component);
		in.component.addListener(this);
	}

	public void start() {
		frame.setVisible(true);
		frame.createBufferStrategy(2);
	}

	public void render(Game game) {
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null) {
			frame.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// START OF RENDER
		for (Viewport viewport : viewports) {
			viewport.render(g, stretch, xOffset, yOffset);
		}
		// END OF RENDER
		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void toggleFullscreen() {
		if (newOSX)
			Application.getApplication().requestToggleFullScreen(frame);
	}

	public void changeTitle(String string) {
		frame.setTitle(string);
	}

	@Override
	public void onResize() {
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();

		float maxStretchX = windowWidth / (float) width;
		float maxStretchY = windowHeight / (float) height;

		stretch = Math.min(maxStretchX, maxStretchY);
		
		xOffset = (int) ((windowWidth - width * stretch) / 2);
		yOffset = (int) ((windowHeight - height * stretch) / 2);
                yOffset += frame.getInsets().top;   

                //TODO fix offset (recenter)
	}
	
	
	public Input getInput() {
		return in;
	}

	public void addViewport(Viewport viewport) {
		viewports.add(viewport);
	}

	/**
	 * see {@link #transformMouse}
	 * @return 
	 */
	public Vector2f transformMouse() {
	    return transformMouse(getInput().mouse.getMouse());
	}

	
	/**
	 * Transforms the coordinate to a system relative to the rendered image 
	 * @param mouse
	 * @return 
	 */
	public Vector2f transformMouse(Vector2f mouse) {
	    return new Vector2f((mouse.x / stretch) - xOffset, (mouse.y / stretch) - yOffset);
	}
}
