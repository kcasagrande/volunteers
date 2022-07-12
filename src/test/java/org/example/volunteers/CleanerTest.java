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
		return Stream.of(
				Arguments.of(new Volunteer("test", "test2", null, "aba.ava@amamama.com", "0687654321"), 2, "Des personnes avec des email et des téléphones différents doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "061234567"), 1, "Des personnes avec un email en commun, des téléphones différents et un nom / prénom identiques doivent être considérés comme identiques"),
				Arguments.of(new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345687"), 1, "Des personnes avec des email en commun, des téléphones différents et un pseudo identiques doivent être considérés comme identiques"),
				Arguments.of(new Volunteer("test4", "test2", null, "test@test.com", null), 2, "Des personnes avec des email en commun mais avec des nom/prénom différents doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test", null, null, null, "0612345678"), 2, "Des personnes avec des téléphones en commun mais sans nom doivent être considérés comme différents"),
				Arguments.of(new Volunteer("test2", "test", null, "test@test.com", "0612345678"), 1, "Des personnes avec des email et des téléphones en commun, avec les nom/prénom inversés doivent être considérés comme identiques")
		);
	}

	public static Stream<Arguments> cleanDuplicatesEmailInputs() {
		return Stream.of(
				Arguments.of(new Volunteer("oui", "oui", null, "oui@oui.com", "0612345645"), 1, "Des personnes différentes ne doivent pas être merge et donc pas avoir de mail en commun"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "0612345678"), 1, "Des personnes identiques avec le mail identiques n'auront pas 2 adresses email identiques une fois merge, mais une seule pour ne pas dupliquer."),
				Arguments.of(new Volunteer("test", "test2", null, null, "0612345678"), 1, "Si une des personnes identiques a un mail null, il n'est pas ajouté à la liste des mails"),
				Arguments.of(new Volunteer("test", "test2", null, "oui@test.com", "0612345678"), 2, "Des personnes identiques avec un mail différents auront une liste de 2 emails une fois merge")
		);
	}

	public static Stream<Arguments> cleanDuplicatesPhoneInputs() {
		return Stream.of(//new Volunteer("test", "test2", "nicknametest", "test@test.com", "0612345678");
				Arguments.of(new Volunteer("test", "test2", null, "aba.ava@amamama.com", "0687654321"), 1, "Des personnes différentes ne doivent pas être merge et donc pas avoir de téléphone en commun"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "0612345687"), 2, "Deux personnes identiques avec un téléphone différent auront une liste de 2 téléphones une fois merge"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", "0612345678"), 1, "Deux personnes identiques avec un numéro de téléphone identique n'en auront qu'un une fois merge, pour ne pas dupliquer"),
				Arguments.of(new Volunteer("test", "test2", null, "test@test.com", null), 1, "Si un des personnes identique a un mail null, il n'est pas ajouté à la liste des téléphones")
		);
	}
}
