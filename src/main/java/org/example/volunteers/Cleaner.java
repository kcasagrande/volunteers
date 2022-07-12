package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    private static final String[][] UMLAUT_REPLACEMENTS = { { "É", "E" }, { "é", "e" }, { "È", "E" }, { "è", "e" } };

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        volunteers = Email.cleanupMailAddresses(volunteers);
        volunteers = Duplicate.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);
        return new ArrayList<Volunteer>(volunteers);
    }

    public static List<Volunteer> removeAccents(List<Volunteer> volunteers) {
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
        for (Volunteer volunteer: volunteers) {
            if (Phone.isValidPhoneNumber(volunteer.eMail)) {
                String oldMailHasPhone = volunteer.eMail;
                String oldPhoneHasMail = volunteer.phone;
                volunteer.phone = oldMailHasPhone;
                volunteer.eMail = oldPhoneHasMail;
            }
        }

        return volunteers;
    }
}
