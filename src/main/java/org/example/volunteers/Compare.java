package org.example.volunteers;

public class Compare {
	public static Boolean compareMails(String email1, String email2) {
		if (!email1.equals("") && !email2.equals("")) {
			email1 = email1.substring(0,email1.lastIndexOf("."));
			email2 = email2.substring(0,email2.lastIndexOf("."));

			String domain1 = email1.substring(email1.lastIndexOf("@"));
			String domain2 = email2.substring(email2.lastIndexOf("@"));

			if (compareStrings(domain1, domain2)) {
				return compareStrings(
					email1.substring(0,email1.lastIndexOf("@")),
					email2.substring(0,email2.lastIndexOf("@"))
				);
			} else {
				return false;
			}

		} else {
			return false;
		}
	};

	public static Boolean comparePhones(String phone1, String phone2) {
		if (phone1 != null && phone2 != null ) {
			phone1 = formatPhone(phone1);
			phone2 = formatPhone(phone2);

			return (phone1.equals(phone2));
		} else {
			return false;
		}
	}

	public static Boolean compareStrings(String string1, String string2) {
		if (string1 != null && string2 != null) {
			return (string1.toLowerCase().equals(string2.toLowerCase()));
		} else {
			return false;
		}
	}

	private static String formatPhone(String number) {
		return number
			.replace("_", "")
			.replace(" ", "")
			.replace("(0)", "")
			.replace("-", "")
			.replace("+33", "0");

	}
}
