package de.earley.pixelengine.vector;

public class Vector2i {

	public int x, y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i() {
	    this(0,0);
	}
	
	public Vector2i(float x, float y) {
		this((int) x, (int) y);
	}

	@Override
	public String toString() {
		return "[Vector2i: x=" + x + ", y=" + y + "]";
	}

	public Vector2i copy() {
		return new Vector2i(x, y);
	}

	public Vector2i add(Vector2i a) {
		this.x += a.x;
		this.y += a.y;
		return this;
	}
	
	public Vector2i add(int d) {
		this.x += d;
		this.y += d;
		return this;
	}
	
	public Vector2i add(int x, int y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2i sub(Vector2i a) {
		this.x -= a.x;
		this.y -= a.y;
		return this;
	}
	
	public Vector2i subX(int dx) {
		this.x -= dx;
		return this;
	}
	
	public Vector2i subY(int dy) {
		this.y -= dy;
		return this;
	}
	
	public Vector2i sub(int d) {
		this.x -= d;
		this.y -= d;
		return this;
	}
	
	public Vector2i sub(int dx, int dy) {
		subX(dx);
		subY(dy);
		return this;
	}
	
	public Vector2i mult(double a) {
		x *= a;
		y *= a;
		return this;
	}
	
	public Vector2f normalize() {
		float length = length();
		return new Vector2f(x / (float) length, y / (float) length);
	}
	
	public float length() {
		return (float) Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public float distance(Vector2i a) {
		Vector2i dif = this.copy().sub(a);
		return dif.length();
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
	
	public float dot(Vector2i a) {
		return a.x * x + a.y * y;
	}
	
	public float getSmallestAngleTo(Vector2i b) {
		return (float) Math.acos(this.copy().normalize().dot(b.normalize()));
	}
	
	public float getAngleTo(Vector2i b) {
		return (float) (Math.atan2(this.x, this.y) - Math.atan2(b.x, b.y));
	}
	
}
