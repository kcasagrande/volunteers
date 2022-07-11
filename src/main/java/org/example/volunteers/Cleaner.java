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

		// On degage si
		// Nom Prenom identiques avec meme email ou phone
		// SI nom prenom et pseudo identique alors regrouper email et phone

		for (int i = 0; i < volunteers.size(); i++) {
			Volunteer volunteer1 = volunteers.get(i);
			for (int j = i + 1; j < volunteers.size(); j++) {
				Volunteer volunteer2 = volunteers.get(j);
				System.out.println(volunteer1);
				System.out.println(volunteer2);
				if (volunteer1.isFirstNameAndLastnameEquals(volunteer2)) {
					//System.out.println(volunteer1 + " (" + i + ") " + " -> " + volunteer2 + " (" + j + ")");
					volunteer1.addEmail(volunteer2.geteMail());
					volunteer1.addPhone(volunteer2.getPhone());
					volunteers.remove(volunteer2);
					j--;
				}
			}
		}

		return volunteers;
	}

}
