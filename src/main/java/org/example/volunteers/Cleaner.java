package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }

    public static String formatPhoneNumber(String name) {
        String nameRegex = "[^0-9a-zA-Z]+";
        String newPhoneNumber =  name.replaceAll(nameRegex, "");
        System.out.println(newPhoneNumber.substring(0, 2));
        if (newPhoneNumber.startsWith("33"))
            newPhoneNumber = "+" + newPhoneNumber;
        if (newPhoneNumber.charAt(0) == '0')
            newPhoneNumber = "+33" + newPhoneNumber.substring(1);
        System.out.println(newPhoneNumber);
        return newPhoneNumber;

    }
}
