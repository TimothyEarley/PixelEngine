package de.earley.pixelengine.sprite;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by timmy on 10/4/15.
 */
public final class SpriteManager {

	/**
	 * If true, does not load Sprites till instructed
	 */
	public static boolean defer;

	private static AtomicInteger doneLoading = new AtomicInteger(), total = new AtomicInteger();

	private static Queue<Sprite> queue = new ArrayDeque<>();

	public static void add(Sprite s) {
		if (defer) {
			queue.add(s);
			total.incrementAndGet();
		} else
			s.load();
	}

	public static void clear() {
		queue.clear();
	}

	public static void loadAll() {
		queue.forEach((s) -> {
			s.load();
			doneLoading.incrementAndGet();
		});
	}

	public static void loadAllParallel() {
		queue.parallelStream().forEach((s) -> {
			s.load();
			doneLoading.incrementAndGet();
		});
	}

	public static boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * @return the loaded Sprite or none
	 */
	public static Sprite loadOne() {
		Sprite s = queue.poll();
		s.load();
		doneLoading.incrementAndGet();
		return s;
	}

	public static int total() {
		return total.get();
	}

	public static int doneLoading() {
		return doneLoading.get();
	}
}
