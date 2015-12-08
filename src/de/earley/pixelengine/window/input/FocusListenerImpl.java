package de.earley.pixelengine.window.input;

import java.awt.event.FocusEvent;

/**
 * Created by timmy on 25/10/15.
 */
public class FocusListenerImpl implements java.awt.event.FocusListener {

	private boolean focused = true;


	@Override
	public void focusGained(FocusEvent e) {
		focused = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		focused = false;
	}

	public boolean getFocused() {
		return focused;
	}
}
