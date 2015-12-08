package de.earley.pixelengine.window.render;

import java.awt.*;

/**
 * Created by timmy on 10/14/15.
 */
public class BlankViewport extends Viewport {


	public BlankViewport(int width, int height) {
		super(width, height, 0, 0, (viewport) -> {
			// blank
		});
	}

	@Override
	protected void renderToScreen(Graphics g, float stretch, int xOffset, int yOffset) {
		// Blank
	}
}
