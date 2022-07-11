package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Cleaner {

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }

    public static List<Volunteer> checkVolunteersWithNoEmail(List<Volunteer> volunteers){
        return volunteers.stream().filter(x-> x.getEmail() == null || x.getEmail().isEmpty()).collect(Collectors.toList());
    }

    public static HashMap<String,List<Volunteer>> checkDuplicateEmail(List<Volunteer> volunteers){
        HashMap<String , List<Volunteer>> mapEmailVolunteers = new HashMap<>();
        List<String> emails = volunteers.stream().map(x-> x.getEmail()).collect(Collectors.toList());
        emails.remove(null);
        for(String email : emails){
            int occurrences = Collections.frequency(emails, email);
            if(occurrences > 1 && !mapEmailVolunteers.containsKey(email)){
                mapEmailVolunteers.put(email,volunteers.stream().filter(x->x.getEmail() == email).collect(Collectors.toList()));
            }
        }
        return mapEmailVolunteers;
    }

    public static List<Volunteer> checkBadEmail(List<Volunteer> volunteers){
        List<Volunteer> volunteersWithBadEmails =new ArrayList<>();
        Validations validator = new Validations();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getEmail() == null || volunteer.getEmail().isEmpty()) {
                continue;
            }
            if (!validator.validateEmailAddress(volunteer.getEmail())) {
                volunteersWithBadEmails.add(volunteer);
            }
        }
        return volunteersWithBadEmails;
    }

    public static List<Volunteer> removeDuplicateByFullName(List<Volunteer> volunteers) throws Exception{
        Validations checkFormat = new Validations();

        //===================================================================================
        //Utiliser les streams pour traiter les occurences
        Volunteer volunteerTest = volunteers.get(0);
        int index = 0;

        for(Volunteer volunteer : volunteers){
            //===================================================================================
            //A regrouper dans methode externe pour les verif
            if(checkFormat.validateFirstName(volunteer.getFirstName())){
                throw new Exception("Malformed name for user "+volunteer.getFirstName());
            }
            if(checkFormat.validateLastName(volunteer.getLastName())){
                throw new Exception("Malformed name for user "+volunteer.getFirstName());
            }

            if(volunteer.getFirstName().equals(volunteerTest.getFirstName()) && volunteer.getLastName().equals(volunteerTest.getLastName())){
                volunteers.remove(index);
            }
            //===================================================================================
            index++;
        }
        return volunteers;
    }

    public  static List<Volunteer> removeDuplicateByPhoneNumber() throws Exception{
        throw new Exception("not implemented");
    }


}
