package org.example.volunteers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        return new ArrayList<Volunteer>(volunteers);
    }

    public static Boolean isValidEmail(Volunteer volunteer) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", volunteer.eMail);
    }

    public static List<Volunteer> removeDuplicateFirstNameLastNamePseudoMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();
        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            if(!linkedsetVolunteers.contains(volunteer.toString())) {
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
