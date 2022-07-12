package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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

        // On fait gère les doublons ayant les memes firstname, lastname et email
        List<Volunteer> completedVolunteers = filterByFirstnameLastnameAndEmail(volunteers);

        completedVolunteers = filterByFirstnameLastnameAndNickname(completedVolunteers);

        System.out.println("Taille finale : " + completedVolunteers.size());

        return completedVolunteers;
    }

    private static List<Volunteer> filterByFirstnameLastnameAndEmail(List<Volunteer> volunteers){
        // Supprime les doublons ayant le meme firstname, lastname et email
        List<Volunteer> volunteersFiltered = getUniqueFilteredDatasByFirstnameLastnameAndEmail(volunteers);

        // Ensuite on parcours la liste des elements pour remplir les champs phone et nickname différents
        List<Volunteer> completedVolunteers = new ArrayList<Volunteer>();
        for (Volunteer volunteer : volunteersFiltered) {
            List<Volunteer> dataToTransfer = getFilteredDatasByFirstnameLastnameAndEmail(volunteers, volunteer);
            if(dataToTransfer.size()>1){
                String nickname = "";
                String phone = "";
                for (Volunteer data : dataToTransfer) {
                    if (data.nickName.length()>0 && nickname.length()>0){
                        nickname += ", ";
                    }
                    if (data.phone.length()>0 && phone.length()>0){
                        phone += ", ";
                    }
                    nickname += data.nickName;
                    phone += data.phone;
                }
                Volunteer newVolunteer = new Volunteer(volunteer.firstName, volunteer.lastName, nickname, volunteer.eMail, phone);
                completedVolunteers.add(newVolunteer);
            }else{
                completedVolunteers.add(volunteer);
            }

        }

        return completedVolunteers;
    }

    private static List<Volunteer> getUniqueFilteredDatasByFirstnameLastnameAndEmail(List<Volunteer> volunteers){
        Set<Volunteer> volunteersFiltered = volunteers.stream().map(
                volunteer -> volunteers.stream().filter(data ->
                        data.firstName.equals(volunteer.firstName)
                        && data.lastName.equals(volunteer.lastName)
                        && data.eMail.equals(volunteer.eMail)
                    )
                    .findFirst()
                    .get()
                )
                .collect(Collectors.toSet());
        return new ArrayList<>(volunteersFiltered);
    }

    private static List<Volunteer> getFilteredDatasByFirstnameLastnameAndEmail(List<Volunteer> volunteers, Volunteer volunteer){
        Set<Volunteer> volunteersFiltered = volunteers.stream().filter(data ->
            data.firstName.equals(volunteer.firstName)
            && data.lastName.equals(volunteer.lastName)
            && data.eMail.equals(volunteer.eMail)
        )
        .collect(Collectors.toSet());
        return new ArrayList<>(volunteersFiltered);
    }

    private static List<Volunteer> filterByFirstnameLastnameAndNickname(List<Volunteer> volunteers){
        // Supprime les doublons ayant le meme firstname, lastname et nickname
        List<Volunteer> volunteersFiltered = getUniqueFilteredDatasByFirstnameLastnameAndNickname(volunteers);

        // Ensuite on parcours la liste des elements pour remplir les champs phone et nickname différents
        List<Volunteer> completedVolunteers = new ArrayList<Volunteer>();
        for (Volunteer volunteer : volunteersFiltered) {
            List<Volunteer> dataToTransfer = getFilteredDatasByFirstnameLastnameAndNickname(volunteers, volunteer);
            if(dataToTransfer.size()>1){
                String email = "";
                String phone = "";
                for (Volunteer data : dataToTransfer) {
                    if (data.eMail.length()>0 && email.length()>0){
                        email += ", ";
                    }
                    if (data.phone.length()>0 && phone.length()>0){
                        phone += ", ";
                    }
                    email += data.eMail;
                    phone += data.phone;
                }
                Volunteer newVolunteer = new Volunteer(volunteer.firstName, volunteer.lastName, volunteer.nickName, email, phone);
                completedVolunteers.add(newVolunteer);
            }else{
                completedVolunteers.add(volunteer);
            }

        }

        return completedVolunteers;
    }

    private static List<Volunteer> getUniqueFilteredDatasByFirstnameLastnameAndNickname(List<Volunteer> volunteers){
        Set<Volunteer> volunteersFiltered = volunteers.stream().map(
            volunteer -> volunteers.stream().filter(data ->
                data.firstName.equals(volunteer.firstName)
                && data.lastName.equals(volunteer.lastName)
                && data.nickName.equals(volunteer.nickName)
            )
            .findFirst()
            .get()
            )
        .collect(Collectors.toSet());
        return new ArrayList<>(volunteersFiltered);
    }

    private static List<Volunteer> getFilteredDatasByFirstnameLastnameAndNickname(List<Volunteer> volunteers, Volunteer volunteer){
        Set<Volunteer> volunteersFiltered = volunteers.stream().filter(data ->
            data.firstName.equals(volunteer.firstName)
            && data.lastName.equals(volunteer.lastName)
            && data.nickName.equals(volunteer.nickName)
        )
        .collect(Collectors.toSet());
        return new ArrayList<>(volunteersFiltered);
    }


}