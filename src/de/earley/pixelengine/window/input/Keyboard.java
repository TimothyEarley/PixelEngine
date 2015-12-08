package de.earley.pixelengine.window.input;

import de.earley.pixelengine.util.Range;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] keysTyped = new boolean[65536];

	public void poll() {
		for (int i = 0; i < keysTyped.length; i++) {
			keysTyped[i] = false;
		}
	}

	public boolean isKeyDown(int code) {
		return Range.isInRangeExclusive(code, -1, keys.length) && keys[code];
	}


	public boolean didKeyType(int code) {
		return Range.isInRangeExclusive(code, -1, keysTyped.length) && keysTyped[code];
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// e.getKeyCode() always returns 0. :|
		int code = KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar());
		keysTyped[code] = true;
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
