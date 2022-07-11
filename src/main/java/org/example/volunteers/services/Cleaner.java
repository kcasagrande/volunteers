package org.example.volunteers.services;

import org.example.volunteers.models.Volunteer;

import java.util.ArrayList;
import java.util.List;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return new ArrayList<>(volunteers);
    }

    public static List<Volunteer> removeDuplicateByEmail() throws Exception{
        throw new Exception("not implemented");
    }

    public static List<Volunteer> removeDuplicateByFullName() throws Exception{
        throw new Exception("not implemented");
    }

    public  static List<Volunteer> removeDuplicateByPhoneNumber() throws Exception{
        throw new Exception("not implemented");
    }

    public  static String formatPhoneNumber(String phoneNumber){
        String result = phoneNumber.replaceFirst("\\+\\d{2}","0")
                .replaceFirst("\\(0\\)", "")
                .replace(".", "")
                .replace("-", "")
                .replace(" ", "");
        System.out.println(result);
        return result;
    }

    public  static boolean hasDuplicatePhoneNumber(ArrayList<Volunteer> volunteersList){
        if (volunteersList.size() == 0) {
            return false;
        }

        for (int i = 0; i < volunteersList.size(); i++) {
            Volunteer volA = volunteersList.get(i);
            Volunteer volB = volunteersList.get(i + 1);
        }
        return true;
    }
}
