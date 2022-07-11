package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cleaner {
    public static String formatName(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        for (Volunteer volunteer : volunteers) {
            String firstName = volunteer.firstName;
            String lastName = volunteer.lastName;

            // first & last names
            if (firstName != "" && Objects.equals(firstName.substring(1), firstName.substring(1).toUpperCase())) {
                volunteer.firstName = formatName(firstName);
            }

            if (lastName != "" && Objects.equals(lastName.substring(1), lastName.substring(1).toUpperCase())) {
                volunteer.lastName = formatName(lastName);
            }

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
            }
        }

        return new ArrayList<>(volunteers);
    }
}
