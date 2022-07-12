package org.example.volunteers;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

	static boolean hasJustOneNull(String st1, String st2) {
		if (st1 == null && st2 == null) return false;
		return st1 == null || st2 == null;
	}


	static boolean bothStringsAreNull(String st1, String st2) {
		return st1 == null && st2 == null;
	}


	static String formatNames(String input) {
		if (input == null || input.length() == 0) return null;
		String upperCasedString = input.toUpperCase();
		return Normalizer.normalize(upperCasedString, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static String formatEmail(String input) {
		if (input == null || input.length() == 0) return null;

		boolean isValid = Pattern.
				compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
				.matcher(input).matches();
		if (!isValid) return null;
		return input;
	}

	public static String formatPhone(String input) {
		if (input == null || input.length() == 0) return null;

		String validString = input
				.replace(".", "")
				.replace("-", "")
				.replace(" ", "")
				.replace("(", "")
				.replace(")", "")
				.replace("_", "");


		if (validString.length() < 10) return null;

		if (!validString.startsWith("+")) {
			validString = "+33" + validString;
		}

		if (validString.length() > 13 || validString.length() < 12) return null;


		return validString;
	}

	static String getNotNullString(String st1, String st2) {
		if (st1 == null && st2 == null) return null;
		if (st1 != null) return st1;
		return st2;
	}

}
