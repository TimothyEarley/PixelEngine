package de.earley.pixelengine.util;

public abstract class Range {

    public static boolean isInRangeExclusive(int num, int left, int right) {
	    return !(num <= left || num >= right);
    }

    public static boolean isInRangeInclusive(int num, int left, int right) {
	    return !(num < left || num > right);
    }

    public static boolean intersects(float aLeft, float aTop, float aWidth, float aHeight, float bLeft, float bTop, float bWidth, float bHeight) {
	float aRight = aLeft + aWidth;
	float bRight = bLeft + bWidth;
	float aBottom = aTop + aHeight;
	float bBottom = bTop + bHeight;
	return !(bLeft > aRight || bRight < aLeft || bTop > aBottom || bBottom < aTop);
    }

	
}
