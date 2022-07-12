package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class Cleaner {

    private List<Volunteer> cleanedVolunteersList = new ArrayList<Volunteer>();

    public List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        for (Volunteer volunteer : volunteers) {

            // first & last name
            volunteer.firstName = formatName(volunteer.firstName);
            volunteer.lastName = formatName(volunteer.lastName);

            //phone
            volunteer.phone = formatPhone(volunteer.phone);

            //email
            volunteer.eMail = formatMail(volunteer.eMail);

            isVolunteerDuplicated(volunteer);
        }
        return new ArrayList<>(cleanedVolunteersList);
    }

    private void isVolunteerDuplicated(Volunteer volunteer) {

        isVolunteerInverted(volunteer);

        Optional<Volunteer> volunteerWithSameInformation = cleanedVolunteersList.stream()
                .filter(
                        volunteerItem -> volunteerItem.firstName.equals(volunteer.firstName) &&
                                volunteerItem.lastName.equals(volunteer.lastName) &&
                                volunteerItem.nickName.equals(volunteer.nickName) &&
                                volunteerItem.eMail.equals(volunteer.eMail) &&
                                volunteerItem.phone.equals(volunteer.phone)
                ).findFirst();

        if (!volunteerWithSameInformation.isPresent()) {
            cleanedVolunteersList.add(volunteer);
        }
    }

    private Volunteer isVolunteerInverted(Volunteer volunteer) {
        Optional<Volunteer> volunteerWithInvertedInformation = this.cleanedVolunteersList.stream()
                .filter(
                        volunteerItem -> volunteerItem.lastName.equals(volunteer.firstName) &&
                                volunteerItem.firstName.equals(volunteer.lastName)
                ).findFirst();

        if (volunteerWithInvertedInformation.isPresent()) {
            String firstname = volunteer.firstName;
            String lastname = volunteer.lastName;

            volunteer.firstName = lastname;
            volunteer.lastName = firstname;
        }

        return volunteer;
    }

    private String formatPhone(String phone) {
        if (!phone.isEmpty()) {
            String correctedPhoneNumber = phone.replaceAll("[^0-9]", "");

            if (correctedPhoneNumber.charAt(1) == '3') {
                correctedPhoneNumber = correctedPhoneNumber.substring(2);
            } else {
                correctedPhoneNumber = correctedPhoneNumber.substring(1);
            }

            if (correctedPhoneNumber.length() == 10) {
                correctedPhoneNumber = correctedPhoneNumber.substring(1);
            }
            correctedPhoneNumber = "+33" + correctedPhoneNumber;
            return correctedPhoneNumber;
        } else {
            return "";
        }
    }

    private static String formatMail(String email) {
        if (!email.isEmpty()) {
            String emailFormatRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            if (email.charAt(email.length() - 1) == '.') {
                email = removeLastCharacter(email);
            }
            if (!Pattern.compile(emailFormatRegex).matcher(email).matches()) {
                return "";
            }
            return email;
        }
        return "";
    }

    private static String formatName(String input) {
        // Only non-empty names
        if (!Objects.equals(input, "")) {
            // Add uppercase on first character and rest lowercase
            if (!Objects.equals(input.substring(1), input.substring(1).toUpperCase()) || Objects.equals(input, input.toUpperCase())) {
                input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            }
        }

        // Format names with '-'
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-') {
                input = input.substring(0, i) + "-" + input.substring(i+1, i+2).toUpperCase() + input.substring(i+2);
            }
        }
        return input;
    }

    public static String removeLastCharacter(String str) {
        String result = Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                .orElse(str);
        return result;
    }
}
