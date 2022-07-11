package org.example.volunteers.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VolunteerEmailError {

    public List<Volunteer> noEmail ;
    public HashMap<String , List<Volunteer>> duplicateEmail ;
    public List<Volunteer> badFormatEmail ;

    public VolunteerEmailError( List<Volunteer> noEmail ,HashMap<String , List<Volunteer>> duplicateEmail  ,   List<Volunteer> badFormatEmail){
        this.noEmail = noEmail;
        this.duplicateEmail = duplicateEmail;
        this.badFormatEmail = badFormatEmail;
    }
}
