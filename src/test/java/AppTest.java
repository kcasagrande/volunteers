import org.example.volunteers.Volunteer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
	protected App testedInstance;

	@Test
	void shouldClearUserList() {
		List<Volunteer> testList = new ArrayList<Volunteer>();

		testList.add(new Volunteer(
				"Huga",
				"Denis",
				"zgeg2200",
				"test@test.com",
				"+330645342321"
		));

		testList.add(new Volunteer(
				"Higo",
				"Denis",
				"zgeg22",
				"test@test.fr",
				"+330645342321"
		));

		testList.add(new Volunteer(
				"Tom",
				"Martin",
				"tomDu81",
				"tom@martin.fr",
				"+330675439876"
		));

		List<Volunteer> cleanedList = App.cleanUp(testList);
		testList.remove(1);
		Assertions.assertEquals(testList, cleanedList);
	}

	// Cas de figure d'un homonyme
	@Test
	void shouldNotCleatUserList() {
		List<Volunteer> testList = new ArrayList<Volunteer>();

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"zgeg2200",
				"test@test.com",
				"+330645342321"
		));

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"MONFION32",
				"HUGO@denis.fr",
				"+330675439876"
		));

		List<Volunteer> cleanedList = App.cleanUp(testList);
		Assertions.assertEquals(testList, cleanedList);
	}

	// Cas de figure de deux volotaires avec le même numéro mais des infos différentes
	@Test
	void shouldRemoveVolunteerWithSamePhone() {
		List<Volunteer> testList = new ArrayList<Volunteer>();

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"Hugden",
				"",
				"+330645342321"
		));

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"Marmiton",
				"HUGO@denis.fr",
				"+330645342321"
		));

		List<Volunteer> cleanedList = App.cleanUp(testList);
		testList.remove(1);
		Assertions.assertEquals(testList, cleanedList);
	}

	// Cas de figure ou un volontaire n'a pas de numéro ou de mail
	@Test
	void shouldNotRemoveVolunteerWithNoPhoneAndMail() {
		List<Volunteer> testList = new ArrayList<Volunteer>();

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"Hugden",
				"",
				""
		));

		testList.add(new Volunteer(
				"Hugo",
				"Denis",
				"Marmiton",
				"HUGO@denis.fr",
				"+330645342321"
		));

		List<Volunteer> cleanedList = App.cleanUp(testList);
		Assertions.assertEquals(testList, cleanedList);
	}
}