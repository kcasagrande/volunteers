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

		Assertions.assertEquals(testList.remove(1), cleanedList);
	}
}