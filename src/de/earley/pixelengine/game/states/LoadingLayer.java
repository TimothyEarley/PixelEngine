package de.earley.pixelengine.game.states;

import de.earley.pixelengine.sprite.Sprite;
import de.earley.pixelengine.sprite.SpriteManager;
import de.earley.pixelengine.window.Window;
import de.earley.pixelengine.window.render.GraphicsHelper;

import java.awt.*;

/**
 * Created by timmy on 10/4/15.
 */
public abstract class LoadingLayer extends SingleExitLayer {

	protected String path;
	protected int finished, total;

	public LoadingLayer(Layer exitLayer) {
		super(exitLayer);
	}

	@Override
	public void init() {
		viewport = new GraphicsHelper(parent.getWidth(), parent.getHeight(), 0, 0, (gh) -> {
			Graphics g = ((GraphicsHelper) gh).getGraphics();
			renderProgress(g);
		});
	}

	protected abstract void renderProgress(Graphics g);

	@Override
	public boolean update(long delta, Window window) {
		if (!SpriteManager.isEmpty()) {
			Sprite loaded = SpriteManager.loadOne();
			setFinished(SpriteManager.doneLoading());
			setTotal(SpriteManager.total());
			setPath(loaded.getPath());
		} else {
			exit();
		}
		return false;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
