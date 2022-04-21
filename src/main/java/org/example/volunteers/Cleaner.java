package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        List<Volunteer> newVolunteers = Duplicate.regroupByName(volunteers);
        newVolunteers = Duplicate.duplicateByLevenshtein(newVolunteers);
        return newVolunteers;
    }
}
