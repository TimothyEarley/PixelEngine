package de.earley.pixelengine.entity.projectile;

import de.earley.pixelengine.entity.Entity;
import de.earley.pixelengine.entity.Mob;
import de.earley.pixelengine.vector.Vector2f;
import de.earley.pixelengine.window.input.Keyboard;
import de.earley.pixelengine.window.input.Mouse;

import java.util.ArrayList;

/**
 * @author timmy
 */
public abstract class Projectile extends Entity {

	protected long timeout;
	protected Vector2f dir;
	protected float speed;
	protected int damage;
	protected Entity sender;
	protected ArrayList<Class> senderClass;
	protected ArrayList<Entity> senderEntity;

	public Projectile(Vector2f pos, Vector2f dir, float speed, int damage, Entity sender) {
		this(pos, dir, speed, damage, Long.MAX_VALUE, sender);
	}

	public Projectile(Vector2f pos, Vector2f dir, float speed, int damage, long timeout, Entity sender) {
		this.dir = dir.normalize();
		this.speed = speed;
		this.position = pos;
		this.damage = damage;

		this.timeout = timeout;
		this.sender = sender;

		collidableTile = true;
		collidableEntity = true;
		collidableProjectile = false;

		senderEntity = new ArrayList<>();
		senderClass = new ArrayList<>();
	}

	@Override
	public void update(long delta, Keyboard keyboard, Mouse mouse) {
		timeout -= delta;
		if (!move(dir.copy().mult(speed * delta), false) || timeout < 0) {
			remove();
		}

	}

	/**
	 * @param e Might indicate affect on certain entities
	 * @return the amount of damage dealt to this entity
	 */
	public int getDamage(Entity e) {
		return damage;
	}

	@Override
	public void collide(Entity other) {

		if (senderClass.contains(other.getClass()) || senderEntity.contains(other)) {
			return;
		}

		if (other instanceof Mob) {
			Mob mob = (Mob) other;
			if (mob.getHealth() > 0) {
				mob.damage(damage);
				if (sender != null)
					sender.projectileHit(other);
			}
		}
		remove();
	}

	protected final void center() {
		position.sub(drawable.getWidth() / 2, drawable.getHeight() / 2);
	}

	public void addSender(Class clazz) {
		senderClass.add(clazz);
	}

	public void addSender(Entity e) {
		senderEntity.add(e);
	}
}
