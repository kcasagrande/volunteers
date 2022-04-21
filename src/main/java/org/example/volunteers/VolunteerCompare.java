package org.example.volunteers;

import org.example.volunteers.entity.Volunteer;

public class VolunteerCompare {
	public static Boolean compareMails(String email1, String email2) {
		if (!email1.equals("") && !email2.equals("")) {
			email1 = email1.substring(0, email1.lastIndexOf("."));
			email2 = email2.substring(0, email2.lastIndexOf("."));

			String domain1 = email1.substring(email1.lastIndexOf("@"));
			String domain2 = email2.substring(email2.lastIndexOf("@"));

			if (compareStrings(domain1, domain2)) {
				return compareStrings(
						email1.substring(0, email1.lastIndexOf("@")),
						email2.substring(0, email2.lastIndexOf("@"))
				);
			} else {
				return false;
			}
		}

		return false;
	}

	public static Boolean comparePhones(String phone1, String phone2) {
		if (phone1 != null && phone2 != null) {
			phone1 = formatPhone(phone1);
			phone2 = formatPhone(phone2);

			return (phone1.equals(phone2));
		}

		return false;
	}

	public static Boolean compareStrings(String string1, String string2) {
		if (string1 != null && string2 != null) {
			return (string1.toLowerCase().equals(string2.toLowerCase()));
		}

		return false;
	}

	public static Boolean isSame(Volunteer volunteer, Volunteer volunteer2) {
		boolean infosAreTheSame = VolunteerCompare.compareStrings(volunteer.firstName, volunteer2.firstName) &&
				VolunteerCompare.compareStrings(volunteer.lastName, volunteer2.lastName) &&
				VolunteerCompare.compareStrings(volunteer.nickName, volunteer2.nickName);

		if (infosAreTheSame) {
			return true;
		}

		return VolunteerCompare.compareMails(volunteer.eMail, volunteer2.eMail) || VolunteerCompare.comparePhones(volunteer.phone, volunteer2.phone);
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
