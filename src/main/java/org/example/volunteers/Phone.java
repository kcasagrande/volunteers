package org.example.volunteers;

import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;


public class Phone {

    // Phone
    public static List<Volunteer> cleanupPhoneNumber(List<Volunteer> volunteers) {
        List<Volunteer> cleanedVolunteers = new ArrayList<>();
        for(Volunteer volunteer: volunteers) {
            String cleanPhoneNumber = "";
            String sanitizedPhoneNumber = cleanPhoneNumber(volunteer.phone);
            if (isValidPhoneNumber(sanitizedPhoneNumber)) {
                cleanPhoneNumber = sanitizedPhoneNumber;
            }
            cleanedVolunteers.add(new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, volunteer.eMail, cleanPhoneNumber));
        }
        return cleanedVolunteers;
    }

    public static Boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("^[\\+][1-9][0-9]?[0-9]?[0-9]{9}$", phoneNumber);
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        // Remove Characters
        phoneNumber = phoneNumber.replace("-", "");
        phoneNumber = phoneNumber.replace(".", "");
        phoneNumber = phoneNumber.replace(" ", "");
        phoneNumber = phoneNumber.replace("(0)", "");
        phoneNumber = phoneNumber.replace("(", "").replace(")", "");

        // Change Beginning and add default country code
        if (phoneNumber.length() == 10) {
            if (phoneNumber.charAt(0) == '0') {
                phoneNumber = phoneNumber.replaceFirst("0", "+33");
            }
        } else if (phoneNumber.length() == 9 && phoneNumber.charAt(0) != '+') {
            phoneNumber = "+33" + phoneNumber;
        }
        return phoneNumber;
    }
}
