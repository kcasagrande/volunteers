package org.example.volunteers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerTest {

	@ParameterizedTest
	@MethodSource("isSamePersonTrueInputs")
	public void testIsSamePersonTrue(Volunteer vol2, String message) {
		Volunteer vol1 = new Volunteer("test", "test2", null, "elisabeth_rigal@example.com", null);
		assertTrue(vol1.isSamePerson(vol2), message);
	}

	@ParameterizedTest
	@MethodSource("isSamePersonFalseInputs")
	public void testIsSamePersonFalse(Volunteer vol2, String message) {
		Volunteer vol1 = new Volunteer("test", "test2", null, "elisabeth_rigal@example.com", null);
		assertFalse(vol1.isSamePerson(vol2), message);
	}

	public static Stream<Arguments> isSamePersonTrueInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, "elisabeth_rigal@example.com", null),
						"Should be the same person as the email, last name and firstname are the sames"),
				Arguments.of(new Volunteer("test2", "test", null, "elisabeth_rigal@example.com", null),
						"Should be the same person as the email, last name and firstname are inversed"),
				Arguments.of(new Volunteer(null, "test2", null, "elisabeth_rigal@example.com", null),
						"Should be the same person as the email and lastname are the same and firstname is null")
		);
	}

	public static Stream<Arguments> isSamePersonFalseInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", null, null, "elisabeth_rigal@example.com", null),
						"Should not be the same as the lastname is null"),
				Arguments.of(new Volunteer("test2", "testazeaze", null, "elisabeth_rigal@example.com", null),
						"Should not be the same as the lastname is different"),
				Arguments.of(new Volunteer("testazeaze", "test2", null, "elisabeth_rigal@example.com", null),
						"Should not be the same as the firstname is different")
		);
	}

	@ParameterizedTest
	@MethodSource("mergeVolunteerIdentitiesInputs")
	public void testMergeVolunteerIdentities(Volunteer vol2, Volunteer expected, String message) {
		Volunteer vol1 = new Volunteer("test4", null, null, "lisabeth_rigal@example.com", null);
		vol1.mergeVolunteerIdentities(vol2);

		assertAll(
				message,
				() -> assertEquals(vol1.getFirstName(), expected.getFirstName()),
				() -> assertEquals(vol1.getLastName(), expected.getLastName()),
				() -> assertEquals(vol1.getNickName(), expected.getNickName()),
				() -> {
					vol2.getEmails().forEach(email -> {
						assertTrue(vol1.getEmails().contains(email));
					});
				},
				() -> {
					vol2.getPhones().forEach(phone -> {
						assertTrue(vol1.getPhones().contains(phone));
					});
				}
		);
	}

	public static Stream<Arguments> mergeVolunteerIdentitiesInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, "lisabeth_rigal@example.com", null),
						new Volunteer("test4", "test2", null, "lisabeth_rigal@example.com", null),
						"Vol1 should get the lastname test2"),
				Arguments.of(new Volunteer("test2", "test2", "hello", "lisabeth@example.com", null),
						new Volunteer("test4", "test2", "hello", "lisabeth@example.com", null),
						"Vol 1 should get the lastname of vol2 and the nickname of vol2 and the email is added to his list"),
				Arguments.of(new Volunteer(null, "test2", null, null, null),
						new Volunteer("test4", "test2", null, null, null),
						"Vol1 should take the firstname of vol2"),
				Arguments.of(new Volunteer("test8", "test2", "coucou", null, "0612345678"),
						new Volunteer("test4", "test2", "coucou", null, null),
						"Vol1 should get the nickname of vol2 and his phone number to to list")
		);
	}

}
