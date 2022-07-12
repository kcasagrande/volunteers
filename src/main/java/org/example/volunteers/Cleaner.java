package org.example.volunteers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.

        List<Volunteer> formatedVolunteers = new ArrayList<Volunteer>();

        for (Volunteer volunteer : volunteers) {
            String firstName = formatText(volunteer.firstName);
            String lastName = formatText(volunteer.lastName);
            String phone = formatPhone(volunteer.phone);
            String email = formatEmail(volunteer.eMail);
            Volunteer newVolunteer = new Volunteer(firstName, lastName, volunteer.nickName, email, phone);
            formatedVolunteers.add(newVolunteer);
        }

        List<Volunteer> reducedVolunteers = removeDuplicates(formatedVolunteers);


        return new ArrayList<>(reducedVolunteers);
    }

    public static String formatText(String text){
        // Return null text
        if (text.length() < 1){
            return text;
        }
        text = text.substring(0,1).toUpperCase()+text.substring(1).toLowerCase();
        // Characters before uppercase
        char[] chars = {'-', ' ', '\''};
        for (int i = 0; i < text.length(); i++) {
            // If a character if found
            int index = (new String(chars)).indexOf(text.charAt(i));
            if(index>=0){
                // We get the text before and after, and put in uppercase the letter after the character
                text = text.substring(0, i+1)+(new String(text.charAt(i+1)+"")).toUpperCase()+text.substring(i+2);
            }
        }

         return text;
    }
    public static String formatPhone(String phone){
        // Return null phone number
        if(phone.length() < 1){
            return phone;
        }

        // Remove dot, space, dash, pearenthesis
        String regex = "\\.| |-|\\(\\d*\\)";
        phone = phone.replaceAll(regex, "");

        // Remove letters
        String regex2 = "[a-zA-Z]";
        phone = phone.replaceAll(regex2, "");

        // Replace first 0 with +33
        char firstChar = phone.charAt(0);
        if (firstChar == '0'){
            phone = phone.replaceFirst("0", "+33");
        }

        return phone;
    }
    public static String formatEmail (String email){

        // Delete email if wrongly formatted
        if(!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()){
            return "";
        }
        // LowerCase email
        email = email.toLowerCase();
        return email;
    }

    public static List<Volunteer> removeDuplicates (List<Volunteer> volunteers){
        System.out.println("Taille initiale : "+ volunteers.size() );

        // Utilisation d'un HashSet pour la suppression des doublons parfait
        Set<Volunteer> volunteerSet = new HashSet<>(volunteers);
        volunteers = new ArrayList<>(volunteerSet);

        System.out.println("Taille finale : "+volunteers.size());
        return volunteers;
    }

}
