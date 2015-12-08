package de.earley.pixelengine.window;

import com.apple.eawt.Application;
import com.apple.eawt.FullScreenUtilities;
import de.earley.pixelengine.game.states.Layer;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Input;
import de.earley.pixelengine.window.input.ResizedAction;
import de.earley.pixelengine.window.osx.AboutHelper;
import de.earley.pixelengine.window.render.Viewport;

import javax.swing.*;
import java.awt.*;

public class Window extends JPanel implements ResizedAction {

	/**
	 * Indicates, wether we are running OS X 10.3 or higher
	 */
	private static boolean newOSX;
	/**
	 * The dimensions of the window
	 */
	protected int width, height;
	/**
	 * Receives input from this window
	 */
	protected Input in;
	/**
	 * The actual window
	 */
	private JFrame frame;

	/**
	 * The layer to render
	 */
	private Layer root;

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
		frame.add(this);
	}

	private void setupFrame(String title) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (newOSX) {
			FullScreenUtilities.setWindowCanFullScreen(frame, true);
			//noinspection ConstantConditions
			Application.getApplication().setAboutHandler(new AboutHelper("Hello World!"));
		}
	}

	private void setupInput() {
		in = new Input();
		frame.addKeyListener(in.keyboard);
		frame.addMouseListener(in.mouse);
		frame.addMouseMotionListener(in.mouse);
		frame.addComponentListener(in.component);
		frame.addFocusListener(in.focus);

		in.component.addListener(this);
	}

	public void start() {
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) throws NullPointerException {
		super.paintComponent(g);
		root.render(g, stretch, xOffset, yOffset);
	}

	public void toggleFullscreen() {
		if (newOSX)
			//noinspection ConstantConditions,ConstantConditions
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


	}


	public Input getInput() {
		return in;
	}

	public void setRoot(Layer root) {
		this.root = root;
	}

	/**
	 * Transforms the coordinate to a system relative to the provided viewport
	 *
	 * @param v The relative viewport
	 * @return the transformed mouse position
	 */
	public Vector2f transformMouse(Viewport v) {
		Vector2f mouseTrans = transformMouse();
		mouseTrans.sub(v.getXPosition(), v.getYPosition());
		mouseTrans.x *= v.getWidth() / (float) v.getRenderWidth();
		mouseTrans.y *= v.getHeight() / (float) v.getRenderHeight();
		return mouseTrans;
	}

	/**
	 * Transforms the coordinate to a system relative to the rendered image (i.e. the intended dimensions, unscaled)
	 *
	 * @return the transformed mouse position
	 */
	private Vector2f transformMouse() {
		Vector2f mouse = in.mouse.getMouse();
		return new Vector2f((mouse.x / stretch) - xOffset, ((mouse.y - frame.getInsets().top) / stretch) - yOffset);
	}
}
