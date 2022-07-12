package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cleaner {
	public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
		// This function should contain your dark magic.

		volunteers = cleanNotContactable(volunteers);
		volunteers = cleanDuplicates(volunteers);

		// For now, it simply returns a copy of the initial list.
		return new ArrayList<>(volunteers);
	}


	public static List<Volunteer> cleanNotContactable(List<Volunteer> volunteers) {
		return volunteers.
				stream()
				.filter(Volunteer::isContactable)
				.collect(Collectors.toList());
	}


	public static List<Volunteer> cleanDuplicates(List<Volunteer> volunteers) {

		for (int i = 0; i < volunteers.size(); i++) {
			Volunteer volunteer1 = volunteers.get(i);
			for (int j = i + 1; j < volunteers.size(); j++) {
				Volunteer volunteer2 = volunteers.get(j);
				if (volunteer1.isSamePerson(volunteer2)) {
					volunteer1.mergeVolunteerIdentities(volunteer2);
					volunteers.remove(volunteer2);
					j--;
				}
			}
		}

		return volunteers;
	}

}
