package de.earley.pixelengine.util;

public class CrashHandler {

	//TODO logger

	public static void crash(Exception e, String message) {
		System.err.println(message);
		crash(e);
	}

	public static void crash(Exception e) {
		e.printStackTrace();
		System.exit(1);
	}

}
