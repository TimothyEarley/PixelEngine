package de.earley.pixelengine.window;

import javax.swing.JFrame;

import de.earley.pixelengine.window.input.Input;

public class JFrameWindow extends Window {

	/**
	 * The actual window
	 */
	private JFrame frame;
	
	public JFrameWindow(String title, int width, int height) {
		this.width = width;
		this.height = height;
		
		setupFrame(title);
		setupInput();
	}
	
	private void setupFrame(String title) { 
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setupInput() {
		in = new Input();
		frame.addKeyListener(in.keyboard);
		frame.addMouseListener(in.mouse);
		frame.addMouseMotionListener(in.mouse);
		frame.addFocusListener(in.focus);
	}

	@Override
	public void start() {
		frame.setVisible(true);
		setupScreen();
	}

	private void setupScreen() {
	}
	
}
