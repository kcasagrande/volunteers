package org.example.volunteers;

import com.sun.tools.javac.util.StringUtils;

import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        removeDuplicate(volunteers);
        return new ArrayList<Volunteer>(volunteers);
    }
    public static Boolean isValidEmail(Volunteer volunteer) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", volunteer.eMail);
    }

    public static List<Volunteer> removeDuplicate(List<Volunteer> volunteers) {
        List<Volunteer> cleanedVolunteers = new ArrayList<>();

        for (Volunteer volunteer: volunteers) {
            if (cleanedVolunteers.size() == 0) {
                cleanedVolunteers.add(volunteer);
                continue;
            }

            Boolean addVolunteer = true;
            for (Volunteer cleanedVolunteer: cleanedVolunteers) {
                if (Objects.equals(cleanedVolunteer.toString(), volunteer.toString())) {
                    addVolunteer = false;
                }
            }

            if (addVolunteer) {
                cleanedVolunteers.add(volunteer);
            }
        }

        return cleanedVolunteers;
    }


//    public static List<Volunteer> removeDuplicateFirstNameLastNamePseudoMailPhone(List<Volunteer> volunteers) {
//
//
//        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();
//
//        for (Volunteer volunteer: volunteers) {
//            linkedsetVolunteers.add(volunteer.toString());
//        }
//
//        volunteers.clear();
//
//        for (String str_volunteer: linkedsetVolunteers) {
//            String[] volunteerSplit = str_volunteer.split(";");
//            Volunteer newVolunteer = new Volunteer(volunteerSplit[0], volunteerSplit[1], volunteerSplit[2], volunteerSplit[3], volunteerSplit[4]);
//            volunteers.add(newVolunteer);
//        }
//        return volunteers;
//
//    }
}
