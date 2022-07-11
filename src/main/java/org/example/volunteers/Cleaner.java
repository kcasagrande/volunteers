package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        List<Volunteer> newVolunteers = new ArrayList<Volunteer>();

        for (Volunteer volunteer : volunteers) {
            String firstName = formatText(volunteer.firstName);
            String lastName = formatText(volunteer.lastName);
            String phone = formatPhone(volunteer.phone);
            Volunteer newVolunteer = new Volunteer(firstName, lastName, volunteer.nickName, volunteer.eMail, phone);
            newVolunteers.add(newVolunteer);
        }


        return new ArrayList<>(newVolunteers);
    }

    public static String formatText(String text){
        // Return null text
        if (text.length() < 1){
            return text;
        }
         return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
    public static String formatPhone(String phone){
        // Return null phone number
        if(phone.length() < 1){
            return phone;
        }

        // Remove dot, space, dash, pearenthesis
        String regex = "\\.| |-|\\(\\d*\\)";
        phone = phone.replaceAll(regex, "");

        // Replace first 0 with +33
        char firstChar = phone.charAt(0);
        if (firstChar == '0'){
            phone = phone.replaceFirst("0", "+33");
        }

        return phone;
    }

}
