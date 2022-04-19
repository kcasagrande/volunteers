package org.example.volunteers;

public class Compare {
	public static Boolean compareMails(String email1, String email2) {
		if (email1 != null && email2 != null) {
			email1 = email1.substring(0,email1.lastIndexOf("."));
			email2 = email2.substring(0,email2.lastIndexOf("."));

			String domain1 = email1.substring(email1.lastIndexOf("@") + 1);
			String domain2 = email2.substring(email2.lastIndexOf("@") + 1);

			if (compareStrings(domain1, domain2)) {
				return compareStrings(
					email1.substring(0,email1.lastIndexOf("@")),
					email2.substring(0,email2.lastIndexOf("@"))
				);
			} else {
				return false;
			}

		} else {
			return true;
		}
	};

	public static Boolean comparePhones(String phone1, String phone2) {
		return true;
	}

	public static Boolean compareStrings(String string1, String string2) {
		return (string1.equals(string2));
	}
}
