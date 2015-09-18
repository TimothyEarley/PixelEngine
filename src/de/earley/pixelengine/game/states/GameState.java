package de.earley.pixelengine.game.states;

import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.Viewport;

import java.util.ArrayList;

/**
 * Created by timmy on 9/18/15.
 */
public abstract class GameState {

	private final String name;

	protected ArrayList<Viewport> viewports = new ArrayList<>();

	protected boolean initialised;

	public GameState(String name) {
		this.name = name;
	}

	public abstract void update(long delta, Window window);

	public void init() {
		initialised = true;
	}

	public ArrayList<Viewport> getViewports() {
		return viewports;
	}

	public void onEnter() {
	}

	public String getName() {
		return name;
	}

	public boolean isInitialised() {
		return initialised;
	}
}
