package org.example.volunteers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
		assert(cleanedList.get(0).getPhones().size() == numbersOfPhones);
	}

	public static Stream<Arguments> cleanDuplicatesInputs(){
		return Stream.of(
			Arguments.of(new Volunteer("test","test2",null, "aba.ava@amamama.com", "0687654321"), 2, 1, 1),
			Arguments.of(new Volunteer("test","test2","nicknametest", "test@test.com", "0612345687"), 1, 1, 2),
			Arguments.of(new Volunteer("test4","test2",null, "test@test.com", null), 2, 1, 1),
			Arguments.of(new Volunteer("test5","test5","nicknametest", null, "0605040302"), 2, 1, 1),
			Arguments.of(new Volunteer("test",null,null, null, "0612345678"), 2, 1, 1),
			Arguments.of(new Volunteer("test","test2",null, "test@test.com", "061234567"), 1, 1, 1),
			Arguments.of(new Volunteer("test2","test",null, "test@test.com", "0612345678"), 1, 1, 1)
		);
	}

	@Test
	public void testCleanUp() throws IOException {
		Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

		List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get("src/main/resources/data.csv")).stream()
				.map(string -> Arrays.stream(string.split(";", -1))
						.map(token -> quotes.matcher(token).replaceAll("$1"))
						.collect(toList()))
				.map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
				.collect(toList());

		List<Volunteer> outputVolunteers = Cleaner.cleanUp(inputVolunteers);

		assertEquals(outputVolunteers.size(),398);
	}
}
