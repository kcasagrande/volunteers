package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class Cleaner {
    private static String formatName(String input) {
        if (!Objects.equals(input, "") && !Objects.equals(input.substring(1), input.substring(1).toUpperCase())) {
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
        return input;
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        for (Volunteer volunteer : volunteers) {

            // first & last name
            volunteer.firstName = formatName(volunteer.firstName);
            volunteer.lastName = formatName(volunteer.lastName);

            //phone

            if (!volunteer.phone.isEmpty()) {
                String correctedPhoneNumber = volunteer.phone.replaceAll("[^0-9]", "");

                if (correctedPhoneNumber.charAt(1) == '3') {
                    correctedPhoneNumber = correctedPhoneNumber.substring(2);
                } else {
                    correctedPhoneNumber = correctedPhoneNumber.substring(1);
                }

                if (correctedPhoneNumber.length() == 10) {
                    correctedPhoneNumber = correctedPhoneNumber.substring(1);
                }
                correctedPhoneNumber = "+33" + correctedPhoneNumber;
                volunteer.phone = correctedPhoneNumber;
            }

/*
            ^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$
*/

            String emailFormatRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            if (volunteer.eMail.charAt(volunteer.eMail.length() - 1) == '.') {
                volunteer.eMail = removeLastCharacter(volunteer.eMail);
            }
            if (!Pattern.compile(emailFormatRegex).matcher(volunteer.eMail).matches()) {
                volunteer.eMail = "";
            }
        }
        return new ArrayList<>(volunteers);
    }


    public static String removeLastCharacter(String str) {
        String result = Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                .orElse(str);
        return result;
    }
}
