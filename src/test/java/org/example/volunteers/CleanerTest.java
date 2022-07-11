package org.example.volunteers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CleanerTest {

	@ParameterizedTest
	@MethodSource("cleanNotContactableInputs")
	public void testCleanNotContactable(Volunteer volunteer, Integer expectedListSize){
		List<Volunteer> list = new ArrayList();
		list.add(volunteer);
		assert(Cleaner.cleanNotContactable(list).size() == expectedListSize);
	}

	public static Stream<Arguments> cleanNotContactableInputs(){
		return Stream.of(
			Arguments.of(new Volunteer("test","test2",null, null, null), 0),
			Arguments.of(new Volunteer("test","test2",null, "test@test.com", null), 1),
			Arguments.of(new Volunteer("test","test2",null, null, "0605040302"), 1),
			Arguments.of(new Volunteer("test","test2",null, "test@test.com", "0605040302"), 1),
			Arguments.of(new Volunteer("test","test2",null, null, "061234567"), 0)
		);
	}


	@ParameterizedTest
	@MethodSource("cleanDuplicatesInputs")
	public void testCleanDuplicates(Volunteer volunteer, Integer expectedListSize, Integer numbersOfEmails){
		Volunteer defaultVolunteer =  new Volunteer("test","test2","nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assert(cleanedList.size() == expectedListSize);
	}

	@ParameterizedTest
	@MethodSource("cleanDuplicatesInputs")
	public void testCleanDuplicatesEmailsNumber(Volunteer volunteer, Integer expectedListSize, Integer numbersOfEmails){
		Volunteer defaultVolunteer =  new Volunteer("test","test2","nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assert(cleanedList.get(0).getEmails().size() == numbersOfEmails);
	}

	@ParameterizedTest
	@MethodSource("cleanDuplicatesInputs")
	public void testCleanDuplicatesPhonesNumber(Volunteer volunteer, Integer expectedListSize, Integer numbersOfEmails, Integer numbersOfPhones){
		Volunteer defaultVolunteer =  new Volunteer("test","test2","nicknametest", "test@test.com", "0612345678");
		List<Volunteer> list = new ArrayList();
		list.add(defaultVolunteer);
		list.add(volunteer);
		List<Volunteer> cleanedList = Cleaner.cleanDuplicates(list);
		assert(cleanedList.get(0).getEmails().size() == numbersOfPhones);
	}

	public static Stream<Arguments> cleanDuplicatesInputs(){
		return Stream.of(
			Arguments.of(new Volunteer("test","test2",null, "aba.ava@amamama.com", null), 1, 2,1),
			Arguments.of(new Volunteer("test","test2","nicknametest", "test@test.com", "0612345678"), 1, 1, 2),
			Arguments.of(new Volunteer("test4","test2",null, "test@test.com", null), 2, 1, 1),
			Arguments.of(new Volunteer("test5","test5","nicknametest", null, "0605040302"), 2, 1, 2),
			Arguments.of(new Volunteer("test",null,null, null, "0612345678"), 2, 1, 2),
				Arguments.of(new Volunteer("test",null,null, null, "0612345678"), 2, 1, 2)
		);
	}
}
