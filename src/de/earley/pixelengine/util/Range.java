package de.earley.pixelengine.util;

public abstract class Range {

	public static boolean isInRangeExclusive(int num, int left, int right) {
		return num > left && num < right;
	}

	
}
