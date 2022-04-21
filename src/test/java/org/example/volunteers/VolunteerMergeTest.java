package org.example.volunteers;

import org.example.volunteers.entity.Volunteer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolunteerMergeTest {
	@Test
	void shouldMergeTwoUsers() {
		Volunteer volunteer1 = new Volunteer(
				"",
				"Denis",
				"Hugden",
				"",
				"+330645342321"
		);

		Volunteer volunteer2 = new Volunteer(
				"Hugo",
				"",
				"",
				"HUGO@denis.fr",
				"+330645342321"
		);

		Assertions.assertEquals(VolunteerMerge.merge(volunteer1, volunteer2), new Volunteer(
				"Hugo",
				"Denis",
				"Hugden",
				"HUGO@denis.fr",
				"+330645342321"
		));
	}
}