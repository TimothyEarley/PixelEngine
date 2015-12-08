package de.earley.pixelengine.game.states;

/**
 * Created by timmy on 10/14/15.
 */
public abstract class SingleExitLayer extends Layer {

	protected Layer exitLayer;

	public SingleExitLayer(Layer exitLayer) {
		this.exitLayer = exitLayer;
	}

	public final void exit() {
		close();
		exitLayer.open();
	}


}
