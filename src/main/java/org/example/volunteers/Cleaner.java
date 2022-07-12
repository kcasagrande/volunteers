package org.example.volunteers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        //        volunteers.stream().map(volunteer -> cleanPhoneNumber(volunteer.getPhone())).collect(Collectors.toList());
        for(Volunteer v: volunteers){
            // Formattage du nom et du prénom
            v.setFirstName( formatFirstName( v.getFirstName()) );
            v.setLastName( formatLastName( v.getLastName()) );

            // Formattage du pseudo
            v.setNickName( Cleaner.formatNickName( v.getNickName()) );

            // Formattage du mail
            v.setEMail( Cleaner.formatEmail( v.getEMail()) );

            // Formattage du téléphone
            String oldPhone = v.getPhone();
            String newPhone = cleanPhoneNumber(oldPhone);
            if(!oldPhone.equals(newPhone)) {
//                System.out.println("CHANGEMENT from " + oldPhone + " to " + newPhone);
                v.setPhone(newPhone);
            }
        }
        return volunteers;
    }

    public static String capitalizeWord(String str){
        String[] words = str.toLowerCase().split("\\s");
        String capitalizeWord = "";
        for(String w:words){
            String first = w.substring(0,1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }

    public static String formatFirstName(String name){
        name = name.trim();

        if(name == null || name.isEmpty()) return "";

        name = capitalizeWord(name);
        name = name.replaceAll(" ", "-");
        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−\u00C0-\u00FF]","");
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String formatLastName(String name) {
        name = name.trim();

        if(name == null || name.isEmpty()) return "";

        name = capitalizeWord(name);
        name = name.toLowerCase().replaceAll(" ", "-");
        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−\u00C0-\u00FF]", "");
        return name.toUpperCase();
    }

    public static String formatNickName(String nickname){
        nickname = nickname.trim();

        if(nickname == null || nickname.isEmpty()) return "";

        nickname = nickname.replaceAll(" ", "").replaceAll("[^a-zA-Z0-9\u00C0-\u00FF-_&@€!]", "");
        return nickname;
    }

    public static String cleanPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            return "";
        }

        // regex : replace ' ', '.' and '-' by ''
        final Pattern pattern = Pattern.compile("[ .-]", Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(phoneNumber);
        final String result = matcher.replaceAll("");


        // regex :  replace '(0)' by ''
        final Pattern pattern2 = Pattern.compile("(\\(0\\))", Pattern.MULTILINE);
        final Matcher matcher2 = pattern2.matcher(result);
        final String result2 = matcher2.replaceAll("");

        // regex : check if phone number start with `+33`, content 9 number.
        final Pattern pattern3 = Pattern.compile("(?:\\+|00)33([0-9]........)", Pattern.MULTILINE);
        final Matcher matcher3 = pattern3.matcher(result2);

        if (matcher3.find()) {
            return matcher3.group(0);
        }

        // replace the first number by `+33`
        final Pattern pattern4 = Pattern.compile("^0");
        final Matcher matcher4 = pattern4.matcher(result2);

        return matcher4.replaceAll("+33");
    }

    public static String formatEmail(String email){
        email = email.trim();
        if(email != null && !email.isEmpty()) {
            email = email.replaceAll(" ", "");
            // If string does not contain @ symbol, returns nothing
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            if(m.matches()){
                return email.toLowerCase();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}
