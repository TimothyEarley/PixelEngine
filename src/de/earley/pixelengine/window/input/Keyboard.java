package de.earley.pixelengine.window.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.earley.pixelengine.util.Range;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] keysTyped = new boolean[65536];

	public void poll() {
		for (int i = 0; i < keysTyped.length; i++) {
			keysTyped[i] = false;
		}
	}
	
	public boolean isKeyDown(int code) {
		if (Range.isInRangeExclusive(code, -1, keys.length))
			return keys[code];
		else
			return false;
	}


	public boolean didKeyType(int code) {
		if (Range.isInRangeExclusive(code, -1, keysTyped.length))
			return keysTyped[code];
		else
			return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		keysTyped[e.getExtendedKeyCode()] = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
