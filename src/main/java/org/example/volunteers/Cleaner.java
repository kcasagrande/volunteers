package org.example.volunteers;

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
        System.out.println("clean up call");
        List<Volunteer> cleanVolunteers = cleanupMailAddresses(volunteers);
        return new ArrayList<Volunteer>(cleanVolunteers);
    }
    public static Boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
    public static String cleanEmailAddress(String email) {
        email = email.toLowerCase();
        email = email.replaceAll("é", "e");
        email = email.replaceAll("è", "e");
        email = email.replaceAll("ê", "e");
        email = email.replaceAll("œ", "oe");
        email = email.replaceAll("ë", "e");
        email = email.replaceAll("à", "a");
        email = email.replaceAll("ä", "a");
        email = email.replaceAll("â", "a");
        email = email.replaceAll("î", "i");
        email = email.replaceAll("ï", "i");
        email = email.replaceAll("ì", "i");
        email = email.replaceAll("û", "u");
        email = email.replaceAll("ù", "u");
        email = email.replaceAll("ü", "u");
        email = email.replaceAll("ô", "o");
        email = email.replaceAll("ò", "o");
        email = email.replaceAll("ö", "o");
        return email;
    }
    public static List<Volunteer> cleanupMailAddresses(List<Volunteer> volunteers) {
        System.out.println("CALL");
        List<Volunteer> cleanedVolunteers = new ArrayList<>();
        for(Volunteer volunteer: volunteers) {
            String cleanEmail = "";
            String sanitizedEmail = cleanEmailAddress(volunteer.eMail);
            if (isValidEmail(sanitizedEmail)) {
                cleanEmail = sanitizedEmail;
            }
            cleanedVolunteers.add(new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, cleanEmail, volunteer.phone));
        }
        return cleanedVolunteers;
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
