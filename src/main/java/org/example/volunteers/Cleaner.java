package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cleaner {
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
