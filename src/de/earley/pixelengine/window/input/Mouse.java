package de.earley.pixelengine.window.input;

import de.earley.pixelengine.util.Range;
import de.earley.pixelengine.vector.Vector2f;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	private Vector2f mouse = new Vector2f();
	private boolean[] button = new boolean[MouseInfo.getNumberOfButtons()];
	private boolean[] buttonClick = new boolean[MouseInfo.getNumberOfButtons()];

	public void poll() {
		for (int i = 0; i < buttonClick.length; i++) {
			buttonClick[i] = false;
		}
	}

	public Vector2f getMouse() {
		return mouse.copy();
	}

	public boolean didButtonClick(int buttonNumber) {
		return Range.isInRangeExclusive(buttonNumber, -1, buttonClick.length) && buttonClick[buttonNumber];
	}

	public boolean isButtonDown(int buttonNumber) {
		return Range.isInRangeExclusive(buttonNumber, -1, button.length) && button[buttonNumber];
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		buttonClick[e.getButton()] = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		button[e.getButton()] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button[e.getButton()] = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
