package org.example.volunteers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class VolunteerTest {

	@ParameterizedTest
	@MethodSource("provideNamesInputs")
	public void testFormatName(String name, String expected) {
		if (expected == null) {
			assertNull(Volunteer.formatNames(name));
			return;
		}
		assert (Volunteer.formatNames(name).equals(expected));
	}

	public static Stream<Arguments> provideNamesInputs() {
		return Stream.of(
			Arguments.of("", null),
			Arguments.of("test", "TEST"),
			Arguments.of("TeSt", "TEST"),
			Arguments.of("tést", "TEST"),
			Arguments.of("Clément", "CLEMENT"),
			Arguments.of("azerty123", "AZERTY123"),
			Arguments.of("Édouard", "EDOUARD"),
			Arguments.of("Jéan-Louis", "JEAN-LOUIS")
		);
	}


	@ParameterizedTest
	@MethodSource("provideInvalidEmailInputs")
	public void testFormatEmailNull(String email) {
		assertNull(Volunteer.formatEmail(email));
	}

	@ParameterizedTest
	@MethodSource("provideValidEmailInputs")
	public void testFormatEmailNotNull(String email) {
		assertNotNull(Volunteer.formatEmail(email));
	}

	public static Stream<Arguments> provideInvalidEmailInputs() {
		return Stream.of(
			Arguments.of("elisabeth_rigalexample.com"),
			Arguments.of("elisabeth_rigal@examplecom"),
			Arguments.of("elisabeth_rigal?@example.com"),
			Arguments.of("élisabeth_rigal@example.com"),
			Arguments.of("")
		);
	}

	public static Stream<Arguments> provideValidEmailInputs() {
		return Stream.of(
			Arguments.of("elisabeth_rigal@example.com"),
			Arguments.of("ouais@re.com"),
			Arguments.of("ouais.bou@re.com")
		);
	}

	@ParameterizedTest
	@MethodSource("providePhoneInputs")
	public void testFormatPhone(String phone, String expected) {
		assert (Volunteer.formatPhone(phone).equals(expected));
	}

	@ParameterizedTest
	@MethodSource("provideNullPhoneInputs")
	public void testFormatPhoneNull(String phone) {
		assertNull(Volunteer.formatPhone(phone));
	}

	public static Stream<Arguments> providePhoneInputs() {
		return Stream.of(
			Arguments.of("+33000555882", "+33000555882"),
			Arguments.of("+33(0)0.75.55.99.79", "+330075559979"),
			Arguments.of("+33(0)0 00 55 52 26", "+330000555226"),
			Arguments.of("+330 00 55 57 66", "+33000555766"),
			Arguments.of("+33(0)0-00-55-50-28", "+330000555028"),
			Arguments.of("00-55-52-89-61", "+330055528961"),
			Arguments.of("+330-55-59-77-78", "+33055597778"),
			Arguments.of("00-55-59-77-78", "+330055597778")
		);
	}

	public static Stream<Arguments> provideNullPhoneInputs() {
		return Stream.of(
			Arguments.of(null, null),
			Arguments.of("123456798789789465467", null),
			Arguments.of("00", null),
			Arguments.of("", null),
			Arguments.of("+3301234567", null)
		);
	}

	@ParameterizedTest
	@MethodSource("isFullyEqualTrueInputs")
	public void testIsFullyEqualTrue(Volunteer vol1,Volunteer vol2) {
		assertTrue(vol2.isFullyEqual(vol1));
	}

	public static Stream<Arguments> isFullyEqualTrueInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test","test2","nickName", null, null),new Volunteer("test","test2","nickName", null, null)),
				Arguments.of(new Volunteer("test","test2",null, null, null),new Volunteer("test","test2",null, null, null))

		);
	}

	@ParameterizedTest
	@MethodSource("isFullyEqualFalseInputs")
	public void testIsFullyEqualFalse(Volunteer vol1,Volunteer vol2) {
		assertFalse(vol2.isFullyEqual(vol1));
	}

	public static Stream<Arguments> isFullyEqualFalseInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test",null,"nickName", null, null),new Volunteer("test","test2","nickName", null, null)),
				Arguments.of(new Volunteer("test","test2","nickname", null, null),new Volunteer("test","test2",null, null, null))

		);
	}
}
