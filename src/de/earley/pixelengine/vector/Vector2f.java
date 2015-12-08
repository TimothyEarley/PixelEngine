package de.earley.pixelengine.vector;

import de.earley.pixelengine.util.StringUtil;

import java.awt.*;
import java.io.Serializable;

@SuppressWarnings("SuspiciousNameCombination")
public class Vector2f implements Serializable {
	private static final long serialVersionUID = 6946630701349833727L;

	public static Vector2f horizontal = new Vector2f(1, 0), vertical = new Vector2f(0, 1);


	public float x, y;

	/**
	 * calls Vector2f(0, 0)
	 */
	public Vector2f() {
		this(0, 0);
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2i vec) {
		this(vec.x, vec.y);
	}

	/**
	 * @param angle - in radians
	 */
	public Vector2f(float angle) {
		this((float) Math.cos(angle), (float) Math.sin(angle));
	}

	public Vector2f add(Vector2i a) {
		this.x += a.x;
		this.y += a.y;
		return this;
	}

	public Vector2f add(float d) {
		this.x += d;
		this.y += d;
		return this;
	}

	public Vector2f add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2f sub(float d) {
		this.x -= d;
		this.y -= d;
		return this;
	}

	public Vector2f add(Vector2f a) {
		this.x += a.x;
		this.y += a.y;
		return this;
	}

	public Vector2f sub(int dx, int dy) {
		x -= dx;
		y -= dy;
		return this;
	}

	public Vector2f mult(double a) {
		x *= a;
		y *= a;
		return this;
	}

	public float distance(Vector2i a) {
		return (float) Math.sqrt(distanceSquare(a));
	}

	public float distanceSquare(Vector2i a) {
		Vector2f dif = this.copy().sub(a);
		return dif.lengthSquare();
	}

	public Vector2f sub(Vector2i a) {
		this.x -= a.x;
		this.y -= a.y;
		return this;
	}

	public Vector2f copy() {
		return new Vector2f(x, y);
	}

	public float lengthSquare() {
		return this.x * this.x + this.y * this.y;
	}

	public float distance(Vector2f a) {
		return (float) Math.sqrt(distanceSquare(a));
	}

	public float distanceSquare(Vector2f a) {
		Vector2f dif = this.copy().sub(a);
		return dif.lengthSquare();
	}

	public Vector2f sub(Vector2f a) {
		this.x -= a.x;
		this.y -= a.y;
		return this;
	}

	public Vector2f getOrthogonal() {
		return new Vector2f(-this.y, this.x);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vector2i) {
			Vector2i b = (Vector2i) obj;
			return (x == b.x && y == b.y);
		}
		if (obj instanceof Vector2f) {
			Vector2f b = (Vector2f) obj;
			return (x == b.x && y == b.y);
		}

		return false;
	}

	@Override
	public String toString() {
		return "[" + StringUtil.toDecimal(x, 4, true) + " | " + StringUtil.toDecimal(y, 4, true) + "]";
	}

	public Vector2i toVector2i() {
		return new Vector2i(x, y);
	}

	public float getSmallestAngleTo(Vector2f b) {
		return (float) Math.acos(this.copy().normalize().dot(b.copy().normalize()));
	}

	public float dot(Vector2f a) {
		return a.x * x + a.y * y;
	}

	public Vector2f normalize() {
		float length = length();
		this.x /= length;
		this.y /= length;
		return this;
	}

	public float length() {
		return (float) Math.sqrt(lengthSquare());
	}

	public float getAngleTo(Vector2f b) {
		return (float) (Math.atan2(this.y - b.y, this.x - b.x));
	}

	public float getAngleTo(Vector2i b) {
		return (float) (Math.atan2(this.y - b.y, this.x - b.x));
	}

	public float getAngle() {
		return (float) (Math.atan2(y, x));
	}

	public Point toPoint() {
		return new Point((int) x, (int) y);
	}

}
