package org.example.volunteers;

import org.example.volunteers.entity.Volunteer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}