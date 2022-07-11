package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

            if (Objects.equals(volunteer.nickName, "")) {
                volunteer.nickName = volunteer.firstName;
            }
            //nickname

            //phone

            if (!volunteer.phone.isEmpty()) {
                String correctedPhoneNumber = volunteer.phone.replaceAll("[^0-9]", "");

                if(correctedPhoneNumber.charAt(1) == '3') {
                    correctedPhoneNumber = correctedPhoneNumber.substring(2);
                } else {
                    correctedPhoneNumber = correctedPhoneNumber.substring(1);
                }

                if (correctedPhoneNumber.length() == 10) {
                    correctedPhoneNumber = correctedPhoneNumber.substring(1);
                }
                correctedPhoneNumber = "+33" + correctedPhoneNumber;
                volunteer.phone = correctedPhoneNumber;

            if (Objects.equals(volunteer.nickName, "")) {
                volunteer.nickName = volunteer.firstName;
            String emailRegex = "^(.+)@$";
            String emailRegex = "^(.+)@(\\S+)$";
            if (!Pattern.compile(emailRegex).matcher(volunteer.eMail).matches()) {
                volunteer.eMail = "";
            }
        }

        return new ArrayList<>(volunteers);
    }
}
