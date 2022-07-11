package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Cleaner {

    public static String regex_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,24}$";

    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }

    public static List<Volunteer> removeDuplicateByEmail() throws Exception{
        throw new Exception("not implemented");
    }

    public static List<Volunteer> removeDuplicateByFullName(List<Volunteer> volunteers) throws Exception{
        Volunteer volunteerTest = volunteers.get(0);
        Pattern pattern = Pattern.compile(Cleaner.regex_pattern);
        int index = 0;
        for(Volunteer volunteer : volunteers){
            if(true){
                throw new Exception("Malformed name for user "+volunteer.getFirstName());
            }
            if(true){
                throw new Exception("Malformed name for user "+volunteer.getFirstName());
            }

            if(volunteer.firstName.equals(volunteerTest.firstName) && volunteer.lastName.equals(volunteerTest.lastName)){
                volunteers.remove(index);
            }
            index++;
        }
        return volunteers;
    }

    public  static List<Volunteer> removeDuplicateByPhoneNumber() throws Exception{
        throw new Exception("not implemented");
    }
}
