package org.example.volunteers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleaner {

    private static List<String> ALLOWED_DOMAIN = List.of(new String[]{"gmail", "outlook", "icloud", "hotmail"});

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

    public static String correctEmail(String invalidEmail) {
        String correctedEmail = "";
        if (!invalidEmail.contains("@")) {
            for (int i = 0; i < ALLOWED_DOMAIN.size(); i++) {
                if (invalidEmail.contains(ALLOWED_DOMAIN.get(i))) {
                    String beginningOfEmail = invalidEmail.split(ALLOWED_DOMAIN.get(i))[0] + "@";
                    String domain = ALLOWED_DOMAIN.get(i);
                    String endingOfEmail = invalidEmail.split(ALLOWED_DOMAIN.get(i))[1];
                    correctedEmail =  beginningOfEmail + domain + endingOfEmail;
                }
            }
        }
        return correctedEmail;
    }


    public static List<Volunteer> getInvalidEmailAddresses(List<Volunteer> volunteers) {
        List<Volunteer> invalidEmailAddresses = new ArrayList<>();
        for (Volunteer volunteer : volunteers) {
            if (!volunteer.eMail.contains("@")) {
                invalidEmailAddresses.add(volunteer);
            }
        }
        return invalidEmailAddresses;
    }

    public static Boolean toTitleCase(String name) {
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1).toLowerCase())
                    .append(" ");
        }
        String nameRegex = "^[A-Z][a-z]*$";
        Pattern pattern = Pattern.compile(nameRegex);
        String nameTransformed = sb.toString().trim();
        Matcher matcher = pattern.matcher(nameTransformed);
        System.out.println(nameTransformed + " : " + matcher.matches());
        return matcher.matches();
    }
}
