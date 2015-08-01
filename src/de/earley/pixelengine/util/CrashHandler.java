package de.earley.pixelengine.util;

public class CrashHandler {

	public static void crash(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
	
}
