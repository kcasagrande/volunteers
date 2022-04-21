package org.example.volunteers;

import org.example.volunteers.entity.Volunteer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VolunteerCompareTest {
	@Test
	void compareStrictlySameEmails() {
		String email1 = "Hugo@denis.fr";
		String email2 = "Hugo@denis.fr";

		Assertions.assertEquals(VolunteerCompare.compareMails(email1, email2), true);
	}

	@Test
	void compareStrictlyDifferentEmails() {
		String email1 = "Hugo@denis.fr";
		String email2 = "yhomas@Fiak.com";

		Assertions.assertEquals(VolunteerCompare.compareMails(email1, email2), false);
	}

	@Test
	void compareStrictlySamePhoneNumber() {
		String phoneNumber1 = "+33655443322";
		String phoneNumber2 = "+33655443322";

		Assertions.assertEquals(VolunteerCompare.comparePhones(phoneNumber1, phoneNumber2), true);
	}

	@Test
	void compareSameComplexesPhoneNumbers() {
		String phoneNumber1 = "+33(0)6_55 44_3322";
		String phoneNumber2 = "06 55 44 33 22";

		Assertions.assertEquals(VolunteerCompare.comparePhones(phoneNumber1, phoneNumber2), true);
	}

	@Test
	void compareDifferentComplexesPhoneNumbers() {
		String phoneNumber1 = "+33(0)6_55 44_3322";
		String phoneNumber2 = "+33(0)699_44_332 2";

		Assertions.assertEquals(VolunteerCompare.comparePhones(phoneNumber1, phoneNumber2), false);
	}

	@Test
	void compareStrictlySameStrings() {
		String string1 = "a string";
		String string2 = "a string";

		Assertions.assertEquals(VolunteerCompare.compareStrings(string1, string2), true);
	}

	@Test
	void compareSlightlySameStrings() {
		String string1 = "astring";
		String string2 = "aSTRINg";

		Assertions.assertEquals(VolunteerCompare.compareStrings(string1, string2), true);
	}

	@Test
	void compareDifferentStrings() {
		String string1 = "a string";
		String string2 = "another string";

		Assertions.assertEquals(VolunteerCompare.compareStrings(string1, string2), false);
	}

	@Test
	void compareStrictlySameVolunteers() {
		Volunteer volunteer1 = new Volunteer(
				"Hugo",
				"Denis",
				"zgeg22",
				"test@test.fr",
				"+330645342321"
		);

		Volunteer volunteer2 = new Volunteer(
				"Hugo",
				"Denis",
				"zgeg22",
				"test@test.fr",
				"+330645342321"
		);

		Assertions.assertEquals(VolunteerCompare.isSame(volunteer1, volunteer2), true);
	}

	@Test
	void compareStrictlyDifferentVolunteers() {
		Volunteer volunteer1 = new Volunteer(
				"Pascal",
				"Fiak",
				"Pfiak34",
				"nom@prenom.fr",
				"+330654328783"
		);

		Volunteer volunteer2 = new Volunteer(
				"Hugo",
				"Denis",
				"zgeg22",
				"test@test.fr",
				"+330645342321"
		);

		Assertions.assertEquals(VolunteerCompare.isSame(volunteer1, volunteer2), false);
	}
}
