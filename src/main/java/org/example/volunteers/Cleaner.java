package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        //        volunteers.stream().map(volunteer -> cleanPhoneNumber(volunteer.getPhone())).collect(Collectors.toList());

            // Formattage du téléphone
        volunteers = removeEmptyVolunteers(volunteers);

        for (Volunteer v : volunteers) {
            // Formattage du nom et du prénom
            v.setFirstName( formatFirstName( v.getFirstName()) );
            v.setLastName( formatLastName( v.getLastName()) );

            // Formattage du pseudo
            v.setNickName( Cleaner.formatNickName( v.getNickName()) );

            // Formattage du mail
            v.setEMail( Cleaner.formatEmail( v.getEMail()) );

            String oldPhone = v.getPhone();
            String newPhone = cleanPhoneNumber(oldPhone);

            if (!oldPhone.equals(newPhone)) {
//                System.out.println("CHANGEMENT from " + oldPhone + " to " + newPhone);
                v.setPhone(newPhone);
            }
        }

        return volunteers;
    }

    public static List<Volunteer> removeEmptyVolunteers(List<Volunteer> volunteers) {
        System.out.println("Remove empty lines");
        List<Volunteer> newList = new ArrayList<>();

        for (Volunteer v : volunteers) {
            if (v.getFirstName().equals("") && v.getLastName().equals("") && v.getNickName().equals("") && v.getPhone().equals("") && v.getEMail().equals("")) {
                System.out.println("Empty line");
            } else {
                newList.add(v);
            }
        }

        return newList;
    }

    public static String capitalizeWord(String str) {
        String[] words = str.toLowerCase().split("\\s|-");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim().replaceAll("\\s", "-");
    }

    public static String formatFirstName(String name) {
        name = name.trim();

        if (name.isEmpty()) {
            return "";
        }

        name = capitalizeWord(name);
        name = name.replaceAll(" ", "-");

        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−\u00C0-\u00FF]", "");

        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String formatLastName(String name) {
        name = name.trim();

        if(name.isEmpty()) {
            return "";
        }

        name = capitalizeWord(name);
        name = name.toLowerCase().replaceAll(" ", "-");

        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−\u00C0-\u00FF]", "");

        return name.toUpperCase();
    }

    public static String formatNickName(String nickname){
        nickname = nickname.trim();
        if(nickname.isEmpty()) {
            return "";
        }

        String result = cleanSpace(nickname, "~", false);
        return result.replaceAll("[^a-zA-Z0-9\u00C0-\u00FF-_&@€!]", "");
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            return "";
        }

        phoneNumber = cleanSpace(phoneNumber, "", true);

        // regex :  replace '(0)' by ''
        final Pattern pattern2 = Pattern.compile("(\\(0\\))", Pattern.MULTILINE);
        final Matcher matcher2 = pattern2.matcher(phoneNumber);
        phoneNumber = matcher2.replaceAll("");

        // regex : check if phone number start with `+33`, content 9 number.
        Matcher regexChecked = checkWithRegex(phoneNumber, "(?:\\+|00)33([0-9]........)");

        if (regexChecked.find()) {
            return regexChecked.group(0);
        }

        // replace the first number by `+33`
        return phoneNumber.replaceAll("^0", "+33");
    }

    public static String formatEmail(String email){
        email = email.trim();

        if(email.isEmpty()) {
            return "";
        }

        email = email.replaceAll(" ", "");
        // If string does not contain @ symbol, returns nothing
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        if(m.matches()){
            return email.toLowerCase();
        }

        return "";
    }

    public static String cleanSpace(String str, String replacement, boolean replacePointAndDash) {
        String regex = "[ ]";

        if (replacePointAndDash) {
            regex = "[ .-]";
        }

        // regex : replace ' ', '.' and '-' by replacement.
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);

        return matcher.replaceAll(replacement);
    }

    public static Matcher checkWithRegex(String str, String regex) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

        return pattern.matcher(str);
    }
}
