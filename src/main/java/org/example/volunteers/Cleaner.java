package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers, FieldsSanitizer fieldsSanitizer) {
        HashMap<String, Volunteer> volunteersFullName = new HashMap<>();
        HashMap<String, Volunteer> volunteersNickName = new HashMap<>();

        List<Volunteer> result = new ArrayList<>();

        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        for (Volunteer volunteer : volunteers) {
            volunteer.firstName = fieldsSanitizer.clearName(volunteer.firstName);
            volunteer.lastName = fieldsSanitizer.clearName(volunteer.lastName);
            volunteer.eMail = fieldsSanitizer.clearEmail(volunteer.eMail);
            volunteer.phone = fieldsSanitizer.clearPhone(volunteer.phone);
        }

        for (Volunteer volunteer : volunteers) {
            String fullName = volunteer.firstName.toLowerCase() + volunteer.lastName.toLowerCase();

            if (fullName.equals("")) {
                Volunteer originalVolunteer = volunteersNickName.get(volunteer.nickName.toLowerCase());

                if (originalVolunteer == null) {
                    volunteersNickName.put(volunteer.nickName.toLowerCase(), volunteer);
                    result.add(volunteer);
                } else {
                    processDuplicate(originalVolunteer, volunteer);
                }
            } else {
                Volunteer originalVolunteer = volunteersFullName.get(fullName);

                if (originalVolunteer == null) {
                    volunteersFullName.put(fullName, volunteer);
                    result.add(volunteer);
                } else {
                    processDuplicate(originalVolunteer, volunteer);
                }
            }
        }

        return result;
    }

    private static void processDuplicate(Volunteer original, Volunteer duplicate) {
        if (!original.eMail.contains(duplicate.eMail)) {
            original.eMail += getSeparator(original.eMail) + duplicate.eMail;
        }

        if (!original.phone.contains(duplicate.phone)) {
            original.phone += getSeparator(original.phone) + duplicate.phone;
        }

        if (!original.nickName.contains(duplicate.nickName)) {
            original.nickName += getSeparator(original.nickName) + duplicate.nickName;
        }
    }

    private static String getSeparator(String str) {
        return (!str.equals("") ? ";" : "");
    }
}
