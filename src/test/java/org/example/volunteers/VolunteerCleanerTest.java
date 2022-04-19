package org.example.volunteers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class VolunteerCleanerTest {
	private Volunteer volunteer;

	protected VolunteerCleaner testedInstance;

	@BeforeEach
	public void setUp(Volunteer volunteer) {
		this.testedInstance = new VolunteerCleaner(
				this.volunteer = volunteer
		);
	}

	@Test
	void testCountUsers() {
		List<String> usersList = Arrays.asList(
				"LOZÉ;LESLY;Fledgling;Fledgling4390@example.org;+33045550388",
				"Sayin;Kizil;Octopirate;kizilsayin@example.com;+33000555196"
		);

		int result = testedInstance.countUsers(usersList);

		Assertions.assertEquals(2, result);
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

		Assertions.assertEquals(volunteer1.isSame(volunteer2), true);
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

		Assertions.assertEquals(volunteer1.isSame(volunteer2), false);
	}
}