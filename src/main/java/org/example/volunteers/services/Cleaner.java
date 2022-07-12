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
        volunteersToRemove.addAll(this.nameValidator.noNames);
        HashMap<Boolean,Set<Volunteer>> duplicateVolunteers = this.cleanAndMergeDuplicate();
        volunteersToRemove.addAll(duplicateVolunteers.get(false));

        List<Volunteer> allVolunteersCorrect = this.allVolunteers;
        allVolunteersCorrect.removeAll(volunteersToRemove);
        allVolunteersCorrect.addAll(duplicateVolunteers.get(true));
        return allVolunteersCorrect;
    }

    public HashMap<Boolean,Set<Volunteer>> cleanAndMergeDuplicate() {
        HashMap<Boolean,Set<Volunteer>> mapResult = new HashMap<>();
        HashSet<Volunteer> badVolunteers = new HashSet<>();

        List<Volunteer> excludedVolunteers = new ArrayList<>();
        excludedVolunteers.addAll(this.emailValidator.noEmail);
        excludedVolunteers.addAll(this.emailValidator.badFormatEmail);

        excludedVolunteers.addAll(this.phoneNumberValidator.noPhoneNumber);
        excludedVolunteers.addAll(this.phoneNumberValidator.badFormatPhoneNumber);

        excludedVolunteers.addAll(this.nameValidator.malformedNames);

        HashMap<Boolean,List<Volunteer>> mapEmail = this.cleanAndMergeDuplicate(this.emailValidator.duplicateEmail , new ArrayList<>(),excludedVolunteers, new String[]{"email"});
        badVolunteers.addAll(mapEmail.get(false));
        HashMap<Boolean,List<Volunteer>> mapPhone = this.cleanAndMergeDuplicate(this.phoneNumberValidator.duplicatePhoneNumber, mapEmail.get(true),excludedVolunteers, new String[]{"phone"});
        badVolunteers.addAll(mapPhone.get(false));
        HashMap<Boolean,List<Volunteer>> mapName = this.cleanAndMergeDuplicate(this.nameValidator.duplicateName, mapPhone.get(true) ,excludedVolunteers, new String[]{"firstName","lastName"});
        badVolunteers.addAll(mapName.get(false));

        mapResult.put(false,badVolunteers);
        mapResult.put(true,mapName.get(true).stream().collect(Collectors.toSet()));
        return mapResult;
    }

    private HashMap<Boolean,List<Volunteer>> cleanAndMergeDuplicate(HashMap<String,List<Volunteer>> mapToRemoveEquals ,List<Volunteer> cleanVolunteer , List<Volunteer> excluded , String[] fieldNames) {
        HashMap<Boolean,List<Volunteer>> mapToRemove = new HashMap<>();
        List<Volunteer> badVolunteer = new ArrayList<>();
        List<Volunteer> volunteersPassed = new ArrayList<>();

        for ( String condition : mapToRemoveEquals.keySet()){
            List<Volunteer> volunteers = mapToRemoveEquals.get(condition);
            for(Volunteer volunteer : volunteers){


                if(!cleanVolunteer.stream().anyMatch(x-> x.equals(volunteer))){
                    List<Volunteer> sameVolunteers =  volunteers.stream().filter(x-> x.equals(volunteer)&& !excluded.contains(x)).collect(Collectors.toList());
                    if(sameVolunteers.size() > 1){
                        cleanVolunteer.add(volunteer);
                    }else if (!volunteersPassed.stream().anyMatch(x-> x.equals(volunteer))){
                        List<Volunteer> sameVolunteersByProperty =  volunteers.stream().filter(x-> {
                            try {
                                if(excluded.contains(x)){
                                    return false;
                                }
                                for(String fieldName : fieldNames){
                                    if(!x.get(fieldName).equalsIgnoreCase(volunteer.get(fieldName))){
                                        return false;
                                    }
                                }
                                return true;
                            } catch (Exception e) {
                                return false;
                            }
                        }).collect(Collectors.toList());
                        if(sameVolunteersByProperty.size() > 1){
                            cleanVolunteer.add(Volunteer.concatMultiple(sameVolunteersByProperty));
                            volunteersPassed.addAll(sameVolunteersByProperty);
                        }
                    }
                }
                badVolunteer.add(volunteer);
            }
        }
        mapToRemove.put(false,badVolunteer);
        mapToRemove.put(true,cleanVolunteer);
        return mapToRemove;
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
        ArrayList<Volunteer> volunteersWithNoNames = new ArrayList<>();

        for (Volunteer volunteer : this.allVolunteers) {

            if (volunteer.getFirstName() == null || volunteer.getFirstName().isEmpty()) {
                volunteersWithNoNames.add(volunteer);
                continue;
            }

            if (volunteer.getLastName() == null || volunteer.getLastName().isEmpty()) {
                volunteersWithNoNames.add(volunteer);
            }

            if (this.validators.validateFirstName(volunteer.getFirstName()) || this.validators.validateLastName(volunteer.getLastName())) {
                volunteersWithMalformedNames.add(volunteer);
            }

            if (mapVolunteerNames.containsKey(volunteer.getFirstName() + "." + volunteer.getLastName()) || mapVolunteerNames.containsKey(volunteer.getLastName() + "." + volunteer.getFirstName())) {
                String formatKey = mapVolunteerNames.containsKey(volunteer.getFirstName() + "." + volunteer.getLastName()) ? volunteer.getFirstName() + "." + volunteer.getLastName() : volunteer.getLastName() + "." + volunteer.getFirstName();
                List<Volunteer> storedDuplicateVolunteers = mapVolunteerNames.get(formatKey);
                int originalSize = storedDuplicateVolunteers.size();

                if (storedDuplicateVolunteers.size() > 0) {
                    boolean isDuplicated = false;
                    for (Volunteer storedVolunteer : storedDuplicateVolunteers) {
                        if (storedVolunteer.getEmail().equals(volunteer.getEmail()) || storedVolunteer.getFirstName().equals(volunteer.getLastName()) && storedVolunteer.getLastName().equals(volunteer.getFirstName())) {
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
        this.nameValidator = new VolunteerNameError(mapDuplicateNamesVolunteers, volunteersWithMalformedNames, volunteersWithNoNames);
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
