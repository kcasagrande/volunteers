package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    private static final String[][] UMLAUT_REPLACEMENTS = { { "É", "E" }, { "é", "e" }, { "È", "E" }, { "è", "e" } };

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        volunteers = removeAccents(volunteers);
        volunteers = sanitizeEmailInsteadOfPhone(volunteers);
        volunteers = Email.cleanupMailAddresses(volunteers);
        volunteers = Phone.cleanupPhoneNumber(volunteers);
        volunteers = updateCaseInNames(volunteers);
        volunteers = Duplicate.removeDuplicateFirstNameLastNamePseudoMailPhone(volunteers);
        volunteers = Duplicate.removeDuplicateMailPhone(volunteers);
        return new ArrayList<Volunteer>(volunteers);
    }

    public static List<Volunteer> removeAccents(List<Volunteer> volunteers) {
        List<Volunteer> cleanedVolunteers = new ArrayList<>();

        for (Volunteer volunteer: volunteers) {
            String firstName = volunteer.firstName;
            String lastName = volunteer.lastName;
            String nickName = volunteer.nickName;

            for (String[] umlautReplacement : UMLAUT_REPLACEMENTS) {
                firstName = firstName.replaceAll(umlautReplacement[0], umlautReplacement[1]);
                lastName = lastName.replaceAll(umlautReplacement[0], umlautReplacement[1]);
                nickName = nickName.replaceAll(umlautReplacement[0], umlautReplacement[1]);
            }

            cleanedVolunteers.add(new Volunteer(firstName, lastName, nickName, volunteer.eMail, volunteer.phone));
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

    public static List<Volunteer> updateCaseInNames(List<Volunteer> volunteers) {
        List<Volunteer> cleanedVolunteers = new ArrayList<>();

        List<String> characters = new ArrayList<>();

        for (Volunteer volunteer: volunteers) {
            String cleanedVolunteerFirstName = volunteer.firstName;
            String cleanedVolunteerLastName = volunteer.lastName;

            List<String> cleanedVolunteerFirstNames = new ArrayList<>();
            String[] slitedVolunteerFirstNames = cleanedVolunteerFirstName.split("-");
            for (String slitedVolunteerFirstName : slitedVolunteerFirstNames) {
                cleanedVolunteerFirstNames.add(
                        slitedVolunteerFirstName.length() > 1
                                ? slitedVolunteerFirstName.substring(0, 1).toUpperCase() + slitedVolunteerFirstName.substring(1).toLowerCase()
                                : slitedVolunteerFirstName.toLowerCase()
                );
            }

            cleanedVolunteerFirstName = String.join("-", cleanedVolunteerFirstNames);

            List<String> cleanedVolunteerLastNames = new ArrayList<>();
            String[] slitedVolunteerLastNames = cleanedVolunteerLastName.split("-");
            for (String slitedVolunteerLastName : slitedVolunteerLastNames) {
                cleanedVolunteerLastNames.add(
                        slitedVolunteerLastName.length() > 1
                                ? slitedVolunteerLastName.substring(0, 1).toUpperCase() + slitedVolunteerLastName.substring(1).toLowerCase()
                                : slitedVolunteerLastName.toLowerCase()
                );
            }

            cleanedVolunteerLastName = String.join("-", cleanedVolunteerLastNames);

            String cleanedVolunteerNickName = volunteer.nickName.toLowerCase();

            cleanedVolunteers.add(new Volunteer(cleanedVolunteerFirstName, cleanedVolunteerLastName, cleanedVolunteerNickName, volunteer.eMail, volunteer.phone));
        }

        return cleanedVolunteers;
    }
}
