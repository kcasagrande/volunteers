package org.example.volunteers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Duplicate {
    public static List<Volunteer> removeDuplicateFirstNameLastNamePseudoMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();
        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            Volunteer reversedVolunteer = new Volunteer(volunteer.lastName, volunteer.firstName, volunteer.nickName, volunteer.eMail, volunteer.phone);
            if(!(linkedsetVolunteers.contains(volunteer.toString()) || linkedsetVolunteers.contains(reversedVolunteer.toString()))) {
                linkedsetVolunteers.add(volunteer.toString());
                uniqueVolunteers.add(volunteer);
            }
        }

        return uniqueVolunteers;
    }

    public static List<Volunteer> removeDuplicateMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();
        LinkedHashSet<String> linkedsetVolunteers = new LinkedHashSet<String>();

        for (Volunteer volunteer: volunteers) {
            String volunteerStringTest = volunteer.eMail + ";" + volunteer.phone;

            if (!linkedsetVolunteers.contains(volunteerStringTest)) {
                linkedsetVolunteers.add(volunteerStringTest);
                uniqueVolunteers.add(volunteer);
            }
        }

        return uniqueVolunteers;
    }

    public static List<Volunteer> concatDuplicateMailPhone(List<Volunteer> volunteers) {
        List<Volunteer> uniqueVolunteers = new ArrayList<>();

        for (Volunteer volunteer: volunteers) {
            List<Volunteer> findedVolunteers = uniqueVolunteers.stream().filter(
                (Volunteer v) -> (Objects.equals(v.firstName, volunteer.firstName)
                            && Objects.equals(v.lastName, volunteer.lastName)
                            && Objects.equals(v.nickName, volunteer.nickName))
            ).collect(Collectors.toList());

            if (findedVolunteers.size() == 0) {
                uniqueVolunteers.add(volunteer);
            } else {
                Volunteer volunteerConcact = findedVolunteers.get(0);
                if (!volunteerConcact.eMail.contains(volunteer.eMail) && !volunteerConcact.eMail.isEmpty()) {
                    volunteerConcact.setEMail(volunteerConcact.eMail + "," + volunteer.eMail);
                }
                if (!volunteerConcact.phone.contains(volunteer.phone) && !volunteerConcact.phone.isEmpty()) {
                    volunteerConcact.setPhone(volunteerConcact.phone + "," + volunteer.phone);
                }
            }
        }

        return uniqueVolunteers;
    }
}
