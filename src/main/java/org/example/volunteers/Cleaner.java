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

    public static String capitalizeWord(String str){  
        String words[] = str.toLowerCase().split("\\s");
        String capitalizeWord = "";  
        for(String w:words){  
            String first = w.substring(0,1);  
            String afterfirst = w.substring(1);  
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }  
        return capitalizeWord.trim();  
    }  

    public static String formatFirstName(String name){
        name = capitalizeWord(name);
        name = name.trim().replaceAll(" ", "-");
        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−]","");
        String formattedFirstName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return formattedFirstName;
    }

    public static String formatLastName(String name){
        name = capitalizeWord(name);
        name = name.toLowerCase().trim().replaceAll(" ", "-");
        // Replace all characters except a to z, A to Z and dashes for compound noun
        name = name.replaceAll("[^a-zA-Z-–—−]","");
        String formattedLastName = name.toUpperCase();
        return formattedLastName;
    }
}
