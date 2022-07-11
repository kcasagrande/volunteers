package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        for (Volunteer volunteer : volunteers) {
            String firstName = volunteer.firstName;
            System.out.println(firstName);
            if (Objects.equals(firstName.substring(1), firstName.substring(1).toUpperCase())) {
                String result = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
                System.out.println(result);
                volunteer.firstName = result;
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
