package org.example.volunteers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CleanerTest {

	@ParameterizedTest
	@MethodSource("cleanNotContactableInputs")
	public void testCleanNotContactable(Volunteer volunteer, Integer expectedListSize, String message) {
		List<Volunteer> list = new ArrayList();
		list.add(volunteer);
		assertEquals(Cleaner.cleanNotContactable(list).size(), expectedListSize, message);
	}

	public static Stream<Arguments> cleanNotContactableInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, null, null), 0, "Une personne sans email ou téléphone n'est pas contactable"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", null), 1, "Une personne avec un email est contactable"),
				Arguments.of(new Volunteer("test", "test2", null, null, "0605040302"), 1, "Une personne avec un téléphone est contactable"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "0605040302"), 1, "Une personne avec un mail et un téléphone est contactable"),
				Arguments.of(new Volunteer("test", "test2", null, "test.com", null), 0, "Une personne sans téléphone et avec un mail invalide n'est pas contactable"),
				Arguments.of(new Volunteer("test", "test2", null, null, "061234567"), 0, "Une personne sans mail et avec un téléphone invalide n'est pas contactable"),
				Arguments.of(new Volunteer("test", "test2", null, "test.com", "0612345"), 0, "Une personne avec une mail et un téléphone invalides n'est pas contactable")
		);
	}


	@ParameterizedTest
	@MethodSource("cleanDuplicatesInputs")
	public void testCleanDuplicates(Volunteer volunteer, Integer expectedListSize, String message) {
		Volunteer defaultVolunteer = new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assertEquals(cleanedList.size(), expectedListSize, message);
	}

	@ParameterizedTest
	@MethodSource("cleanDuplicatesEmailInputs")
	public void testCleanDuplicatesEmailsNumber(Volunteer volunteer, Integer numbersOfEmails, String message) {
		Volunteer defaultVolunteer = new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assertEquals(cleanedList.get(0).getEmails().size(), numbersOfEmails, message);
	}

	@ParameterizedTest
	@MethodSource("cleanDuplicatesPhoneInputs")
	public void testCleanDuplicatesPhonesNumber(Volunteer volunteer, Integer numbersOfPhones, String message) {
		Volunteer defaultVolunteer = new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assertEquals(cleanedList.get(0).getPhones().size(), (int) numbersOfPhones, message);
	}

	public static Stream<Arguments> cleanDuplicatesInputs() {
		return Stream.of(//new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345678");
				Arguments.of(new Volunteer("test", "test2", null, "aba.ava@amamama.com", "0687654321"), 2, "Des personnes avec des email et des téléphones différents doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "061234567"), 1, "Des personnes avec un email en commun, des téléphones différents et un nom / prénom identiques doivent être considérés comme identiques"),
				Arguments.of(new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345687"), 1, "Des personnes avec des email en commun, des téléphones différents et un pseudo identiques doivent être considérés comme identiques"),
				Arguments.of(new Volunteer("test4", "test2", null, "test@test.com", null), 2, "Des personnes avec des email en commun doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test5", "test5", "nicknametest", null, "0605040302"), 2, "Des personnes avec des email et des téléphones différents doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test", null, null, null, "0612345678"), 2, "Des personnes avec des email et des téléphones différents doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test2", "test", null, "test@test.com", "0612345678"), 1, "Des personnes avec des email et des téléphones différents doivent être considérés comme différents")
		);
	}

	public static Stream<Arguments> cleanDuplicatesEmailInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, "aba.ava@amamama.com", "0687654321"), 1),
				Arguments.of(new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345687"), 1),
				Arguments.of(new Volunteer("test4", "test2", null, "test@test.com", null),1),
				Arguments.of(new Volunteer("test5", "test5", "nicknametest", null, "0605040302"), 1),
				Arguments.of(new Volunteer("test", null, null, null, "0612345678"),1),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "061234567"), 1),
				Arguments.of(new Volunteer("test2", "test", null, "test@test.com", "0612345678"),1)
		);
	}

	public static Stream<Arguments> cleanDuplicatesPhoneInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, "aba.ava@amamama.com", "0687654321"), 1),
				Arguments.of(new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345687"), 2),
				Arguments.of(new Volunteer("test4", "test2", null, "test@test.com", null), 1),
				Arguments.of(new Volunteer("test5", "test5", "nicknametest", null, "0605040302"), 1),
				Arguments.of(new Volunteer("test", null, null, null, "0612345678"), 1),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "061234567"), 1),
				Arguments.of(new Volunteer("test2", "test", null, "test@test.com", "0612345678"), 1)
		);
	}
}
