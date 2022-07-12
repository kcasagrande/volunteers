package org.example.volunteers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerTest {

	@ParameterizedTest
	@MethodSource("isSamePersonTrueInputs")
	public void testIsSamePersonTrue(Volunteer vol2) {
		Volunteer vol1 = new Volunteer("test","test2",null, "elisabeth_rigal@example.com", null);
		assertTrue(vol1.isSamePerson(vol2));
	}

	@ParameterizedTest
	@MethodSource("isSamePersonFalseInputs")
	public void testIsSamePersonFalse(Volunteer vol2) {
		Volunteer vol1 = new Volunteer("test","test2",null, "elisabeth_rigal@example.com", null);
		assertFalse(vol1.isSamePerson(vol2));
	}

	public static Stream<Arguments> isSamePersonTrueInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test","test2",null, "elisabeth_rigal@example.com", null)),
				Arguments.of(new Volunteer("test2","test",null, "elisabeth_rigal@example.com", null)),
				Arguments.of(new Volunteer(null,"test2",null, "elisabeth_rigal@example.com", null))
				);
	}

	public static Stream<Arguments> isSamePersonFalseInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test",null,null, "elisabeth_rigal@example.com", null)),
				Arguments.of(new Volunteer("test2","testazeaze",null, "elisabeth_rigal@example.com", null)),
				Arguments.of(new Volunteer("testazeaze","test2",null, "elisabeth_rigal@example.com", null))
		);
	}

	@ParameterizedTest
	@MethodSource("mergeVolunteerIdentitiesInputs")
	public void testMergeVolunteerIdentities(Volunteer vol2,Volunteer expected) {
		Volunteer vol1 = new Volunteer("test4",null,null, "lisabeth_rigal@example.com", null);
		vol1.mergeVolunteerIdentities(vol2);
		assertEquals(vol1.getFirstName(),expected.getFirstName());
		assertEquals(vol1.getLastName(),expected.getLastName());
		assertEquals(vol1.getNickName(),expected.getNickName());
		vol2.getEmails().forEach(email -> {
			assert(vol1.getEmails().contains(email));
		});
		vol2.getPhones().forEach(phone -> {
			assert(vol1.getPhones().contains(phone));
		});
	}

	public static Stream<Arguments> mergeVolunteerIdentitiesInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test","test2",null, "lisabeth_rigal@example.com", null),new Volunteer("test4","test2",null, "lisabeth_rigal@example.com", null)),
				Arguments.of(new Volunteer("test2","test2","hello", "lisabeth@example.com", null),new Volunteer("test4","test2","hello", "lisabeth@example.com", null)),
				Arguments.of(new Volunteer(null,"test2",null, null, null),new Volunteer("test4","test2",null, null, null)),
				Arguments.of(new Volunteer("test8","test2","coucou", null, "0612345678"),new Volunteer("test4","test2","coucou", null, null))
		);
	}

}
