package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.models.VolunteerEmailError;
import org.example.volunteers.models.VolunteerNameError;
import org.example.volunteers.models.VolunteerPhoneNumberError;

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Cleaner {

    private Validations validators;
    public VolunteerEmailError emailValidator;
    public VolunteerNameError nameValidator;
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
        this.checkNames();
        volunteersToRemove.addAll(this.emailValidator.noEmail);
        volunteersToRemove.addAll(this.phoneNumberValidator.noPhoneNumber);
        volunteersToRemove.addAll(this.emailValidator.badFormatEmail);
        volunteersToRemove.addAll(this.phoneNumberValidator.badFormatPhoneNumber);
        volunteersToRemove.addAll(this.nameValidator.malformedNames);
        volunteersToRemove.addAll(this.getDuplicateToRemove());

        List<Volunteer> allVolunteersCorrect = this.allVolunteers;
        allVolunteersCorrect.removeAll(volunteersToRemove);

        return allVolunteersCorrect;
    }

    public List<Volunteer> getDuplicateToRemove(){
        Set<Volunteer> volunteersToRemove = new HashSet<>();
        volunteersToRemove.addAll(this.getDuplicateToRemove( this.emailValidator.duplicateEmail));
        volunteersToRemove.addAll(this.getDuplicateToRemove(this.phoneNumberValidator.duplicatePhoneNumber));
        volunteersToRemove.addAll(this.getDuplicateToRemove(this.nameValidator.duplicateName));
        return volunteersToRemove.stream().collect(Collectors.toList());
    }

    private List<Volunteer> getDuplicateToRemove(HashMap<String,List<Volunteer>> mapToRemoveEquals){

        List<Volunteer> badVolunteer = new ArrayList<>();
        List<Volunteer> cleanVolunteer = new ArrayList<>();

        for ( String condition : mapToRemoveEquals.keySet()){
            List<Volunteer> volunteers = mapToRemoveEquals.get(condition);
            for(Volunteer volunteer : volunteers){
                if(!cleanVolunteer.stream().anyMatch(x-> x.equals(volunteer))){
                    List<Volunteer> sameVolonteers =  volunteers.stream().filter(x-> x.equals(volunteer)).collect(Collectors.toList());
                    if(sameVolonteers.size() < 2){
                        badVolunteer.add(volunteer);
                    }else{
                        cleanVolunteer.add(volunteer);
                    }
                }else{
                    badVolunteer.add(volunteer);
                }
            }
        }
        return badVolunteer;
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

    public void checkNames() {

        HashMap<String, List<Volunteer>> mapVolunteerNames = new HashMap<>();
        ArrayList<Volunteer> volunteersWithMalformedNames = new ArrayList<>();

        for (Volunteer volunteer : this.allVolunteers) {

            if (volunteer.getFirstName() == null || volunteer.getFirstName().isEmpty()) {
                volunteersWithMalformedNames.add(volunteer);
                continue;
            }

            if (volunteer.getLastName() == null || volunteer.getLastName().isEmpty()) {
                volunteersWithMalformedNames.add(volunteer);
            }

            if (this.validators.validateFirstName(volunteer.getFirstName()) || this.validators.validateLastName(volunteer.getLastName())) {
                volunteersWithMalformedNames.add(volunteer);
            }

            if (mapVolunteerNames.containsKey(volunteer.getFirstName() + "." + volunteer.getLastName())) {
                List<Volunteer> storedDuplicateVolunteers = mapVolunteerNames.get(volunteer.getFirstName() + "." + volunteer.getLastName());
                int originalSize = storedDuplicateVolunteers.size();

                if (storedDuplicateVolunteers.size() > 0) {
                    boolean isDuplicated = false;
                    for (Volunteer storedVolunteer : storedDuplicateVolunteers) {
                        if (storedVolunteer.getEmail().equals(volunteer.getEmail())) {
                            isDuplicated = true;

                            break;
                        }
                    }
                    if (isDuplicated) {
                        storedDuplicateVolunteers.add(volunteer);
                    }
                } else {
                    storedDuplicateVolunteers.add(volunteer);
                }

                if (originalSize != storedDuplicateVolunteers.size()) {
                    mapVolunteerNames.put(volunteer.getFirstName() + "." + volunteer.getLastName(), storedDuplicateVolunteers);
                }

            } else {
                mapVolunteerNames.put(volunteer.getFirstName() + "." + volunteer.getLastName(), new ArrayList<>(Arrays.asList(volunteer)));
            }
        }

        HashMap<String, List<Volunteer>> mapDuplicateNamesVolunteers = mapVolunteerNames
                .entrySet()
                .stream()
                .filter(x-> x.getValue().stream().count() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        this.nameValidator = new VolunteerNameError(mapDuplicateNamesVolunteers, volunteersWithMalformedNames);
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
