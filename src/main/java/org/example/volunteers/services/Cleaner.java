package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.models.VolunteerEmailError;
import org.example.volunteers.models.VolunteerPhoneNumberError;

import java.util.*;
import java.util.stream.Collectors;


public class Cleaner {

    private Validations validators;
    public VolunteerEmailError emailValidator;
    public VolunteerPhoneNumberError phoneNumberValidator;
    public List<Volunteer> allVolunteers;
    public Cleaner(List<Volunteer> volunteers){
        this.validators = new Validations();
        this.allVolunteers = volunteers;
    }

    public List<Volunteer> cleanUp() {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        Set<Volunteer> volunteersToRemove = new HashSet<>();
        this.checkEmails();
        this.checkPhoneNumbers();
        volunteersToRemove.addAll(this.emailValidator.noEmail);
        volunteersToRemove.addAll(this.phoneNumberValidator.noPhoneNumber);
        volunteersToRemove.addAll(this.emailValidator.badFormatEmail);
        volunteersToRemove.addAll(this.phoneNumberValidator.badFormatPhoneNumber);
        for (String email : this.emailValidator.duplicateEmail.keySet()){
            volunteersToRemove.addAll(this.emailValidator.duplicateEmail.get(email));
        }
        for (String phoneNumber : this.phoneNumberValidator.duplicatePhoneNumber.keySet()){
            volunteersToRemove.addAll(this.phoneNumberValidator.duplicatePhoneNumber.get(phoneNumber));
        }
        List<Volunteer> allVolunteersCorrect = this.allVolunteers;
        allVolunteersCorrect.removeAll(volunteersToRemove);
        return allVolunteersCorrect;
    }


    public void checkEmails(){
        ArrayList<Volunteer> checkVolunteersWithNoEmail = new ArrayList();
        HashMap<String , List<Volunteer>> mapEmailVolunteers = new HashMap<>();
        ArrayList<Volunteer> volunteersWithBadEmails =new ArrayList<>();

        for(Volunteer volunteer : this.allVolunteers){
            if(volunteer.getEmail() == null || volunteer.getEmail().isEmpty()){
                checkVolunteersWithNoEmail.add(volunteer);
                continue;
            }
            if(!this.validators.validateEmailAddress(volunteer.getEmail())){
                volunteersWithBadEmails.add(volunteer);
                continue;
            }
            if(mapEmailVolunteers.containsKey(volunteer.getEmail())){
                List<Volunteer> emailVolunteers = mapEmailVolunteers.get(volunteer.getEmail());
                emailVolunteers.add(volunteer);
                mapEmailVolunteers.put(volunteer.getEmail() , emailVolunteers);
            }else{
                mapEmailVolunteers.put(volunteer.getEmail() , new ArrayList<>(Arrays.asList(volunteer)));
            }
        }
        HashMap<String , List<Volunteer>> mapDuplicateEmailVolunteers = mapEmailVolunteers
                .entrySet()
                .stream()
                .filter(x-> x.getValue().stream().count() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        this.emailValidator= new VolunteerEmailError(checkVolunteersWithNoEmail , mapDuplicateEmailVolunteers , volunteersWithBadEmails);
    }

    public void checkPhoneNumbers(){
        ArrayList<Volunteer> checkVolunteersWithNoPhoneNumbers = new ArrayList();
        HashMap<String , List<Volunteer>> mapPhoneNumberVolunteers = new HashMap<>();
        ArrayList<Volunteer> volunteersWithBadPhoneNumbers =new ArrayList<>();

        for(Volunteer volunteer : this.allVolunteers){
            if(volunteer.getEmail() == null || volunteer.getEmail().isEmpty()){
                checkVolunteersWithNoPhoneNumbers.add(volunteer);
                continue;
            }
            if(!this.validators.validatePhoneNumber(volunteer.getPhone())){
                checkVolunteersWithNoPhoneNumbers.add(volunteer);
                continue;
            }
            if(mapPhoneNumberVolunteers.containsKey(volunteer.getPhone())){
                List<Volunteer> phoneNumberVolunteers = mapPhoneNumberVolunteers.get(volunteer.getPhone());
                phoneNumberVolunteers.add(volunteer);
                mapPhoneNumberVolunteers.put(volunteer.getPhone() , phoneNumberVolunteers);
            } else {
                mapPhoneNumberVolunteers.put(volunteer.getPhone() , new ArrayList<>(Arrays.asList(volunteer)));
            }
        }
        HashMap<String , List<Volunteer>> mapDuplicatePhoneNumberVolunteers = mapPhoneNumberVolunteers
                .entrySet()
                .stream()
                .filter(x-> x.getValue().stream().count() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        this.phoneNumberValidator= new VolunteerPhoneNumberError(checkVolunteersWithNoPhoneNumbers, mapDuplicatePhoneNumberVolunteers, volunteersWithBadPhoneNumbers);
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
