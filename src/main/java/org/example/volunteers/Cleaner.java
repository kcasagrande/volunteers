package org.example.volunteers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }

    public static String formatFirstName(String name){
        name = name.toLowerCase();
        name = name.replaceAll("[^a-zA-Z-–—−]","");
        String formattedFirstName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return formattedFirstName;
    }

    public static String formatLastName(String name){
        name = name.toLowerCase();
        name = name.replaceAll("[^a-zA-Z-–—−]","");
        String formattedLastName = name.toUpperCase();
        return formattedLastName;
    }
}
