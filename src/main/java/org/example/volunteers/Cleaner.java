package org.example.volunteers;

import com.sun.tools.javac.util.StringUtils;

import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }
    public static Boolean isValidEmail(Volunteer volunteer) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", volunteer.eMail);
    }



    public static List<Volunteer> removeDuplicateFirstNameLastNamePseudoMailPhone(List<Volunteer> volunteers) {


        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            linkedsetVolunteers.add(volunteer.toString());
        }

        volunteers.clear();

        for (String str_volunteer: linkedsetVolunteers) {
            String[] volunteerSplit = str_volunteer.split(";");
            Volunteer newVolunteer = new Volunteer(volunteerSplit[0], volunteerSplit[1], volunteerSplit[2], volunteerSplit[3], volunteerSplit[4]);
            volunteers.add(newVolunteer);
        }
        return volunteers;

    }



}
