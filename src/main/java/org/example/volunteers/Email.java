package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Email {
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
}
