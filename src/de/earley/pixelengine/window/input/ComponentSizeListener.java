package de.earley.pixelengine.window.input;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class ComponentSizeListener implements ComponentListener {

	private ArrayList<ResizedAction> listeners = new ArrayList<>();

	@Override
	public void componentResized(ComponentEvent e) {
		listeners.forEach(de.earley.pixelengine.window.input.ResizedAction::onResize);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	public void addListener(ResizedAction a) {
		listeners.add(a);
	}

}
