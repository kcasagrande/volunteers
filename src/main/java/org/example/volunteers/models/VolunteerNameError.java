package org.example.volunteers.models;

import java.util.HashMap;
import java.util.List;

public class VolunteerNameError {

    public HashMap<String , List<Volunteer>> duplicateName ;
    public List<Volunteer> malformedNames ;

    public VolunteerNameError( HashMap<String, List<Volunteer>> duplicateName, List<Volunteer> malformedNames){
        this.duplicateName = duplicateName;
        this.malformedNames = malformedNames;
    }


}
