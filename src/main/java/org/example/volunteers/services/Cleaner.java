package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;
import org.example.volunteers.models.VolunteerEmailError;
import org.example.volunteers.models.VolunteerNameError;

import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;


public class Cleaner {

    private Validations validators;
    public VolunteerEmailError emailValidator;
    public VolunteerNameError nameValidator;
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
        volunteersToRemove.addAll(this.emailValidator.noEmail);
        volunteersToRemove.addAll(this.emailValidator.badFormatEmail);
        for (String email : this.emailValidator.duplicateEmail.keySet()){
            volunteersToRemove.addAll(this.emailValidator.duplicateEmail.get(email));
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


    public void checkNames(){

        HashMap<String , List<Volunteer>> mapVolunteerNames = new HashMap<>();
        ArrayList<Volunteer> volunteersWithMalformedNames = new ArrayList<>();

        for(Volunteer volunteer : this.allVolunteers){

            if(volunteer.getFirstName() == null || volunteer.getFirstName().isEmpty()){
                volunteersWithMalformedNames.add(volunteer);
                continue;
            }

            if(volunteer.getLastName() == null || volunteer.getLastName().isEmpty()){
                volunteersWithMalformedNames.add(volunteer);
            }

            if(this.validators.validateFirstName(volunteer.getFirstName()) || this.validators.validateLastName(volunteer.getLastName())){
                volunteersWithMalformedNames.add(volunteer);
            }

            if(mapVolunteerNames.containsKey(volunteer.getFirstName()+"."+volunteer.getLastName())){
                List<Volunteer> storedDuplicateVolunteers = mapVolunteerNames.get(volunteer.getFirstName()+"."+volunteer.getLastName());
                int originalSize = storedDuplicateVolunteers.size();

                if(storedDuplicateVolunteers.size() > 0){
                    boolean isDuplicated = false;
                    for(Volunteer storedVolunteer : storedDuplicateVolunteers){
                        if (storedVolunteer.getEmail().equals(volunteer.getEmail())) {
                            isDuplicated = true;

                            break;
                        }
                    }
                    if(isDuplicated){
                        storedDuplicateVolunteers.add(volunteer);
                    }
                }else{
                    storedDuplicateVolunteers.add(volunteer);
                }

                if(originalSize != storedDuplicateVolunteers.size()){
                    mapVolunteerNames.put(volunteer.getFirstName()+"."+volunteer.getLastName() , storedDuplicateVolunteers);
                }

            }else{
                mapVolunteerNames.put(volunteer.getFirstName()+"."+volunteer.getLastName() , new ArrayList<>(Arrays.asList(volunteer)));
            }
        }

        HashMap<String , List<Volunteer>> mapDuplicateNamesVolunteers = mapVolunteerNames
                .entrySet()
                .stream()
                .filter(x-> x.getValue().stream().count() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));
        this.nameValidator= new VolunteerNameError(mapDuplicateNamesVolunteers, volunteersWithMalformedNames);
    }

    public  static List<Volunteer> removeDuplicateByPhoneNumber() throws Exception{
        throw new Exception("not implemented");
    }
}
