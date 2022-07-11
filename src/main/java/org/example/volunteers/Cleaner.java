package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
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
