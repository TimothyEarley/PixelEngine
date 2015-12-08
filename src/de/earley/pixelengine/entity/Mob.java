package de.earley.pixelengine.entity;

/**
 * @author timmy
 */
public abstract class Mob extends Entity {

	protected int health;
	protected int damage;
	protected float speed;

	public void damage(int damage) {
		health -= damage;
	}

	public int getHealth() {
		return health;
	}

}
