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
        ArrayList<Volunteer> volunteersWithoutPhoneNumber = new ArrayList();
        HashMap<String,List<Volunteer>> volunteersMappedByPhoneNumber = new HashMap<>();
        ArrayList<Volunteer> volunteersWithBadPhoneNumber = new ArrayList<>();

        for(Volunteer volunteer : this.allVolunteers){
            String volunteerPhone = volunteer.getPhone();
            if(volunteerPhone == null || volunteerPhone.isEmpty()){
                volunteersWithoutPhoneNumber.add(volunteer);
                continue;
            }
            if(!this.validators.validatePhoneNumber(volunteerPhone)){
                volunteersWithBadPhoneNumber.add(volunteer);
                continue;
            }

            String formattedVolunteerPhone = formatPhoneNumber(volunteerPhone);
            if(volunteersMappedByPhoneNumber.containsKey(formattedVolunteerPhone)){
                List<Volunteer> phoneNumberVolunteers = volunteersMappedByPhoneNumber.get(formattedVolunteerPhone);
                phoneNumberVolunteers.add(volunteer);
                volunteersMappedByPhoneNumber.put(formattedVolunteerPhone, phoneNumberVolunteers);
            } else {
                volunteersMappedByPhoneNumber.put(formattedVolunteerPhone, new ArrayList<>(Arrays.asList(volunteer)));
            }
        }
        HashMap<String , List<Volunteer>> mapDuplicatePhoneNumberVolunteers = volunteersMappedByPhoneNumber
                .entrySet()
                .stream()
                .filter(x-> x.getValue().stream().count() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        this.phoneNumberValidator = new VolunteerPhoneNumberError(volunteersWithoutPhoneNumber, mapDuplicatePhoneNumberVolunteers, volunteersWithBadPhoneNumber);
    }

    public  static String formatPhoneNumber(String phoneNumber){
        return phoneNumber.replaceFirst("\\+\\d{2}","0")
                .replaceFirst("\\(0\\)", "")
                .replace(".", "")
                .replace("-", "")
                .replace(" ", "");
    }

}
