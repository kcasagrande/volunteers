package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;
import java.util.*;
import java.util.stream.Collectors;


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
        List<Volunteer> volunteersWithBadEmails = new ArrayList<>();
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


    public static List<Volunteer> removeDuplicateByFullName() throws Exception{
        throw new Exception("not implemented");
    }

    public  static List<Volunteer> removeDuplicateByPhoneNumber() throws Exception{
        throw new Exception("not implemented");
    }

    public  static String formatPhoneNumber(String phoneNumber){
        return phoneNumber.replaceFirst("\\+\\d{2}","0")
                .replaceFirst("\\(0\\)", "")
                .replace(".", "")
                .replace("-", "")
                .replace(" ", "");
    }

    public static List<Volunteer> checkVolunteersWithNoPhoneNumber(List<Volunteer> volunteers){
        return volunteers.stream().filter(x-> x.getPhone() == null || x.getPhone().isEmpty()).collect(Collectors.toList());
    }

    public static HashMap<String,List<Volunteer>> checkDuplicatePhoneNumbers(List<Volunteer> volunteers){
        HashMap<String , List<Volunteer>> mapPhoneNumbersVolunteers = new HashMap<>();
        List<String> phoneNumbers = volunteers
                .stream().map(x-> formatPhoneNumber(x.getPhone()))
                .collect(Collectors.toList());
        phoneNumbers.remove(null);
        for(String phoneNumber : phoneNumbers){
            int occurrences = Collections.frequency(phoneNumbers, phoneNumber);
            if(occurrences > 1 && !mapPhoneNumbersVolunteers.containsKey(phoneNumber)){
                mapPhoneNumbersVolunteers
                        .put(phoneNumber,volunteers.stream()
                        .filter(x-> formatPhoneNumber(x.getPhone()) == phoneNumber).collect(Collectors.toList()));
            }
        }
        return mapPhoneNumbersVolunteers;
    }

    public static List<Volunteer> checkBadPhoneNumber(List<Volunteer> volunteers){
        List<Volunteer> volunteersWithBadPhoneNumbers = new ArrayList<>();
        Validations validator = new Validations();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getPhone() == null || volunteer.getPhone().isEmpty()) {
                continue;
            }
            if (!validator.validatePhoneNumber(volunteer.getPhone())) {
                volunteersWithBadPhoneNumbers.add(volunteer);
            }
        }
        return volunteersWithBadPhoneNumbers;
    }

}
