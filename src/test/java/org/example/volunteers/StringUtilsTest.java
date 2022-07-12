package org.example.volunteers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
	@ParameterizedTest
	@MethodSource("provideStringsOneNull")
	public void testHasJustOneNull(String st1, String st2, Boolean expected,String message) {
		assertEquals(StringUtils.hasJustOneNull(st1, st2), expected, message);
	}

	public static Stream<Arguments> provideStringsOneNull() {
		return Stream.of(
			Arguments.of("test", "test", false, "We check that no value is null"),
			Arguments.of("", "", false, "We check that no value is null even with empty fields"),
			Arguments.of("test", null, true, "We check that if the name is detected as null"),
			Arguments.of(null, "test", true, "We check that the first name is detected as null"),
			Arguments.of(null, null, false, "We check that if the two values are null we return false")
		);
	}

	@ParameterizedTest
	@MethodSource("provideStringsAllNull")
	public void testBothStringsAreNull(String st1, String st2, Boolean expected,String message) {
		assertEquals(StringUtils.bothStringsAreNull(st1, st2), expected, message);
	}

	public static Stream<Arguments> provideStringsAllNull() {
		return Stream.of(
				Arguments.of("test", "test", false,"We test if the 2 values are not null"),
				Arguments.of("", "", false, "We test if the 2 values are not null"),
				Arguments.of("test", null, false,"We test if the 2 values are not null"),
				Arguments.of(null, "test", false, "We test if the 2 values are not null"),
				Arguments.of(null, null, true , "We test if the 2 values are null")
		);
	}

	@Test
	public void testFormatNameIsNull() {
		assertNull(StringUtils.formatNames(""),"We test that if we fill an empty field the return is null");
	}

	@ParameterizedTest
	@MethodSource("provideNamesInputs")
	public void testFormatName(String name, String expected, String message) {
		assertEquals(StringUtils.formatNames(name),(expected),message);
	}

	public static Stream<Arguments> provideNamesInputs() {
		return Stream.of(
				Arguments.of("test", "TEST","We test that the function puts the string in upper case"),
				Arguments.of("TeSt", "TEST","We test that the function puts the string in upper case"),
				Arguments.of("tést", "TEST","We test that the function capitalizes the string and removes the accents"),
				Arguments.of("Clément", "CLEMENT","We test that the function capitalizes the string and removes the accents"),
				Arguments.of("azerty123", "AZERTY123","We test that the function puts the string in uppercase even if there are numbers"),
				Arguments.of("Édouard", "EDOUARD","We test that the function puts the whole string in uppercase even if there is an accent on a capital letter"),
				Arguments.of("Jéan-Louis", "JEAN-LOUIS","We test that the function capitalizes the string even if there is a dash in the name")
		);
	}

	@ParameterizedTest
	@MethodSource("provideInvalidEmailInputs")
	public void testFormatEmailNull(String email, String message) {
		assertNull(StringUtils.formatEmail(email), message);
	}

	@ParameterizedTest
	@MethodSource("provideValidEmailInputs")
	public void testFormatEmailNotNull(String email, String message) {
		assertNotNull(StringUtils.formatEmail(email), message);
	}

	public static Stream<Arguments> provideInvalidEmailInputs() {
		return Stream.of(
				Arguments.of("elisabeth_rigalexample.com", "Vérifie que l'email sans le @ sous incorect"),
				Arguments.of("elisabeth_rigal@examplecom", "Vérifie que l'email sans le . index après le @ sous incorect"),
				Arguments.of("elisabeth_rigal?@example.com", "Vérifie que l'email sans le @ sous incorect"),
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
		assert (StringUtils.formatPhone(phone).equals(expected));
	}

	@ParameterizedTest
	@MethodSource("provideNullPhoneInputs")
	public void testFormatPhoneNull(String phone) {
		assertNull(StringUtils.formatPhone(phone));
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
}
