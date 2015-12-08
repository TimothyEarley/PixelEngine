package testPackage;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.level.Tile;
import de.earley.pixelengine.sprite.Drawable;

/**
 * Created by timmy on 10/9/15.
 */
public class EndTile extends Tile {

	private SpeedTestState speedTestState;

	public EndTile(Drawable drawable, boolean solid, String name, SpeedTestState speed) {
		super(drawable, solid, name);
		this.speedTestState = speed;
	}

	@Override
	public boolean canMove(Entity e) {
		speedTestState.endTimer();
		return super.canMove(e);
	}
}
