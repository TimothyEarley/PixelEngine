package de.earley.pixelengine.game;

import de.earley.pixelengine.game.states.GameState;
import de.earley.pixelengine.game.util.GameSettings;
import de.earley.pixelengine.window.Window;

import java.util.HashMap;

/**
 * Created by timmy on 9/18/15.
 */
public class StateBasedGame extends Game {

	HashMap<String, GameState> gameStates = new HashMap<>();
	GameState active;

	public StateBasedGame(Window window, GameSettings gameSettings) {
		super(window, gameSettings);
	}

	public StateBasedGame(Window window) {
		super(window);
	}

	public void addGameState(GameState gameState) {
		gameStates.put(gameState.getName(), gameState);
	}

	@Override
	public void update(long delta, Window window) {
		active.update(delta, window);
	}

	public void enter(String name) {
		active = gameStates.getOrDefault(name, this.active);
		window.setViewports(active.getViewports());
		if (!active.isInitialised()) {
			active.init();
		}
		active.onEnter();
	}
}
