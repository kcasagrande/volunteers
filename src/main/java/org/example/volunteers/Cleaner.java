package org.example.volunteers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Cleaner {
    private static final String[][] UMLAUT_REPLACEMENTS = { { "É", "E" }, { "é", "e" }, { "È", "E" }, { "è", "e" } };

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        return new ArrayList<Volunteer>(volunteers);
    }

    // Email
    public static Boolean isValidEmail(Volunteer volunteer) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", volunteer.eMail);
    }

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

    public static List<Volunteer> removeSpecialCharacters(List<Volunteer> volunteers) {
        List<Volunteer> cleanedVolunteers = new ArrayList<>();

        for (Volunteer volunteer: volunteers) {
            String firstName = volunteer.firstName;
            String lastName = volunteer.lastName;

            for (String[] umlautReplacement : UMLAUT_REPLACEMENTS) {
                firstName = firstName.replaceAll(umlautReplacement[0], umlautReplacement[1]);
                lastName = lastName.replaceAll(umlautReplacement[0], umlautReplacement[1]);
            }

            cleanedVolunteers.add(new Volunteer(firstName, lastName, volunteer.nickName, volunteer.eMail, volunteer.phone));
        }

        return cleanedVolunteers;
    }

    public static List<Volunteer> sanitizeEmailInsteadOfPhone(List<Volunteer> volunteers) {
        //List<Volunteer> volunteersSanitized = new ArrayList<>();
        //LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {

            if (isValidPhoneNumber(volunteer.eMail)) {
                String oldMailHasPhone = volunteer.eMail;
                String oldPhoneHasMail = volunteer.phone;
                volunteer.phone = oldMailHasPhone;
                volunteer.eMail = oldPhoneHasMail;
            }
        }

        return volunteers;
    }

    // Phone
    public static Boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("^[\\+][0-9]{1,3}[0-9]{9}$", phoneNumber);
    }

    public static Volunteer convertDashesFromPhoneNumber(Volunteer volunteer) {
        String cleanPhone = volunteer.phone.replace("-", "");
        return new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhone);
    }

    public static Volunteer convertDotsFromPhoneNumber(Volunteer volunteer) {
        String cleanPhone = volunteer.phone.replace(".", "");
        return new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhone);
    }

    public static Volunteer convertSpacesFromPhoneNumber(Volunteer volunteer) {
        String cleanPhone = volunteer.phone.replace(" ", "");
        return new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhone);
    }

    public static Volunteer convertParenthesisFromPhoneNumber(Volunteer volunteer) {
        String cleanPhone = volunteer.phone.replace("(", "").replace(")", "");
        return new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhone);
    }

    public static Volunteer replaceBeginningPhoneNumber(Volunteer volunteer) {
        String cleanPhone = volunteer.phone;
        if (volunteer.phone.length() == 10) {
            if (volunteer.phone.charAt(0) == '0') {
                cleanPhone = volunteer.phone.replaceFirst("0", "+33");
            }
        } else if (volunteer.phone.length() == 9 && volunteer.phone.charAt(0) != '+') {
            cleanPhone = "+33" + volunteer.phone;
        }
        return new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhone);
    }

}
