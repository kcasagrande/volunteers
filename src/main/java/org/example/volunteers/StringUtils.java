package org.example.volunteers;

public class StringUtils {

	static boolean hasJustOneNull(String st1, String st2) {
		if (st1 == null && st2 == null) return false;
		if (st1 == null || st2 == null) return true;
		return false;
	}


	static  boolean bothStringsAreNull(String st1, String st2) {
		return  st1 == null && st2 == null;
	}

}
