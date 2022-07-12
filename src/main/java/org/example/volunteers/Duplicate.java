package org.example.volunteers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Duplicate {
    public static List<Volunteer> removeDuplicateFirstNameLastNamePseudoMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();
        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            Volunteer reversedVolunteer = new Volunteer(volunteer.lastName, volunteer.firstName, volunteer.nickName, volunteer.eMail, volunteer.phone);
            if(!(linkedsetVolunteers.contains(volunteer.toString()) || linkedsetVolunteers.contains(reversedVolunteer.toString()))) {
                linkedsetVolunteers.add(volunteer.toString());
                uniqueVolunteers.add(volunteer);
            }
        }

        return uniqueVolunteers;
    }

    public static List<Volunteer> removeDuplicateMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();
        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            String volunteerStringTest = volunteer.eMail + ";" + volunteer.phone;

            if(!linkedsetVolunteers.contains(volunteerStringTest)) {
                linkedsetVolunteers.add(volunteerStringTest);
                uniqueVolunteers.add(volunteer);
            }
        }

        return uniqueVolunteers;
    }
}
