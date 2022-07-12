package org.example.volunteers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringUtilsTest {
	@ParameterizedTest
	@MethodSource("provideStringsOneNull")
	public void testHasJustOneNull(String st1, String st2, Boolean expected) {
		assert(StringUtils.hasJustOneNull(st1, st2) == expected);
	}

	public static Stream<Arguments> provideStringsOneNull() {
		return Stream.of(
				Arguments.of("test", "test", false),
				Arguments.of("", "", false),
				Arguments.of("test", null, true),
				Arguments.of(null, "test", true),
				Arguments.of(null, null, false)
		);
	}

	@ParameterizedTest
	@MethodSource("provideStringsAllNull")
	public void testBothStringsAreNull(String st1, String st2, Boolean expected) {
		assert(StringUtils.bothStringsAreNull(st1, st2) == expected);
	}

	public static Stream<Arguments> provideStringsAllNull() {
		return Stream.of(
				Arguments.of("test", "test", false),
				Arguments.of("", "", false),
				Arguments.of("test", null, false),
				Arguments.of(null, "test", false),
				Arguments.of(null, null, true)
		);
	}

	@ParameterizedTest
	@MethodSource("provideNamesInputs")
	public void testFormatName(String name, String expected) {
		if (expected == null) {
			assertNull(StringUtils.formatNames(name));
			return;
		}
		assert (StringUtils.formatNames(name).equals(expected));
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
		assertNull(StringUtils.formatEmail(email));
	}

	@ParameterizedTest
	@MethodSource("provideValidEmailInputs")
	public void testFormatEmailNotNull(String email) {
		assertNotNull(StringUtils.formatEmail(email));
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
