package org.example.volunteers;

import java.util.*;
import java.util.stream.Collectors;

public class Cleaner {
    public static List<List<Volunteer>> cleanUp(List<Volunteer> volunteers) {
        volunteers.forEach(volunteer -> {
            volunteer.phone = phoneNumberFormatter(volunteer.phone);
            volunteer.eMail = emailValidator(volunteer.eMail);
        });
        List<Volunteer> duplicate = getDuplicatedEmail(volunteers);
        List<Volunteer> noPhoneNoEmail = cleanNoPhoneNoEmail(volunteers);
        volunteers.removeAll(duplicate);
        duplicate.addAll(getDuplicatedPhone(volunteers));
        volunteers.removeAll(duplicate);
        volunteers.removeAll(noPhoneNoEmail);
        List<List<Volunteer>> result = new ArrayList<>();
        result.add(volunteers);
        result.add(duplicate);
        result.add(noPhoneNoEmail);

        return result;
    }


    public static List<Volunteer> cleanNoPhoneNoEmail(List<Volunteer> volunteers) {
        return volunteers.stream().filter(volunteer -> volunteer.eMail.equals("Null") && volunteer.phone.equals("Null")).collect(Collectors.toList());
    }

    public static String phoneNumberFormatter(String phoneNumber) {
        phoneNumber.trim();
        phoneNumber = phoneNumber.replaceAll("^(\\+33)", "");
        phoneNumber = phoneNumber.replaceAll("\\D", "");
        if (phoneNumber.length() > 10 || phoneNumber.length() < 9) {
            return "Null";
        }
        if (phoneNumber.length() == 10 && phoneNumber.toCharArray()[0] == '0') {
            phoneNumber = phoneNumber.replaceAll("^0", "");
        }
        return "+33" + phoneNumber;
    }

    public static String emailValidator(String email) {
        email.trim();
        boolean match = email.matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
        );
        if (match) {
            return email;
        }
        return "Null";
    }


    public static List<Volunteer> getDuplicatedEmail(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().filter(volunteer -> volunteer.getEmail() != "Null").collect(Collectors.groupingBy(Volunteer::getEmail));
        temp.values().forEach(volunteersDu -> {
            getDuplicatedNames(volunteersDu).forEach(volunteerFullNameDupli -> {
                if (volunteersDu.size() > 1 && volunteersDu.contains(volunteerFullNameDupli)) {
                    for (int u = 0; u <= volunteersDu.size(); u++) {
                        if (u != volunteersDu.size()) {
                            temp.remove(volunteerFullNameDupli);
                        }
                    }

//                    volunteersDu.remove(volunteerFullNameDupli);
                }
            });
        });

        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedPhone(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getPhone));
        temp.values().forEach(volunteersDu -> {
            getDuplicatedNames(volunteersDu).forEach(volunteerFullNameDupli -> {
                if (volunteersDu.size() > 1 && volunteersDu.contains(volunteerFullNameDupli)) {
                    for (int u = 0; u <= volunteersDu.size(); u++) {
                        if (u != volunteersDu.size()) {
                            volunteersDu.remove(volunteerFullNameDupli);
                        }
                    }
                }
            });
        });
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedFirstName(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getFirstName));
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedLastName(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getLastName));
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedNames(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getFullName));
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedReverseNames(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getReverseFullName));
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    public static List<Volunteer> getDuplicatedNickName(List<Volunteer> volunteers) {
        Map<String, List<Volunteer>> temp = volunteers.stream().collect(Collectors.groupingBy(Volunteer::getNickName));
        return temp.values().stream().filter(a -> a.size() > 1).flatMap(l -> l.stream()).collect(Collectors.toList());
    }


}
